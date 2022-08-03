import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.*
import kr.co.js.youtube_api.model.repository.SearchRepository
import kr.co.js.youtube_api.model.vo.Video

class MainActivityViewModel : ViewModel() {

    companion object {
        val TAG = MainActivityViewModel::class.java.simpleName
    }

}

class MainActivityViewModelFactory(
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainActivityViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}