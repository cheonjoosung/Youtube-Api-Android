package com.example.domain.usecase.remote

import com.example.domain.model.ApiResult
import com.example.domain.model.remote.TrendVideoResult
import com.example.domain.repository.YoutubeRepository
import javax.inject.Inject

class TrendVideoUseCase @Inject constructor(
    private val youtubeRepository: YoutubeRepository
) {

    suspend operator fun invoke(): ApiResult<TrendVideoResult> {
        return youtubeRepository.getYoutubeTrendVideo()
    }

}