package com.example.data.mapper

import android.util.Log
import com.example.data.model.YoutubeChannelInfo
import com.example.data.model.YoutubeVideo
import com.example.data.model.YoutubeVideoInfo
import com.example.domain.model.*

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
                                channelTitle = snippet.channelTitle,
                                channelId = snippet.channelId
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
                        channelTitle = "",
                        channelId = ""
                    )
                )
            } ?: run {
                ApiResult.Error()
            }

        }
    }

    fun youtubeTrendMapper(
        youtubeVideoInfo: YoutubeVideoInfo?
    ): ApiResult<TrendVideoResult> {
        return if (youtubeVideoInfo?.items.isNullOrEmpty()) ApiResult.Error()
        else {

            youtubeVideoInfo?.items?.let { list ->

                val trendVideoResult = TrendVideoResult()

                val tempList = mutableListOf<VideoInfo>()

                for (item in list) {
                    tempList.add(
                        VideoInfo(
                            videoId = item.id,
                            title = item.snippet.title,
                            description = item.snippet.description,
                            publishedAt = item.snippet.publishedAt,
                            imgUrl = item.snippet.thumbnails.medium.url,
                            channelTitle = item.snippet.channelTitle,
                            duration = item.contentDetails.duration,
                            viewCount = item.statistics.viewCount ?: "0",
                            channelId = item.snippet.channelId
                        )
                    )
                }

                trendVideoResult.videoList.addAll(tempList)
                ApiResult.Success(trendVideoResult)

            } ?: run {
                ApiResult.Error()
            }

        }
    }

    fun youtubeChannelMapper(
        youtubeChannelInfo: YoutubeChannelInfo?
    ): ApiResult<ChannelInfo> {

        return if (youtubeChannelInfo?.items.isNullOrEmpty()) ApiResult.Error()
        else {
            youtubeChannelInfo?.items?.get(0)?.let { info ->
                ApiResult.Success(
                    ChannelInfo(
                        title = "",
                        description = "",
                        imgUrl = info.snippet.thumbnails.medium.url
                    )
                )

            } ?: run {
                ApiResult.Error()
            }
        }
    }
}