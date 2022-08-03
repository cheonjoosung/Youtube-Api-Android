package kr.co.js.youtube_api.ui.trend

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.js.youtube_api.databinding.FragmentSearchVideoBinding
import kr.co.js.youtube_api.databinding.FragmentTrendVideoBinding

class TrendVideoFragment : Fragment() {

    companion object {
        val TAG: String = TrendVideoFragment::class.java.simpleName
    }

    private lateinit var viewModel: TrendVideoViewModel

    private lateinit var binding: FragmentTrendVideoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrendVideoBinding.inflate(inflater)
        return binding.root
    }

}