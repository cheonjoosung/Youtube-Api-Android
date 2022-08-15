package com.example.domain.usecase.remote

import com.example.domain.model.ApiResult
import com.example.domain.model.remote.ChannelInfo
import com.example.domain.repository.YoutubeRepository
import javax.inject.Inject

class ChannelInfoUseCase @Inject constructor(
    private val youtubeRepository: YoutubeRepository
) {

    suspend operator fun invoke(channelId: String): ApiResult<ChannelInfo> {
        return youtubeRepository.getYoutubeChannelInfo(channelId)
    }

}