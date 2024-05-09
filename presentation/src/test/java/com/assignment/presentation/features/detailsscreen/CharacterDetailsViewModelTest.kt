package com.assignment.presentation.features.detailsscreen

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.assignment.common.APIResult
import com.assignment.domain.usecases.GetDisneyCharacterDetailsUseCase
import com.assignment.presentation.di.MainDispatcher
import com.assignment.presentation.fakes.FakeData
import com.assignment.presentation.features.homescreen.CharactersListViewModelTest
import com.assignment.presentation.mappers.CharacterMapper
import com.assignment.presentation.rules.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterDetailsViewModelTest {


    @get:Rule
    val mockkRule = MockKRule(this)

    @get:Rule
    val testDispatcherRule = MainDispatcherRule()

    private lateinit var characterDetailsViewModel: CharacterDetailsViewModel

    @MockK
    private lateinit var getDisneyCharacterDetailsUseCase: GetDisneyCharacterDetailsUseCase

    @MockK
    private lateinit var characterMapper: CharacterMapper

    @RelaxedMockK // because to get default return values of un-stubbed methods
    private lateinit var savedStateHandle: SavedStateHandle


    @Before
    fun setUp() {
        val characterEntity = FakeData.getCharacterEntity()
        val character = FakeData.getCharacter()
        coEvery { savedStateHandle.get<Int>(characterId) } returns ID
        coEvery { characterMapper.mapToCharacter(characterEntity) } returns character
        coEvery { getDisneyCharacterDetailsUseCase(1) } returns APIResult.Success(characterEntity)

        characterDetailsViewModel = CharacterDetailsViewModel(
            savedStateHandle,
            getDisneyCharacterDetailsUseCase,
            characterMapper, testDispatcherRule.getDispatcher()
        )
    }

    @Test
    fun `GIVEN intent to load data WHEN call sendIntent THEN returns success`() {
        runTest {
            characterDetailsViewModel.run {

                stateFlow.test {
                    sendIntent(CharacterDetailsViewIntent.LoadData(id = ID))
                    assertTrue(awaitItem() is CharacterDetailsViewState.Success)
                }

            }
        }

    }

    @Test
    fun `GIVEN intent to load data WHEN call sendIntent THEN returns error`() {
        runTest {

            coEvery { getDisneyCharacterDetailsUseCase(id = ID) } answers {
                APIResult.Error(Exception(CharactersListViewModelTest.EXCEPTION_MESSAGE))
            }
            characterDetailsViewModel.run {
                stateFlow.test {
                    sendIntent(CharacterDetailsViewIntent.LoadData(id = ID))
                    assertTrue(awaitItem() is CharacterDetailsViewState.Success)
                    assertTrue(awaitItem() is CharacterDetailsViewState.Error)
                }
            }
        }

    }


    companion object {
        const val ID = 1
        const val characterId = "characterId"
    }
}