package com.example.di

import com.example.data.service.YoutubeApiService
import com.example.data.source.YoutubeRemoteDataSource
import com.example.data.source.YoutubeRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    fun provideYoutubeRemoteDataSource(
        youtubeApiService: YoutubeApiService
    ): YoutubeRemoteDataSource {
        return YoutubeRemoteDataSourceImpl(
            youtubeApiService
        )
    }
}