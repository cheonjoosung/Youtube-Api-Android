package com.example.presentation.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.MainActivity
import com.example.presentation.databinding.FragmentSearchVideoBinding
import com.example.presentation.player.YoutubePlayerActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchVideoFragment : Fragment() {

    companion object {
        val TAG: String = SearchVideoFragment::class.java.simpleName
    }

    private lateinit var binding: FragmentSearchVideoBinding

    private val searchVideoViewModel: SearchVideoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchVideoBinding.inflate(inflater)

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

        searchVideoViewModel.firstSearch.observe(requireActivity()) { list ->
            binding.rvSearchResult.adapter = SearchAdapter(list.toMutableList()).apply {
                videoClick = { video ->
                    Intent(requireContext(), YoutubePlayerActivity::class.java).apply {
                        putExtra(YoutubePlayerActivity.VIDEO, video)
                        startActivity(this)
                    }
                }

                videoLongClick = { _ ->

                }
            }
        }

        searchVideoViewModel.moreSearch.observe(requireActivity()) { addList ->
            (binding.rvSearchResult.adapter as SearchAdapter).addMoreVideoList(addList.toMutableList())
        }

        binding.rvSearchResult.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                binding.rvSearchResult.adapter?.let {
                    val lastVisibleItemPosition =
                        (binding.rvSearchResult.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()

                    if (lastVisibleItemPosition == it.itemCount - 1) {
                        Toast.makeText(requireContext(), "Request More Videos", Toast.LENGTH_SHORT)
                            .show()
                        searchVideoViewModel.getYoutubeVideoMore()
                    }
                }
            }
        })

        return binding.root
    }

    private fun searchYoutube(keyword: String) {
        if (keyword.isEmpty()) {
            Toast.makeText(requireContext(), "Search Keyword Empty!!", Toast.LENGTH_SHORT).show()
            return
        }

        hideKeyboard()
        Log.e(MainActivity.TAG, "keywrod $keyword")

        searchVideoViewModel.getYoutubeVideo(keyword)
    }

    private fun hideKeyboard() {
        binding.etSearch.clearFocus()
        val inputMethodManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.etSearch.windowToken, 0)
    }
}