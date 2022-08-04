package kr.co.js.youtube_api.data.repository

import androidx.annotation.WorkerThread
import kr.co.js.youtube_api.model.network.YoutubeApiRequestFactory

class VideoRepository{

    private val apiKey = "AIzaSyDJbO6MYWglVOs9PI8uejU4N7qDoewaYC8"

    @WorkerThread
    suspend fun requestVideoInfo(videoId: String) =
        YoutubeApiRequestFactory.retrofit.getVideoInfo(
            apiKey = apiKey,
            videoId = videoId
        )
}