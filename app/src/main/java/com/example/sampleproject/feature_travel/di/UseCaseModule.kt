package com.example.sampleproject.feature_travel.di

import com.example.sampleproject.feature_travel.domain.repository.AttractionRepository
import com.example.sampleproject.feature_travel.domain.usecase.AddToFavoritesUseCase
import com.example.sampleproject.feature_travel.domain.usecase.DeleteFromFavoritesUseCase
import com.example.sampleproject.feature_travel.domain.usecase.FavoriteUseCases
import com.example.sampleproject.feature_travel.domain.usecase.GetFavoritesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideFavoriteUseCase(
        attractionRepository: AttractionRepository
    ): FavoriteUseCases = FavoriteUseCases(
        GetFavoritesUseCase(attractionRepository),
        AddToFavoritesUseCase(attractionRepository),
        DeleteFromFavoritesUseCase(attractionRepository)
    )

}