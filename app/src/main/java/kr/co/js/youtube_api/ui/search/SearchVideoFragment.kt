package kr.co.js.youtube_api.ui.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.js.youtube_api.R
import kr.co.js.youtube_api.databinding.FragmentSearchVideoBinding

class SearchVideoFragment : Fragment() {

    companion object {
        val TAG: String = SearchVideoFragment::class.java.simpleName
    }

    private lateinit var viewModel: SearchVideoViewModel

    private lateinit var binding: FragmentSearchVideoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchVideoBinding.inflate(inflater)
        return binding.root
    }

}