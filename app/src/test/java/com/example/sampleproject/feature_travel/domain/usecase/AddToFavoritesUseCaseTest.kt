package com.example.sampleproject.feature_travel.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.sampleproject.MainCoroutineRule
import com.example.sampleproject.feature_travel.data.repository.FakeAttractionRepository
import com.example.sampleproject.feature_travel.domain.model.Attraction
import kotlinx.coroutines.runBlocking
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AddToFavoritesUseCaseTest {

    private lateinit var addToFavoritesUseCase: AddToFavoritesUseCase
    private lateinit var fakeRepository: FakeAttractionRepository

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        fakeRepository = FakeAttractionRepository()
        addToFavoritesUseCase = AddToFavoritesUseCase(fakeRepository)
    }

    @Test
    fun addAttractionToFavorites_attractionIsInFavorites () {
        val attraction = Attraction(12, "test", "a", "b", "c", "d")

        runBlocking { addToFavoritesUseCase(attraction) }

        assertThat(fakeRepository.favorites).contains(attraction)
    }

}