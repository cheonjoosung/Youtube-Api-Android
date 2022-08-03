package kr.co.js.youtube_api.ui.my

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.js.youtube_api.databinding.FragmentMyVideoBinding
import kr.co.js.youtube_api.databinding.FragmentSearchVideoBinding

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