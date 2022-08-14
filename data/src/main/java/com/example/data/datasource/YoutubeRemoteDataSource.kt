package com.example.data.datasource

import com.example.data.model.YoutubeVideo
import com.example.data.model.YoutubeVideoInfo
import retrofit2.Response

interface YoutubeRemoteDataSource {

    suspend fun searchYoutube(searchText: String): Response<YoutubeVideo>

    suspend fun searchYoutubeMore(searchText: String, nextPageToken: String): Response<YoutubeVideo>

    suspend fun getYoutubeVideoInfo(videoId: String): Response<YoutubeVideoInfo>
}