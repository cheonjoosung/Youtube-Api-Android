package kr.co.js.youtube_api.model.repository

import androidx.annotation.WorkerThread
import kr.co.js.youtube_api.model.network.YoutubeApiRequestFactory

class SearchRepository{

    //TODO NEED GOOGLE APIKEY for youtube (https://console.cloud.google.com/apis)
    private val apiKey = ""

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