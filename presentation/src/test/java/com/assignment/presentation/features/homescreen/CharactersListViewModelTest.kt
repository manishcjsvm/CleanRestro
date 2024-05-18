package com.assignment.presentation.features.homescreen

import app.cash.turbine.test
import com.assignment.domain.common.APIResult
import com.assignment.domain.usecases.GetDisneyCharactersListUseCase
import com.assignment.presentation.fakes.FakeData
import com.assignment.presentation.rules.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class CharactersListViewModelTest {


    @get:Rule
    val mockkRule = MockKRule(this)

    @get:Rule
    val testDispatcherRule = MainDispatcherRule()

    private val getDisneyCharactersListUseCaseMock: GetDisneyCharactersListUseCase = mockk()

    private val fakeData = FakeData()


    @Test
    fun `GIVEN intent LoadData WHEN call getCharactersList returns success view state`() = runTest {
        coEvery { getDisneyCharactersListUseCaseMock() } returns APIResult.Success(fakeData.getCharactersListEntity())

        val charactersListViewModel = CharactersListViewModel(
            getDisneyCharactersListUseCaseMock, testDispatcherRule.getDispatcher()
        )

        charactersListViewModel.stateFlow.test {
            assertTrue(awaitItem() is CharacterListViewState.Success)

        }
    }


    @Test
    fun `GIVEN intent LoadData WHEN call sendIntent THEN returns error view state`() = runTest {

        coEvery { getDisneyCharactersListUseCaseMock() } returns APIResult.Error(
            STATUS_CODE,
            ERROR_MESSAGE
        )
        val charactersListViewModel = CharactersListViewModel(
            getDisneyCharactersListUseCaseMock, testDispatcherRule.getDispatcher()
        )
        charactersListViewModel.stateFlow.test {
            assertTrue(awaitItem() is CharacterListViewState.Error)
        }
    }

    @Test
    fun `GIVEN intent OnItemClicked WHEN call sendIntent THEN navigates to details screen with correct id`() =
        runTest {
            coEvery { getDisneyCharactersListUseCaseMock() } returns APIResult.Success(fakeData.getCharactersListEntity())

            val charactersListViewModel = CharactersListViewModel(
                getDisneyCharactersListUseCaseMock, testDispatcherRule.getDispatcher()
            )

            with(charactersListViewModel) {
                sideEffectFlow.test {
                    sendIntent(CharacterListViewIntent.OnItemClicked(id = ID))
                    assertEquals(awaitItem().id, ID)
                }
            }
        }


    private companion object {
        const val STATUS_CODE = 1
        const val ERROR_MESSAGE = "Network Error!"
        const val ID = 1
    }
}