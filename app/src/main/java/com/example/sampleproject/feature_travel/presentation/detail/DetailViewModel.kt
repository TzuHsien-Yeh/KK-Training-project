package com.example.sampleproject.feature_travel.presentation.detail

import android.content.ContentResolver
import android.net.Uri
import android.view.View
import androidx.lifecycle.*
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import com.example.sampleproject.R
import com.example.sampleproject.SampleApplication
import com.example.sampleproject.core.ext.loadImage
import com.example.sampleproject.core.ext.toUri
import com.example.sampleproject.feature_travel.domain.model.Attraction
import com.example.sampleproject.feature_travel.domain.usecase.FavoriteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val favoriteUseCase: FavoriteUseCases,
    val player: Player,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val attraction: Attraction? = savedStateHandle["attractionKey"]

    private val favoriteAttractions: LiveData<List<Attraction>> = favoriteUseCase.getFavoritesUseCase()

    val isFavorite: LiveData<Boolean> = Transformations.map(favoriteAttractions) {
        favoriteAttractions.value?.contains(attraction) ?: false
    }

    fun initPlayer() {

        Timber.d("initPlayer attraction $attraction")

        attraction?.let {
            player.setMediaItem(MediaItem.fromUri(it.video.toUri()))
            Timber.d("setMediaItem: ${it.video.toUri()}")
            player.prepare()
        }

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

    fun releaseVideoPlayer() {
        player.stop()
        player.release()
    }
    override fun onCleared() {
        super.onCleared()
        Timber.d("onCleared")
        releaseVideoPlayer()
    }

}