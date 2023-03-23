package com.example.sampleproject.feature_travel.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleproject.feature_travel.data.source.TravelDataSource
import com.example.sampleproject.feature_travel.data.source.remote.TravelRemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val  testDataSource: TravelDataSource
    // TODO: inject use cases
) : ViewModel() {

    fun getAttractions(){
        viewModelScope.launch {
            Timber.d("estDataSource.getAttractions(3)")
            testDataSource.getAttractions(1)
        }
    }

}