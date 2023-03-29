package com.example.sampleproject.feature_travel.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.sampleproject.MainCoroutineRule
import com.example.sampleproject.feature_travel.data.repository.FakeAttractionRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DeleteFromFavoritesUseCaseTest {

    private lateinit var deleteFromFavoritesUseCase: DeleteFromFavoritesUseCase
    private lateinit var fakeRepository: FakeAttractionRepository

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        fakeRepository = FakeAttractionRepository()
        deleteFromFavoritesUseCase = DeleteFromFavoritesUseCase(fakeRepository)
    }

    @Test
    fun deleteFromFavorites_attractionRemovedFromFavorites() {
        val attractionToDelete = fakeRepository.favorites.first()

        runBlocking { deleteFromFavoritesUseCase(attractionToDelete) }

        assertThat(fakeRepository.favorites.contains(attractionToDelete)).isFalse()
    }
}
