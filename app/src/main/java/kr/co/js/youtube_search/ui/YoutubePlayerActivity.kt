package kr.co.js.youtube_search.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kr.co.js.youtube_search.R
import kr.co.js.youtube_search.databinding.ActivityMainBinding
import kr.co.js.youtube_search.databinding.ActivityYoutubePlayerBinding
import kr.co.js.youtube_search.model.vo.Video

class YoutubePlayerActivity : AppCompatActivity() {

    companion object {
        val VIDEO = "VIDEO"
        val TAG = YoutubePlayerActivity::class.java.simpleName
    }

    private lateinit var binding: ActivityYoutubePlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityYoutubePlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<Video>(VIDEO)
        Log.e(TAG, "data is $data")
    }
}