package com.example.domain.usecase

import com.example.domain.model.ApiResult
import com.example.domain.model.TrendVideoResult
import com.example.domain.model.VideoInfo
import com.example.domain.repository.YoutubeRepository
import javax.inject.Inject

class TrendVideoUseCase @Inject constructor(
    private val youtubeRepository: YoutubeRepository
) {

    suspend operator fun invoke(): ApiResult<TrendVideoResult> {
        return youtubeRepository.getYoutubeTrendVideo()
    }

}