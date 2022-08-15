package com.example.domain.model.remote

data class VideoResult(
    var nextPageToken: String = "",
    val videoList: MutableList<Video> = mutableListOf()
)