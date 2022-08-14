package com.example.presentation.player

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.domain.model.Video
import com.example.presentation.databinding.ActivityYoutubePlayerBinding
import com.example.utils.VideoUtil
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YoutubePlayerActivity : AppCompatActivity() {

    companion object {
        val VIDEO = "VIDEO"
        val TAG = YoutubePlayerActivity::class.java.simpleName
    }

    private lateinit var binding: ActivityYoutubePlayerBinding

    private val utils = VideoUtil()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityYoutubePlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val video = intent.getParcelableExtra<Video>(VIDEO)
        Log.e(TAG, "data is $video")

        binding.apply {
            lifecycle.addObserver(youtubePlayerView)

            youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    val videoId = video?.videoId ?: ""
                    youTubePlayer.loadVideo(videoId, 0f)
                }
            })

            tvVideoTitle.text = video?.title
            tvVideoChannelName.text = video?.channelTitle
            tvVideoDate.text = utils.convertPublishedDate(video?.publishedAt ?: "")
            tvVideoViewCount.text = utils.convertViewCount(video?.viewCount ?: "")
            tvVideoDuration.text = utils.convertDurationToHHMMSS(video?.duration ?: "")
            tvVideoDescription.text = video?.description
        }
    }
}