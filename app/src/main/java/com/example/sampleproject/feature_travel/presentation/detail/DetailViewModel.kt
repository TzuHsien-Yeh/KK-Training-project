package com.example.sampleproject.feature_travel.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleproject.feature_travel.domain.model.Attraction
import com.example.sampleproject.feature_travel.domain.usecase.FavoriteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val favoriteUseCase: FavoriteUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val attraction: Attraction? = savedStateHandle["attractionKey"]

    val favoriteAttractions: LiveData<List<Attraction>> = favoriteUseCase.getFavoritesUseCase()


    init {
        attraction?.let { addToFavorites(it) }
        Timber.d("favoriteAttractions = ${favoriteAttractions.value}")
    }


    fun addToFavorites(attraction: Attraction) {
        viewModelScope.launch {
            Timber.d("addToFavorites: $attraction")
            favoriteUseCase.addToFavoritesUseCase(attraction)
        }
    }


}