package com.example.sampleproject.feature_travel.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleproject.core.util.Resource
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
    private var total = 0

    private val _attractionList = MutableLiveData<List<Attraction>?>()
    val attractionList: LiveData<List<Attraction>?>
        get() = _attractionList

    private val _errorMsg = MutableLiveData<String?>(null)
    val errorMsg: LiveData<String?>
        get() = _errorMsg

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    val listUiState = ListUiState(
        onAttractionClick = {
            navigateToDetail(it)
        }
    )

    private val _navigateToDetail = MutableLiveData<Attraction?>()
    val navigateToDetail: LiveData<Attraction?>
        get() = _navigateToDetail


    fun getAttractions(){
        viewModelScope.launch {

            when (val attractionsResult = getAttractionsUseCase()){
                is Resource.Success -> {

                    attractionsResult.data?.let {
                        fetchedData.addAll(it.list)
                        total = it.total
                    }

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

    fun loadMoreAttractions() {

        if (hasMoreAttractions()){

            _isLoading.value = true

            viewModelScope.launch {

                when (val attractionsResult = getAttractionsUseCase()){
                    is Resource.Success -> {
                        attractionsResult.data?.let {
                            fetchedData.addAll(it.list)
                            total = it.total
                        }
                        _isLoading.value = false
                    }
                    is Resource.Error -> {
                        _errorMsg.value = attractionsResult.message
                        _isLoading.value = false
                    }
                }

                // Update list to be submitted
                _attractionList.value = fetchedData
            }

        }
    }

    private fun hasMoreAttractions(): Boolean {
        return total > fetchedData.size
    }

    private fun navigateToDetail(attraction: Attraction){
        _navigateToDetail.value = attraction
    }

    fun doneNavigating(){
        _navigateToDetail.value = null
    }
}

data class ListUiState(
    val onAttractionClick: (Attraction) -> Unit
)