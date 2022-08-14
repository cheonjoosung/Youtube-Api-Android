package com.example.domain.repository

import com.example.domain.model.*

interface YoutubeRepository {
    suspend fun searchYoutube(searchKeyword: String): ApiResult<VideoResult>

    suspend fun searchYoutubeMore(searchKeyword: String, nextPageToken: String): ApiResult<VideoResult>

    suspend fun getYoutubeVideoInfo(videoId: String): ApiResult<VideoInfo>

    suspend fun getYoutubeTrendVideo(): ApiResult<TrendVideoResult>
}