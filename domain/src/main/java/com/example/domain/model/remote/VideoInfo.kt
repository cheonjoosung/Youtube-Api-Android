package com.example.domain.model.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoInfo(

    val videoId: String,

    val title: String,

    val description: String,

    val publishedAt: String,

    val imgUrl: String,

    var duration: String = "0",

    val channelTitle: String,

    var viewCount: String = "0",

    val channelId: String,

    var channelImgUrl: String = ""
) : Parcelable
