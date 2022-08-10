package kr.co.js.youtube_api.domain.search

import kotlinx.coroutines.coroutineScope
import kr.co.js.youtube_api.data.model.SearchRequestParams
import kr.co.js.youtube_api.data.model.SearchType
import kr.co.js.youtube_api.data.repository.SearchRepository
import kr.co.js.youtube_api.model.vo.YoutubeVideo
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject
import kr.co.js.youtube_api.data.model.VideoApiResponse as VideoApiResponse

class SearchVideoUseCase @Inject constructor(
    private val repository: SearchRepository
) {

    suspend operator fun invoke(searchRequestParams: SearchRequestParams): Result<YoutubeVideo> {
        return coroutineScope {
            Result.failure(exception = Exception())
            //Result.success(YoutubeVideo("","","","",PageI,""))
            //repository.getYoutubeVideo("")

            /*if (params.type == SearchType.First) {

                val response = repository.getYoutubeVideo(params.searchKeyword)

            } else {
                repository.getYouTubeMoreVideos(params.searchKeyword, params.nextPageToken)
            }*/
        }
    }

}