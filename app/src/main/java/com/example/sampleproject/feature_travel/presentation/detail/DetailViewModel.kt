package com.example.sampleproject.feature_travel.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.sampleproject.feature_travel.domain.model.Attraction
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val attraction: Attraction? = savedStateHandle["attractionKey"]
}