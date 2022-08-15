package com.example.domain.usecase.remote

import com.example.domain.model.ApiResult
import com.example.domain.model.remote.VideoResult
import com.example.domain.repository.YoutubeRepository
import javax.inject.Inject

class SearchVideoUseCase @Inject constructor(
    private val youtubeRepository: YoutubeRepository
) {

    suspend operator fun invoke(searchKeyword: String, nextPageToken: String): ApiResult<VideoResult> {
        return if (nextPageToken.isEmpty())
            youtubeRepository.searchYoutube(searchKeyword)
        else youtubeRepository.searchYoutubeMore(searchKeyword, nextPageToken)
    }
}