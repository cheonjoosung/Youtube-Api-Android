package com.example.domain.model.remote

data class TrendVideoResult(
    val videoList: MutableList<VideoInfo> = mutableListOf()
)