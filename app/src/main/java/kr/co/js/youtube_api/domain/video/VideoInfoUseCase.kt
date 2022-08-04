package kr.co.js.youtube_api.domain.video

import kotlinx.coroutines.coroutineScope
import kr.co.js.youtube_api.data.repository.SearchRepository
import kr.co.js.youtube_api.model.vo.YoutubeVideo
import retrofit2.Response
import javax.inject.Inject

class VideoInfoUseCase @Inject constructor(
    private val repository: SearchRepository
) {

    suspend operator fun invoke(savedKeyword: String, nextPageToken: String): Response<YoutubeVideo> {
        return coroutineScope {
            repository.getYoutubeVideo("")
        }
    }

}