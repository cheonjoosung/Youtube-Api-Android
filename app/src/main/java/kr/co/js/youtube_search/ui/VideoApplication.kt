package kr.co.js.youtube_search.ui

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kr.co.js.youtube_search.model.db.VideoDatabase
import kr.co.js.youtube_search.model.repository.SearchRepository

class VideoApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { VideoDatabase.getDatabase(this, applicationScope) }

    val searchRepository by lazy { SearchRepository(database.searchDao()) }
}