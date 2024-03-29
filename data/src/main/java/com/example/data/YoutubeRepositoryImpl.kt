package com.example.data

import com.example.data.mapper.YoutubeMapper
import com.example.data.source.YoutubeRemoteDataSource
import com.example.domain.model.*
import com.example.domain.model.remote.ChannelInfo
import com.example.domain.model.remote.TrendVideoResult
import com.example.domain.model.remote.VideoInfo
import com.example.domain.model.remote.VideoResult
import com.example.domain.repository.YoutubeRepository
import javax.inject.Inject

class YoutubeRepositoryImpl @Inject constructor(
    private val youtubeRemoteDataSource: YoutubeRemoteDataSource
) : YoutubeRepository {

    override suspend fun searchYoutube(searchKeyword: String): ApiResult<VideoResult> {
        return YoutubeMapper.youtubeMapper(youtubeRemoteDataSource.searchYoutube(searchKeyword).body())
    }

    override suspend fun searchYoutubeMore(
        searchKeyword: String,
        nextPageToken: String
    ): ApiResult<VideoResult> {
        return YoutubeMapper.youtubeMapper(youtubeRemoteDataSource.searchYoutubeMore(searchKeyword, nextPageToken).body())
    }

    override suspend fun getYoutubeVideoInfo(videoId: String): ApiResult<VideoInfo> {
        return YoutubeMapper.youtubeInfoMapper(youtubeRemoteDataSource.getYoutubeVideoInfo(videoId).body())
    }

    override suspend fun getYoutubeTrendVideo(): ApiResult<TrendVideoResult> {
        return YoutubeMapper.youtubeTrendMapper(youtubeRemoteDataSource.getTrendYoutubeVideo().body())
    }

    override suspend fun getYoutubeChannelInfo(channelId: String): ApiResult<ChannelInfo> {
        return YoutubeMapper.youtubeChannelMapper(youtubeRemoteDataSource.getYoutubeChannelInfo(channelId).body())
    }

}