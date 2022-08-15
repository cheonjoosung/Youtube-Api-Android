package com.example.domain.repository

import com.example.domain.model.*
import com.example.domain.model.remote.ChannelInfo
import com.example.domain.model.remote.TrendVideoResult
import com.example.domain.model.remote.VideoInfo
import com.example.domain.model.remote.VideoResult

interface YoutubeRepository {
    suspend fun searchYoutube(searchKeyword: String): ApiResult<VideoResult>

    suspend fun searchYoutubeMore(searchKeyword: String, nextPageToken: String): ApiResult<VideoResult>

    suspend fun getYoutubeVideoInfo(videoId: String): ApiResult<VideoInfo>

    suspend fun getYoutubeTrendVideo(): ApiResult<TrendVideoResult>

    suspend fun getYoutubeChannelInfo(channelId: String): ApiResult<ChannelInfo>
}