package kr.co.js.youtube_search.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kr.co.js.youtube_search.model.repository.SearchRepository

@HiltAndroidApp
class VideoApplication : Application() {
    val searchRepository by lazy { SearchRepository() }
}