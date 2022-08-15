package com.example.presentation.trend

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.ApiResult
import com.example.domain.model.remote.VideoInfo
import com.example.domain.usecase.remote.ChannelInfoUseCase
import com.example.domain.usecase.remote.TrendVideoUseCase
import com.example.presentation.search.SearchVideoViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class TrendVideoViewModel @Inject constructor(
    private val trendVideoUseCase: TrendVideoUseCase,
    private val channelInfoUseCase: ChannelInfoUseCase
) : ViewModel() {

    companion object {
        val TAG: String = TrendVideoViewModel::class.java.simpleName
    }

    val trendVideoList = MutableLiveData<List<VideoInfo>>()

    fun getTrendVideos() = viewModelScope.launch {

        trendVideoUseCase.invoke().run {

            when (this) {
                is ApiResult.Success -> {
                    Log.e(TAG, "Success Trend Video")

                    val searchResultList = mutableListOf<VideoInfo>()
                    searchResultList.addAll(this.value.videoList)

                    coroutineScope {

                        (searchResultList.indices).map { idx ->

                            async(Dispatchers.IO) {
                                channelInfoUseCase.invoke(searchResultList[idx].channelId)
                                    .run {
                                        when (this) {
                                            is ApiResult.Success -> {

                                                this.value.let { videoInfo ->
                                                    Log.e(
                                                        SearchVideoViewModel.TAG,
                                                        "Success ChannelInfo"
                                                    )

                                                    searchResultList.find { video ->
                                                        video.videoId == searchResultList[idx].videoId
                                                    }?.let { findVideo ->
                                                        findVideo.channelImgUrl =
                                                            videoInfo.imgUrl
                                                    }

                                                }
                                            }

                                            else -> {
                                                Log.e(
                                                    SearchVideoViewModel.TAG,
                                                    "onFailure ChannelInfo"
                                                )
                                            }
                                        }
                                    }
                            }

                        }.awaitAll()

                        trendVideoList.postValue(searchResultList)
                    }
                }

                else -> {
                    Log.e(TAG, "Fail Trend Video")
                }
            }
        }
    }

}