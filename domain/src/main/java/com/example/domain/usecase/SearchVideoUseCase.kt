package com.example.domain.usecase

import com.example.domain.model.Video
import com.example.domain.repository.YoutubeRepository
import javax.inject.Inject

class SearchVideoUseCase @Inject constructor(
    private val youtubeRepository: YoutubeRepository
) {

    suspend operator fun invoke(searchKeyword: String, nextPageToken: String): List<Video> {
        return if (nextPageToken.isEmpty())
            youtubeRepository.searchYoutube(searchKeyword)
        else youtubeRepository.searchYoutubeMore(searchKeyword, nextPageToken)
    }
}