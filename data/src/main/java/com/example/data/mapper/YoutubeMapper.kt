package com.example.data.mapper

import com.example.data.model.YoutubeVideo
import com.example.data.model.YoutubeVideoInfo
import com.example.domain.model.ApiResult
import com.example.domain.model.Video
import com.example.domain.model.VideoInfo
import com.example.domain.model.VideoResult

object YoutubeMapper {

    fun youtubeMapper(
        youtubeVideo: YoutubeVideo?
    ): ApiResult<VideoResult> {
        return if (youtubeVideo?.items.isNullOrEmpty()) ApiResult.Error()
        else {

            val videoResult = VideoResult()

            youtubeVideo?.let {
                videoResult.nextPageToken = youtubeVideo.nextPageToken

                val tempList = mutableListOf<Video>()

                it.items?.let { list ->
                    for (item in list) {
                        val id = item.id
                        val snippet = item.snippet

                        tempList.add(
                            Video(
                                videoId = id.videoId,
                                title = snippet.title,
                                description = snippet.description,
                                publishedAt = snippet.publishedAt,
                                imgUrl = snippet.thumbnails.medium.url,
                                channelTitle = snippet.channelTitle
                            )
                        )

                    }
                }

                videoResult.videoList.addAll(tempList)
            }

            ApiResult.Success(videoResult)
        }
    }

    fun youtubeInfoMapper(
        youtubeVideoInfo: YoutubeVideoInfo?
    ): ApiResult<VideoInfo> {
        return if (youtubeVideoInfo?.items.isNullOrEmpty()) ApiResult.Error()
        else {

            youtubeVideoInfo?.items?.let { list ->

                ApiResult.Success(
                    VideoInfo(
                        videoId = list[0].id,
                        duration = list[0].contentDetails.duration,
                        viewCount = list[0].statistics.viewCount.toString(),
                        title = "",
                        description = "",
                        imgUrl = "",
                        publishedAt = "",
                        channelTitle = ""
                    )
                )
            } ?: run {
                ApiResult.Error()
            }

        }
    }
}