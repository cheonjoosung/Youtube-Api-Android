package com.example.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChannelInfo(

    val title: String,
    val description: String,
    val imgUrl: String

) : Parcelable
