package kr.co.js.youtube_search.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kr.co.js.youtube_search.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = MainActivity::class.java.simpleName
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            ivSearch.setOnClickListener {
                searchYoutube(binding.etSearch.text.toString())
            }

            etSearch.setOnEditorActionListener { _, id, keyEvent ->
                if (id == EditorInfo.IME_ACTION_SEARCH) {
                    searchYoutube(etSearch.text.toString())
                }

                true
            }
        }
    }

    private fun searchYoutube(keyword: String) {
        if (keyword.isEmpty()) {
            Toast.makeText(applicationContext, "Search Keyword Empty!!", Toast.LENGTH_SHORT).show()
            return
        }

        hideKeyboard()

        Log.e(TAG, "keywrod $keyword")
    }

    private fun hideKeyboard() {
        binding.etSearch.clearFocus()
        val inputMethodManager = applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.etSearch.windowToken, 0)
    }
}