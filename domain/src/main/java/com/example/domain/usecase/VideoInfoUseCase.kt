package com.example.domain.usecase

import com.example.domain.model.ApiResult
import com.example.domain.model.VideoInfo
import com.example.domain.repository.YoutubeRepository
import javax.inject.Inject

class VideoInfoUseCase @Inject constructor(
    private val youtubeRepository: YoutubeRepository
) {

    suspend operator fun invoke(videoId: String): ApiResult<VideoInfo> {
        return youtubeRepository.getYoutubeVideoInfo(videoId)
    }

}