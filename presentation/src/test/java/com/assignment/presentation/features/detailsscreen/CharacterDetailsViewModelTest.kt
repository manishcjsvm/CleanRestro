package com.assignment.presentation.features.detailsscreen

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.assignment.domain.common.APIResult
import com.assignment.domain.usecases.GetDisneyCharacterDetailsUseCase
import com.assignment.presentation.fakes.FakeData
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


    private val getDisneyCharacterDetailsUseCaseMock: GetDisneyCharacterDetailsUseCase = mockk()

    private val savedStateHandleMock: SavedStateHandle = mockk()

    private val fakeData: FakeData = FakeData()
    private val characterEntity = fakeData.getCharacterEntity()


    @Before
    fun setUp() {
        coEvery { savedStateHandleMock.get<Int>(CHARACTER_ID) } returns ID
    }

    @Test
    fun `GIVEN intent LoadData WHEN call sendIntent THEN returns success view state`() =
        runTest {

            coEvery { getDisneyCharacterDetailsUseCaseMock(ID) } returns APIResult.Success(
                characterEntity
            )
            val characterDetailsViewModel = CharacterDetailsViewModel(
                savedStateHandleMock,
                getDisneyCharacterDetailsUseCaseMock,
                testDispatcherRule.getDispatcher()
            )
            characterDetailsViewModel.stateFlow.test {
                assertTrue(awaitItem() is CharacterDetailsViewState.Success)
            }
        }

    @Test
    fun `GIVEN intent LoadData WHEN call sendIntent THEN returns error view state`() = runTest {

        coEvery { getDisneyCharacterDetailsUseCaseMock(id = any()) } returns APIResult.Error(
            STATUS_CODE,
            ERROR_MESSAGE
        )
        val characterDetailsViewModel = CharacterDetailsViewModel(
            savedStateHandleMock,
            getDisneyCharacterDetailsUseCaseMock,
            testDispatcherRule.getDispatcher()
        )

        characterDetailsViewModel.stateFlow.test {
            assertTrue(awaitItem() is CharacterDetailsViewState.Error)
        }
    }


    private companion object {
        const val ID = 1
        const val CHARACTER_ID = "characterId"
        const val ERROR_MESSAGE = "Network Error!"
        const val STATUS_CODE = 1
    }
}