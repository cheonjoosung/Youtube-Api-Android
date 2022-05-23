package kr.co.js.youtube_search.model.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Object For Retrofit
 */
object YoutubeApiRequestFactory {

    private const val youtubeBaseUrl = "https://www.googleapis.com/youtube/v3/"

    val retrofit: YoutubeApiService = Retrofit.Builder()
        .baseUrl(youtubeBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(
            OkHttpClient.Builder().addInterceptor(
                HttpLoggingInterceptor().apply { this.level = HttpLoggingInterceptor.Level.BODY }
            ).build())
        .build()
        .create(YoutubeApiService::class.java)
}