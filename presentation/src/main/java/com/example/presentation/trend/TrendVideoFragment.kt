package com.example.presentation.trend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.presentation.databinding.FragmentTrendVideoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrendVideoFragment : Fragment() {

    companion object {
        val TAG: String = TrendVideoFragment::class.java.simpleName
    }

    private val viewModel: TrendVideoViewModel by viewModels()

    private lateinit var binding: FragmentTrendVideoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrendVideoBinding.inflate(inflater)

        binding.apply {
            swipeRefresh.setOnRefreshListener {
                viewModel.getTrendVideos()
            }
        }

        viewModel.trendVideoList.observe(requireActivity()) { list ->

            if (binding.swipeRefresh.isRefreshing) {
                binding.swipeRefresh.isRefreshing = false
            }

            binding.rvTrendList.adapter = TrendVideoAdapter(list.toMutableList()).apply {
                onVideoClick = { video ->
                    Toast.makeText(requireContext(), "Click $video", Toast.LENGTH_SHORT).show()
//                    video.numberOfTimes += 1
//                    videoClickListener.videoClick(video.convertToVideo(video))
                }

                onVideoLongClick = { video ->
                    Toast.makeText(requireContext(), "LongCLick $video", Toast.LENGTH_SHORT).show()
//                    val msg = if (video.isFavorite) {
//                        video.isFavorite = false
//                        requireContext().getString(R.string.msg_delete_favorite_video)
//                    } else {
//                        video.isFavorite = true
//                        requireContext().getString(R.string.msg_add_favorite_video)
//                    }
//
//                    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
//                    videoViewModel.updateTrendVideo(video)
//                    isLoadPass = true
//                    videoViewModel.addOrRemoveFavoriteVideo(video.convertToVideo(video))
                }

                onVideoMoreClick = { video, view ->
                    Toast.makeText(requireContext(), "onVideoMoreClick $video", Toast.LENGTH_SHORT).show()
                    //showPopup(video, view)
                }
            }
        }

        viewModel.getTrendVideos()
        return binding.root
    }

}