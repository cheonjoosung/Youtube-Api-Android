package com.example.domain.model

data class VideoResult(
    var nextPageToken: String = "",
    val videoList: MutableList<Video> = mutableListOf()
)