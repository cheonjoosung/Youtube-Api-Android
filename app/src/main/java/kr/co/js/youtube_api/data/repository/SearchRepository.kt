package kr.co.js.youtube_api.data.repository

import androidx.annotation.WorkerThread
import kr.co.js.youtube_api.model.network.YoutubeApiRequestFactory

class SearchRepository{

    private val apiKey = "AIzaSyDJbO6MYWglVOs9PI8uejU4N7qDoewaYC8"

    /**
     * 유투브 비디오 검색
     */
    @WorkerThread
    suspend fun getYoutubeVideo(searchText: String) =
        YoutubeApiRequestFactory.retrofit.getYouTubeVideos(
            apiKey = apiKey, query = searchText, videoOrder = "relevance", maxResults = 10
        )

    @WorkerThread
    suspend fun getYouTubeMoreVideos(searchText: String, nextPageToken: String) =
        YoutubeApiRequestFactory.retrofit.getYouTubeMoreVideos(
            apiKey = apiKey,
            query = searchText,
            nextPageToken = nextPageToken,
            videoOrder = "relevance",
            maxResults = 20
        )

    @WorkerThread
    suspend fun requestVideoInfo(videoId: String) =
        YoutubeApiRequestFactory.retrofit.getVideoInfo(
            apiKey = apiKey,
            videoId = videoId
        )
}