package kr.co.js.youtube_search.model.network

import kr.co.js.youtube_search.model.vo.YoutubeVideo
import kr.co.js.youtube_search.model.vo.YoutubeVideoInfo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApiService {

    @GET("search")
    suspend fun getYouTubeVideos(
        @Query("key") apiKey: String,
        @Query("q") query: String,
        @Query("order") videoOrder: String,
        @Query("type") videoType: String = "video",
        @Query("maxResults") maxResults: Int,
        @Query("channelId") channelId: String = "",
        @Query("part") part: String = "snippet",
    ): Response<YoutubeVideo>

    @GET("search")
    suspend fun getYouTubeMoreVideos(
        @Query("key") apiKey: String,
        @Query("q") query: String,
        @Query("pageToken") nextPageToken: String,
        @Query("order") videoOrder: String,
        @Query("type") videoType: String = "video",
        @Query("maxResults") maxResults: Int,
        @Query("channelId") channelId: String = "",
        @Query("part") part: String = "snippet",
    ): Response<YoutubeVideo>

    @GET("videos")
    suspend fun getYoutubeTrendVideosInfo(
        @Query("key") apiKey: String,
        @Query("id") videoId: String,
        @Query("part") part: String = "contentDetails, statistics",
    ): Response<YoutubeVideoInfo>
}