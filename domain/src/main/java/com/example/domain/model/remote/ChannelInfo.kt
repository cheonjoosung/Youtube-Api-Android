package com.example.domain.model.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChannelInfo(

    val title: String,
    val description: String,
    val imgUrl: String

) : Parcelable
