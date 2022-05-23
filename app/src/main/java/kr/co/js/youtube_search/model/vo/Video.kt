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
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "publishedAt")
    val publishedAt: String,

    @ColumnInfo(name = "imgUrl")
    val imgUrl: String,

    @ColumnInfo(name = "duration")
    var duration: String = "0",

    @ColumnInfo(name = "channelTitle")
    val channelTitle: String,

    @ColumnInfo(name = "viewCount")
    var viewCount: String = "0"
) : Parcelable