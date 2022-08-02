package kr.co.js.youtube_search.domain.youtube

import kr.co.js.youtube_search.model.repository.SearchRepository
import javax.inject.Inject

class YoutubeBaseUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {

}