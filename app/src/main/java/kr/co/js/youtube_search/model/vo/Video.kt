package kr.co.js.youtube_search.model.vo

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Video(

    @PrimaryKey
    @ColumnInfo(name = "videoId")
    val videoId: String,

    @ColumnInfo(name = "title")
    val title: String
) : Parcelable