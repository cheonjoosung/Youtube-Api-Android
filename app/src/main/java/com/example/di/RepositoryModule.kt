package com.example.di

import com.example.data.YoutubeRepositoryImpl
import com.example.data.service.YoutubeApiService
import com.example.data.source.YoutubeRemoteDataSource
import com.example.data.source.YoutubeRemoteDataSourceImpl
import com.example.domain.repository.YoutubeRepository
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
object RepositoryModule {

    @Singleton
    @Provides
    fun providesYoutubeRepository(repository: YoutubeRepositoryImpl): YoutubeRepository {
        return repository
    }
}