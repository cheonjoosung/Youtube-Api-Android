package com.example.data.source

import com.example.data.model.YoutubeChannelInfo
import com.example.data.model.YoutubeVideo
import com.example.data.model.YoutubeVideoInfo
import retrofit2.Response

interface YoutubeRemoteDataSource {

    suspend fun searchYoutube(searchText: String): Response<YoutubeVideo>

    suspend fun searchYoutubeMore(searchText: String, nextPageToken: String): Response<YoutubeVideo>

    suspend fun getYoutubeVideoInfo(videoId: String): Response<YoutubeVideoInfo>

    suspend fun getTrendYoutubeVideo(): Response<YoutubeVideoInfo>

    suspend fun getYoutubeChannelInfo(channelId: String): Response<YoutubeChannelInfo>
}