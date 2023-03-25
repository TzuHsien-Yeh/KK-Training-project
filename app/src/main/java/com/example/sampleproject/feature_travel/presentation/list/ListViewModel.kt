package com.example.sampleproject.feature_travel.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleproject.core.util.Resource
import com.example.sampleproject.feature_travel.data.source.TravelDataSource
import com.example.sampleproject.feature_travel.data.source.remote.TravelRemoteDataSource
import com.example.sampleproject.feature_travel.domain.model.Attraction
import com.example.sampleproject.feature_travel.domain.usecase.GetAttractionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val  getAttractionsUseCase: GetAttractionsUseCase
) : ViewModel() {

    private val fetchedData = mutableListOf<Attraction>()

    private val _attractionList = MutableLiveData<List<Attraction>?>()
    val attractionList: LiveData<List<Attraction>?>
        get() = _attractionList

    private val _errorMsg = MutableLiveData<String?>(null)
    val errorMsg: LiveData<String?>
        get() = _errorMsg

    fun getAttractions(){
        viewModelScope.launch {

            when (val attractionsResult = getAttractionsUseCase(page = 1)){
                is Resource.Success -> {

                    attractionsResult.data?.let { fetchedData.addAll(it) }

                    Timber.d("attractionsResult.data  ${attractionsResult.data}")
                }
                is Resource.Error -> {
                    // Enum class defining error msg to be shown accordingly
                    _errorMsg.value = attractionsResult.message
                }
            }

            _attractionList.value = fetchedData
        }
    }

}