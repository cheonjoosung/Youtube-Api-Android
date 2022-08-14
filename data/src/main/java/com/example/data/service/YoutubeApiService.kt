package com.example.data.service

import com.example.data.model.YoutubeVideo
import com.example.data.model.YoutubeVideoInfo
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
    suspend fun getVideoInfo(
        @Query("key") apiKey: String,
        @Query("id") videoId: String,
        @Query("part") part: String = "contentDetails, statistics",
    ): Response<YoutubeVideoInfo>

    @GET("videos")
    suspend fun getYoutubeTrendVideos(
        @Query("key") apiKey: String,
        @Query("regionCode") regionCode: String,
        @Query("maxResults") maxResults: Int,
        @Query("chart") chart: String = "mostPopular",
        @Query("part") part: String = "snippet, contentDetails, statistics",
    ): Response<YoutubeVideoInfo>
}