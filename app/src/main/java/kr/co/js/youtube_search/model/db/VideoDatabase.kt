package kr.co.js.youtube_search.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kr.co.js.youtube_search.model.dao.SearchDao
import kr.co.js.youtube_search.model.vo.Video

@Database(
    entities = [Video::class],
    version = 1,
    exportSchema = false
)
abstract class VideoDatabase : RoomDatabase() {

    abstract fun searchDao(): SearchDao

    companion object {

        @Volatile
        private var INSTANCE: VideoDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): VideoDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VideoDatabase::class.java,
                    "media_downloader"
                )
                    //.addCallback(VideoDatabaseCallback(scope))
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }

    private class VideoDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            INSTANCE?.let { database ->
                scope.launch {
                    initDatabase(database.searchDao())
                }
            }
        }

        suspend fun initDatabase(searchDao: SearchDao) {
            /*
                Init시 하고 싶은것
                searchDao.deleteAll()
             */
        }
    }
}