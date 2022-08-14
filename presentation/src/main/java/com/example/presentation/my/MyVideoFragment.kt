package com.example.presentation.my

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.presentation.databinding.FragmentMyVideoBinding

class MyVideoFragment : Fragment() {

    companion object {
        val TAG: String = MyVideoFragment::class.java.simpleName
    }

    private lateinit var viewModel: MyVideoViewModel

    private lateinit var binding: FragmentMyVideoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyVideoBinding.inflate(inflater)
        return binding.root
    }

}