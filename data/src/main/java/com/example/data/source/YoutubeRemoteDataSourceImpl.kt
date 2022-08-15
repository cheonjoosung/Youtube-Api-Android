package com.example.data.source

import android.util.Log
import com.example.data.model.YoutubeChannelInfo
import com.example.data.model.YoutubeVideo
import com.example.data.model.YoutubeVideoInfo
import com.example.data.service.YoutubeApiService
import com.example.data.utils.Utils
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class YoutubeRemoteDataSourceImpl @Inject constructor(
    private val youtubeApiService: YoutubeApiService
) : YoutubeRemoteDataSource {

    val apiKey = "AIzaSyDJbO6MYWglVOs9PI8uejU4N7qDoewaYC8"

    override suspend fun searchYoutube(searchText: String): Response<YoutubeVideo> {
        return youtubeApiService.getYouTubeVideos(
            apiKey = apiKey, query = searchText, videoOrder = "relevance", maxResults = 10
        )
    }

    override suspend fun searchYoutubeMore(
        searchText: String,
        nextPageToken: String
    ): Response<YoutubeVideo> {
        return youtubeApiService.getYouTubeMoreVideos(
            apiKey = apiKey,
            query = searchText,
            nextPageToken = nextPageToken,
            videoOrder = "relevance",
            maxResults = 10
        )
    }

    override suspend fun getYoutubeVideoInfo(videoId: String): Response<YoutubeVideoInfo> {
        return youtubeApiService.getVideoInfo(
            apiKey = apiKey,
            videoId = videoId
        )
    }

    override suspend fun getTrendYoutubeVideo(): Response<YoutubeVideoInfo> {
        return youtubeApiService.getYoutubeTrendVideos(
            apiKey = apiKey, regionCode = Utils().getISORegionCode(), maxResults = 10
        )
    }

    override suspend fun getYoutubeChannelInfo(channelId: String): Response<YoutubeChannelInfo> {
        return youtubeApiService.getYoutubeChannelInfo(
            apiKey = apiKey, channelId = channelId
        )
    }
}
