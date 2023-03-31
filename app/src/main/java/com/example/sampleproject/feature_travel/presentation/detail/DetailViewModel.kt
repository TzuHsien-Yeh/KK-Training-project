package com.example.sampleproject.feature_travel.presentation.detail

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.lifecycle.*
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.Player
import com.bumptech.glide.Glide
import com.example.sampleproject.SampleApplication.Companion.appContext
import com.example.sampleproject.core.ext.toUri
import com.example.sampleproject.feature_travel.domain.model.Attraction
import com.example.sampleproject.feature_travel.domain.usecase.FavoriteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.net.URL
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val favoriteUseCase: FavoriteUseCases,
    var player: Player,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val attraction: Attraction? = savedStateHandle["attractionKey"]

    private val favoriteAttractions: LiveData<List<Attraction>> = favoriteUseCase.getFavoritesUseCase()

    val isFavorite: LiveData<Boolean> = Transformations.map(favoriteAttractions) {
        favoriteAttractions.value?.contains(attraction) ?: false
    }

//    private val _shouldShowThumbnail = MutableLiveData<Boolean>()
//    val shouldShowThumbnail: LiveData<Boolean>
//        get() = _shouldShowThumbnail

    fun initPlayer() {

        Timber.d("initPlayer attraction $attraction")

        attraction?.let {
//            val thumbnail = attraction.image.toByteArray()
//
//            @SuppressLint("UnsafeOptInUsageError")
//            val mediaMetadata = MediaMetadata.Builder().setArtworkData(thumbnail).build()
//            val mediaItem = MediaItem.Builder()
//                .setMediaMetadata(mediaMetadata)
//                .setUri((it.video.toUri()))
//                .build()
//            player.setMediaItem(mediaItem)
            player.setMediaItem(MediaItem.fromUri(it.video.toUri()))

            Timber.d("setMediaItem: ${it.video.toUri()}")
            player.prepare()
        }

//        player.addListener(object : Player.Listener {
//            override fun onPlaybackStateChanged(playbackState: Int) {
//                super.onPlaybackStateChanged(playbackState)
//                when(playbackState) {
//                    Player.STATE_IDLE -> _shouldShowThumbnail.value = true
//                    Player.STATE_BUFFERING -> _shouldShowThumbnail.value = true
//                    else -> _shouldShowThumbnail.value = false
//                }
//            }
//        })
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

    private fun releaseVideoPlayer() {
        player.release()
    }
    override fun onCleared() {
        super.onCleared()
        Timber.d("onCleared")
        releaseVideoPlayer()
    }

}