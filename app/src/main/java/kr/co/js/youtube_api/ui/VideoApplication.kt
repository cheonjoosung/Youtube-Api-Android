package kr.co.js.youtube_api.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kr.co.js.youtube_api.model.repository.SearchRepository

@HiltAndroidApp
class VideoApplication : Application() {
    val searchRepository by lazy { SearchRepository() }
}