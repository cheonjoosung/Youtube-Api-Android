package com.example.presentation.trend

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.ApiResult
import com.example.domain.model.Video
import com.example.domain.model.VideoInfo
import com.example.domain.usecase.TrendVideoUseCase
import com.example.presentation.search.SearchVideoViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendVideoViewModel @Inject constructor(
    private val trendVideoUseCase: TrendVideoUseCase
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
                    trendVideoList.postValue(this.value.videoList)
                }

                else -> {
                    Log.e(TAG, "Fail Trend Video")
                }
            }
        }
    }

}