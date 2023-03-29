package com.example.sampleproject.feature_travel.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.sampleproject.MainCoroutineRule
import com.example.sampleproject.feature_travel.data.repository.FakeAttractionRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class GetAttractionsUseCaseTest {
    private lateinit var getAttractionUseCase: GetAttractionsUseCase
    private lateinit var fakeRepository: FakeAttractionRepository

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        fakeRepository = FakeAttractionRepository()
        getAttractionUseCase = GetAttractionsUseCase(fakeRepository)
    }

    @Test
    fun getAttractions_networkError_getsErrorMsg() {
        // Given the situation of network error
        fakeRepository.setShouldReturnNetworkError(true)

        val result = runBlocking { getAttractionUseCase() }

        assertThat(result.message).isEqualTo(fakeRepository.networkErrorMsg)
        assertThat(result.data).isEqualTo(null)
    }

    @Test
    fun getAttractions_successfullyRetrievedData_returnsAttractions() {
        val result = runBlocking { getAttractionUseCase() }

        assertThat(result.message).isNull()
        assertThat(result.data).isEqualTo(fakeRepository.attractionList)
        assertThat(result.data?.total).isEqualTo(fakeRepository.attractionList.total)
    }

}