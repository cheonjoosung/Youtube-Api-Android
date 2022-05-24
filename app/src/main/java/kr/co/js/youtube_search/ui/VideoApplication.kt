package kr.co.js.youtube_search.ui

import android.app.Application
import kr.co.js.youtube_search.model.repository.SearchRepository

class VideoApplication : Application() {
    val searchRepository by lazy { SearchRepository() }
}