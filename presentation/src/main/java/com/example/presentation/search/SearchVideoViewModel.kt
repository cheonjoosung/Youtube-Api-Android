package com.example.presentation.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.ApiResult
import com.example.domain.model.Video
import com.example.domain.usecase.SearchVideoUseCase
import com.example.domain.usecase.VideoInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class SearchVideoViewModel @Inject constructor(
    private val searchVideoUseCase: SearchVideoUseCase,
    private val videoInfoUseCase: VideoInfoUseCase
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
        nextPageToken = ""

        searchVideoUseCase.invoke(keyword, "").run {
            when (this) {
                is ApiResult.Success -> {

                    val searchResultList = mutableListOf<Video>()
                    searchResultList.addAll(this.value.videoList)

                    nextPageToken = this.value.nextPageToken

                    Log.e(TAG, "Success ${this.value.videoList.size}")
                    val idList = searchResultList.map { it.videoId }

                    coroutineScope {
                        (idList.indices).map { idx ->
                            async(Dispatchers.IO) {

                                videoInfoUseCase.invoke(idList[idx]).run {
                                    when (this) {
                                        is ApiResult.Success -> {

                                            this.value.let { videoInfo ->
                                                Log.e(TAG, "Success VideoInfo $videoInfo")

                                                searchResultList.find { video ->
                                                    video.videoId == idList[idx]
                                                }?.let { findVideo ->
                                                    findVideo.duration = videoInfo.duration
                                                    findVideo.viewCount = videoInfo.viewCount
                                                }

                                            }
                                        }

                                        else -> {
                                            Log.e(TAG, "onFailure VideoInfo}")
                                        }
                                    }
                                }
                            }
                        }.awaitAll()

                        firstSearch.postValue(searchResultList)
                    }

                }

                is ApiResult.Error -> {
                    Log.e(TAG, "onFailure VideoSearch")
                }

                else -> {
                    Log.e(TAG, "onFailure VideoSearch")
                }
            }
        }

    }

    fun getYoutubeVideoMore() = viewModelScope.launch {
        if (nextPageToken.isEmpty() || savedKeyword.isEmpty()) return@launch

        searchVideoUseCase.invoke(savedKeyword, nextPageToken).run {
            when (this) {
                is ApiResult.Success -> {

                    val searchResultList = mutableListOf<Video>()
                    searchResultList.addAll(this.value.videoList)

                    nextPageToken = this.value.nextPageToken

                    Log.e(TAG, "Success ${this.value.videoList.size}")
                    val idList = searchResultList.map { it.videoId }

                    coroutineScope {
                        (idList.indices).map { idx ->
                            async(Dispatchers.IO) {

                                videoInfoUseCase.invoke(idList[idx]).run {
                                    when (this) {
                                        is ApiResult.Success -> {

                                            this.value.let { videoInfo ->
                                                Log.e(TAG, "Success VideoInfo $videoInfo")

                                                searchResultList.find { video ->
                                                    video.videoId == idList[idx]
                                                }?.let { findVideo ->
                                                    findVideo.duration = videoInfo.duration
                                                    findVideo.viewCount = videoInfo.viewCount
                                                }

                                            }
                                        }

                                        else -> {
                                            Log.e(TAG, "onFailure VideoInfo}")
                                        }
                                    }
                                }
                            }
                        }.awaitAll()

                        moreSearch.postValue(searchResultList)
                    }

                }

                is ApiResult.Error -> {
                    Log.e(TAG, "onFailure VideoSearch")
                }

                else -> {
                    Log.e(TAG, "onFailure VideoSearch")
                }
            }
        }

    }
}
