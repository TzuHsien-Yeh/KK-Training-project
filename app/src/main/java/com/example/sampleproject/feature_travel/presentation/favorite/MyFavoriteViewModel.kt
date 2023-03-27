package com.example.sampleproject.feature_travel.presentation.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleproject.feature_travel.domain.model.Attraction
import com.example.sampleproject.feature_travel.domain.usecase.FavoriteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyFavoriteViewModel @Inject constructor(
    private val favoriteUseCase: FavoriteUseCases
) : ViewModel() {

    val favoriteAttractions: LiveData<List<Attraction>> = favoriteUseCase.getFavoritesUseCase()

    val favoriteUiState = FavoriteUiState(
        onFavoriteBtnClick = {
            toggleFavoriteState(it)
        },
        onAttractionClick = {
            navigateToDetail(it)
        }
    )

    private val _navigateToDetail = MutableLiveData<Attraction?>()
    val navigateToDetail: LiveData<Attraction?>
        get() = _navigateToDetail

    fun toggleFavoriteState(attraction: Attraction) {
        viewModelScope.launch {
            val isCurrentlyFavorite = favoriteAttractions.value?.contains(attraction) ?: false

            if (isCurrentlyFavorite) {
                favoriteUseCase.deleteFromFavoritesUseCase(attraction)
            } else {
                favoriteUseCase.addToFavoritesUseCase(attraction)
            }
        }
    }

    private fun navigateToDetail(attraction: Attraction){
        _navigateToDetail.value = attraction
    }

    fun doneNavigating(){
        _navigateToDetail.value = null
    }
}

data class FavoriteUiState(
    val onFavoriteBtnClick: (Attraction) -> Unit,
    val onAttractionClick: (Attraction) -> Unit
)