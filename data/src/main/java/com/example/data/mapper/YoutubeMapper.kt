package com.example.data.mapper

import com.example.data.model.YoutubeVideo
import com.example.data.model.YoutubeVideoInfo
import com.example.domain.model.Video
import com.example.domain.model.VideoInfo

object YoutubeMapper {

    //TODO 유튜브 매퍼 & 인포 매퍼 만들기
    fun youtubeMapper(
        youtubeVideo: YoutubeVideo?
    ): List<Video> {
        return if (youtubeVideo?.items.isNullOrEmpty()) return emptyList()
        else {
            emptyList()

        }
    }

    fun youtubeInfoMapper(
        youtubeVideoInfo: YoutubeVideoInfo?
    ): List<VideoInfo> {
        return if (youtubeVideoInfo?.items.isNullOrEmpty()) return emptyList()
        else {
            emptyList()

        }
    }
}