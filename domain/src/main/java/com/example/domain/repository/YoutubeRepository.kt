package com.example.domain.repository

import com.example.domain.model.ApiResult
import com.example.domain.model.Video
import com.example.domain.model.VideoInfo
import com.example.domain.model.VideoResult

interface YoutubeRepository {
    suspend fun searchYoutube(searchKeyword: String): ApiResult<VideoResult>

    suspend fun searchYoutubeMore(searchKeyword: String, nextPageToken: String): ApiResult<VideoResult>

    suspend fun getYoutubeVideoInfo(videoId: String): ApiResult<VideoInfo>
}