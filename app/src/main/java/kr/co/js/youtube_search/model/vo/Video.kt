package kr.co.js.youtube_search.model.vo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Video(

    val videoId: String,

    val title: String,

    val description: String,

    val publishedAt: String,

    val imgUrl: String,

    var duration: String = "0",

    val channelTitle: String,

    var viewCount: String = "0"
) : Parcelable