package kr.co.js.youtube_search.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import kr.co.js.youtube_search.model.repository.SearchRepository

class MainActivityViewModel(
    private val searchRepository: SearchRepository
): ViewModel() {

    val allSearchResult = searchRepository.allSearchResult.asLiveData()

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
