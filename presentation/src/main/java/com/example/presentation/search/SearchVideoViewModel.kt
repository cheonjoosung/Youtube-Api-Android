package com.example.presentation.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Video
import com.example.domain.usecase.SearchVideoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchVideoViewModel @Inject constructor(
    private val searchVideoUseCase: SearchVideoUseCase
) : ViewModel() {

    companion object {
        val TAG: String = SearchVideoViewModel::class.java.simpleName
    }

    val firstSearch = MutableLiveData<List<Video>>()
    val moreSearch = MutableLiveData<List<Video>>()

    private var nextPageToken = ""
    private var savedKeyword = ""

    fun getYoutubeVideo(keyword: String) = viewModelScope.launch {
        savedKeyword = keyword
        searchVideoUseCase.invoke(keyword, "").run {

            if (this.isEmpty()) {
                Log.e(TAG, "searchVideoUseCase Fail")
            } else {
                Log.e(TAG, "searchVideoUseCase Success")
            }
        }

        /*
        val result = searchRepository.getYoutubeVideo(keyword)

        when (result.code()) {
            200 -> {

                /**
                 * Youtube Search API Do not return viewCount & Duration
                 * So, Video API needed after Search API
                 * 검색 API는 조회수&영상길이를 주지 않으므로 비디오 API 를 통해 조회가 필요함
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
                            val resultInfo = searchRepository.requestVideoInfo(idList[idx])

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
                                                    findVideo.viewCount =
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
                    }.awaitAll()

                    firstSearch.postValue(convertedList)
                }

            }
            else -> {
                Log.e(TAG, "onFailure ${result.message()}")
            }
        }*/

    }

    fun getYoutubeVideoMore() = viewModelScope.launch {
        if (nextPageToken.isEmpty() || savedKeyword.isEmpty()) return@launch

        /*val result = searchRepository.getYouTubeMoreVideos(savedKeyword, nextPageToken)

        when (result.code()) {
            200 -> {

                /**
                 * Youtube Search API Do not return viewCount & Duration
                 * So, Video API needed after Search API
                 * 검색 API는 조회수&영상길이를 주지 않으므로 비디오 API 를 통해 조회가 필요함
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
                            val resultInfo = searchRepository.requestVideoInfo(idList[idx])

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
                                                    findVideo.viewCount =
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
                    }.awaitAll()

                    moreSearch.postValue(convertedList)
                }

            }
            else -> {
                Log.e(TAG, "onFailure ${result.message()}")
            }
        }*/

    }
}
