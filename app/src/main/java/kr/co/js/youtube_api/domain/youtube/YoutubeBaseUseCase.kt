package kr.co.js.youtube_api.domain.youtube

import kr.co.js.youtube_api.model.repository.SearchRepository
import javax.inject.Inject

class YoutubeBaseUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {

}