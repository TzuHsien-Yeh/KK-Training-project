package com.example.sampleproject.feature_travel.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.sampleproject.MainCoroutineRule
import com.example.sampleproject.feature_travel.data.repository.FakeAttractionRepository
import com.example.sampleproject.feature_travel.domain.model.Attraction
import com.example.sampleproject.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetFavoritesUseCaseTest {

    private lateinit var getFavoritesUseCase: GetFavoritesUseCase
    private lateinit var fakeRepository: FakeAttractionRepository

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        fakeRepository = FakeAttractionRepository()
        getFavoritesUseCase = GetFavoritesUseCase(fakeRepository)
    }

    @Test
    fun getAllAttractionsInFavorites_returnsAllFavoriteAttractionsAndMarkThemWithFavorite () {
        val predicate: (Attraction) -> Boolean = { it.isFavorite }
        assertThat(getFavoritesUseCase().getOrAwaitValue()).isEqualTo(fakeRepository.favorites)
        assertThat(getFavoritesUseCase().getOrAwaitValue().all(predicate)).isTrue()
    }

}