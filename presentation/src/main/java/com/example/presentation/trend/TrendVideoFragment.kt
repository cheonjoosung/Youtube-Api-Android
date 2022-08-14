package com.example.presentation.trend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.presentation.databinding.FragmentTrendVideoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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