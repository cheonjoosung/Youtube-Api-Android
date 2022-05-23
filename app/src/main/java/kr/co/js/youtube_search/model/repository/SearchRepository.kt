package kr.co.js.youtube_search.model.repository

import androidx.annotation.WorkerThread
import kr.co.js.youtube_search.model.dao.SearchDao
import kr.co.js.youtube_search.model.network.YoutubeApiRequestFactory

class SearchRepository(
    private val searchDao: SearchDao
) {

    val allSearchResult = searchDao.getAllSearchResult()

    private val apiKey = ""

    fun getYoutubeVideo() {

    }

    /**
     * 유투브 비디오 검색
     */
    @WorkerThread
    suspend fun requestVideos(searchText: String) =
        YoutubeApiRequestFactory.retrofit.getYouTubeVideos(
            apiKey = apiKey, query = searchText, videoOrder = "relevance", maxResults = 20
        )

    @WorkerThread
    suspend fun requestMoreVideos(searchText: String, nextPageToken: String) =
        YoutubeApiRequestFactory.retrofit.getYouTubeMoreVideos(
            apiKey = apiKey,
            query = searchText,
            nextPageToken = nextPageToken,
            videoOrder = "relevance",
            maxResults = 20
        )

    @WorkerThread
    suspend fun requestVideosInfo(videoId: String) =
        YoutubeApiRequestFactory.retrofit.getYoutubeTrendVideosInfo(
            apiKey = apiKey,
            videoId = videoId
        )
}