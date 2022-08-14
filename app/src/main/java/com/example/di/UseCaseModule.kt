package com.example.di

import com.example.domain.repository.YoutubeRepository
import com.example.domain.usecase.SearchVideoUseCase
import com.example.domain.usecase.VideoInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun providesSearchVideoUseCase(repository: YoutubeRepository): SearchVideoUseCase {
        return SearchVideoUseCase(repository)
    }

    @Provides
    fun providesVideoInfoUseCase(repository: YoutubeRepository): VideoInfoUseCase {
        return VideoInfoUseCase(repository)
    }
}