package com.assignment.presentation.features.detailsscreen

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.assignment.domain.APIResult
import com.assignment.domain.usecases.GetDisneyCharacterDetailsUseCase
import com.assignment.presentation.fakes.FakeData
import com.assignment.presentation.mappers.CharacterMapper
import com.assignment.presentation.rules.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
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


    private val getDisneyCharacterDetailsUseCase: GetDisneyCharacterDetailsUseCase = mockk()


    private val characterMapper: CharacterMapper = mockk()

    private val savedStateHandle: SavedStateHandle = mockk(relaxed = true)


    @Before
    fun setUp() {
        val characterEntity = FakeData.getCharacterEntity()
        val character = FakeData.getCharacter()
        coEvery { savedStateHandle.get<Int>(CHARACTER_ID) } returns ID
        coEvery { characterMapper.map(characterEntity) } returns character
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
                    sendIntent(CharacterDetailsLoadDataViewIntent(id = ID))
                    assertTrue(awaitItem() is CharacterDetailsViewState.Success)
                }

            }
        }

    }

    @Test
    fun `GIVEN intent to load data WHEN call sendIntent THEN returns error`() {
        runTest {

            coEvery { getDisneyCharacterDetailsUseCase(id = ID) } answers {
                APIResult.Error(STATUS_CODE, ERROR_MESSAGE)
            }
            characterDetailsViewModel.run {
                stateFlow.test {
                    sendIntent(CharacterDetailsLoadDataViewIntent(id = ID))
                    assertTrue(awaitItem() is CharacterDetailsViewState.Success)
                    assertTrue(awaitItem() is CharacterDetailsViewState.Error)
                }
            }
        }

    }


    private companion object {
        const val ID = 1
        const val CHARACTER_ID = "characterId"
        const val ERROR_MESSAGE = "Network Error!"
        const val STATUS_CODE = 1
    }
}