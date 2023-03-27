package com.example.sampleproject.feature_travel.domain.usecase

data class FavoriteUseCases(
    val getFavoritesUseCase: GetFavoritesUseCase,
    val addToFavoritesUseCase: AddToFavoritesUseCase,
    val deleteFromFavoritesUseCase: DeleteFromFavoritesUseCase
)