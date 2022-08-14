package com.example.domain.model.kr.co.js.youtube_api.di

import com.example.data.service.YoutubeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val youtubeBaseUrl = "https://www.googleapis.com/youtube/v3/"

    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(youtubeBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(
                OkHttpClient.Builder().addInterceptor(
                    HttpLoggingInterceptor().apply {
                        this.level = HttpLoggingInterceptor.Level.BODY
                    }
                ).build())
            .build()
    }

    @Provides
    @Singleton
    fun provideYoutubeApiService(retrofit: Retrofit): YoutubeApiService { // YoutubeAPIApi interface 의존성 주입
        return retrofit.create(YoutubeApiService::class.java)
    }
}
/*
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
 */