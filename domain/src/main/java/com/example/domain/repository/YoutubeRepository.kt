package com.example.domain.repository

import com.example.domain.model.Video
import com.example.domain.model.VideoInfo

interface YoutubeRepository {
    suspend fun searchYoutube(searchKeyword: String): List<Video>

    suspend fun searchYoutubeMore(searchKeyword: String, nextPageToken: String): List<Video>

    suspend fun getYoutubeVideoInfo(videoId: String): List<VideoInfo>
}