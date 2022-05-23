package kr.co.js.youtube_search.model.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kr.co.js.youtube_search.model.vo.Video

@Dao
interface SearchDao {

    @Query("Select * From video")
    fun getAllSearchResult(): Flow<List<Video>>
}