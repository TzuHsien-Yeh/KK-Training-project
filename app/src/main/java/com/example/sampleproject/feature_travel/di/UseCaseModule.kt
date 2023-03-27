package com.example.sampleproject.feature_travel.di

import com.example.sampleproject.feature_travel.data.source.TravelDataSource
import com.example.sampleproject.feature_travel.data.source.local.AttractionDao
import com.example.sampleproject.feature_travel.data.source.local.TravelLocalDataSource
import com.example.sampleproject.feature_travel.domain.repository.TravelRepository
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
        travelRepository: TravelRepository
    ): FavoriteUseCases = FavoriteUseCases(
        GetFavoritesUseCase(travelRepository),
        AddToFavoritesUseCase(travelRepository),
        DeleteFromFavoritesUseCase(travelRepository)
    )

}