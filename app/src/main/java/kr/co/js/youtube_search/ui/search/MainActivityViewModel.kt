import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.*
import kr.co.js.youtube_search.model.repository.SearchRepository
import kr.co.js.youtube_search.model.vo.Video

class MainActivityViewModel(
    private val searchRepository: SearchRepository
) : ViewModel() {

    companion object {
        val TAG = MainActivityViewModel::class.java.simpleName
    }

    val allSearchResult = searchRepository.allSearchResult.asLiveData()

    val firstSearch = MutableLiveData<List<Video>>()
    val moreSearch = mutableListOf<Video>()

    var nextPageToken = ""

    fun getYoutubeVideo(keyword: String) = viewModelScope.launch {
        val result = searchRepository.getYoutubeVideo(keyword)

        when (result.code()) {
            200 -> {

                /**
                 * Youtube Search API Do not return viewCount & Duration
                 * So,
                 */
                val idList = mutableListOf<String>()
                val convertedList = mutableListOf<Video>()

                result.body()?.let {

                    nextPageToken = it.nextPageToken

                    it.items?.let { list ->
                        for (item in list) {

                            val id = item.id
                            val snippet = item.snippet

                            convertedList.add(
                                Video(
                                    videoId = id.videoId,
                                    title = snippet.title,
                                    description = snippet.description,
                                    publishedAt = snippet.publishedAt,
                                    imgUrl = snippet.thumbnails.medium.url,
                                    channelTitle = snippet.channelTitle
                                )
                            )

                            idList.add(id.videoId)
                        }
                    }

                }

                coroutineScope {
                    (0 until idList.size).map { idx ->
                        async(Dispatchers.IO) {
                            val resultInfo = searchRepository.requestVideosInfo(idList[idx])

                            when (resultInfo.code()) {
                                200 -> {
                                    resultInfo.body()?.let {
                                        convertedList.find { video ->
                                            video.videoId == idList[idx]
                                        }?.let { findVideo ->
                                            it.items?.let { list ->
                                                for (item in list) {
                                                    findVideo.duration =
                                                        item.contentDetails.duration
                                                    findVideo.duration =
                                                        item.statistics.viewCount.toString()
                                                }

                                            }
                                        }
                                    }
                                }
                                else -> {
                                    Log.e(TAG, "onFailure ${resultInfo.message()}")
                                }
                            }
                        }
                    }
                }.awaitAll()

                firstSearch.postValue(convertedList)
            }
            else -> {
                Log.e(TAG, "onFailure ${result.message()}")
            }
        }


    }

    fun getYoutubeVideoMore(keyword: String) {

    }
}

class MainActivityViewModelFactory(
    private val searchRepository: SearchRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainActivityViewModel(searchRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}