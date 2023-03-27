package com.example.sampleproject.feature_travel.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.Transformations
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

    private val favoriteAttractions: LiveData<List<Attraction>> = favoriteUseCase.getFavoritesUseCase()

    val isFavorite: LiveData<Boolean> = Transformations.map(favoriteAttractions) {
        favoriteAttractions.value?.contains(attraction) ?: false
    }

    fun toggleFavoriteState(isFavoriteNow: Boolean) {
        viewModelScope.launch {
            attraction?.let {
                if (isFavoriteNow) {
                    favoriteUseCase.deleteFromFavoritesUseCase(it)
                } else {
                    favoriteUseCase.addToFavoritesUseCase(it)
                }
            }
        }

    }

}