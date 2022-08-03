package kr.co.js.youtube_api.ui.search

import MainActivityViewModel
import MainActivityViewModelFactory
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.js.youtube_api.databinding.ActivityMainBinding
import kr.co.js.youtube_api.ui.VideoApplication
import kr.co.js.youtube_api.ui.YoutubePlayerActivity

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = MainActivity::class.java.simpleName
    }

    private lateinit var binding: ActivityMainBinding

    val mainViewModel: MainActivityViewModel by viewModels {
        MainActivityViewModelFactory((this.applicationContext as VideoApplication).searchRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            ivSearch.setOnClickListener {
                searchYoutube(binding.etSearch.text.toString())
            }

            etSearch.setOnEditorActionListener { _, id, _ ->
                if (id == EditorInfo.IME_ACTION_SEARCH) {
                    searchYoutube(etSearch.text.toString())
                }

                true
            }
        }

        mainViewModel.firstSearch.observe(this) { list ->
            binding.rvSearchResult.adapter = SearchAdapter(list.toMutableList()).apply {
                videoClick = { video ->
                    Intent(this@MainActivity, YoutubePlayerActivity::class.java).apply {
                        putExtra(YoutubePlayerActivity.VIDEO, video)
                        startActivity(this)
                    }
                }
            }
        }

        mainViewModel.moreSearch.observe(this) { addList ->
            (binding.rvSearchResult.adapter as SearchAdapter).addMoreVideoList(addList.toMutableList())
        }

        binding.rvSearchResult.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                binding.rvSearchResult.adapter?.let {
                    val lastVisibleItemPosition =
                        (binding.rvSearchResult.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()

                    if (lastVisibleItemPosition == it.itemCount - 1) {
                        Toast.makeText(applicationContext, "Request More Videos", Toast.LENGTH_SHORT).show()
                        mainViewModel.getYoutubeVideoMore()
                    }
                }
            }
        })
    }

    private fun searchYoutube(keyword: String) {
        if (keyword.isEmpty()) {
            Toast.makeText(applicationContext, "Search Keyword Empty!!", Toast.LENGTH_SHORT).show()
            return
        }

        hideKeyboard()
        Log.e(TAG, "keywrod $keyword")

        mainViewModel.getYoutubeVideo(keyword)
    }

    private fun hideKeyboard() {
        binding.etSearch.clearFocus()
        val inputMethodManager =
            applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.etSearch.windowToken, 0)
    }
}