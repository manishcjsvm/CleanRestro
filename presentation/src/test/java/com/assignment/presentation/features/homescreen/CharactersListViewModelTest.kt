package com.assignment.presentation.features.homescreen

import app.cash.turbine.test
import com.assignment.domain.APIResult
import com.assignment.domain.usecases.GetDisneyCharactersListUseCase
import com.assignment.presentation.fakes.FakeData
import com.assignment.presentation.mappers.CharacterListMapper
import com.assignment.presentation.rules.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharactersListViewModelTest {


    @get:Rule
    val mockkRule = MockKRule(this)

    @get:Rule
    val testDispatcherRule = MainDispatcherRule()

    private lateinit var charactersListViewModel: CharactersListViewModel

    private val getDisneyCharactersListUseCase: GetDisneyCharactersListUseCase = mockk()

    private val characterListMapper: CharacterListMapper =
        mockk() // Do it like this only, memory point of view.


    @Before
    fun setUp() {

        val charactersListEntity = FakeData.getCharactersListEntity()
        val charactersList = FakeData.getCharactersList()

        coEvery { getDisneyCharactersListUseCase() } returns APIResult.Success(
            charactersListEntity
        )
        coEvery { characterListMapper.map(charactersListEntity) } returns charactersList

        charactersListViewModel =
            CharactersListViewModel(
                getDisneyCharactersListUseCase,
                characterListMapper,
                testDispatcherRule.getDispatcher()
            )
    }

    @Test
    fun `GIVEN intent WHEN call getCharactersList THEN returns success`() {
        runTest {

            charactersListViewModel.run {
                stateFlow.test {
                    sendIntent(CharacterListViewIntent.LoadData)
                    assertTrue(awaitItem() is CharacterListViewState.Success)
                }
            }

        }
    }

    @Test
    fun `GIVEN intent to load data WHEN call sendIntent THEN returns error`() {
        runTest {

            coEvery { getDisneyCharactersListUseCase() } answers {
                APIResult.Error(STATUS_CODE, ERROR_MESSAGE)
            }
            charactersListViewModel.run {
                stateFlow.test {
                    sendIntent(CharacterListViewIntent.LoadData)
                    assertTrue(awaitItem() is CharacterListViewState.Success)
                    assertTrue(awaitItem() is CharacterListViewState.Error)
                }
            }
        }

    }

    @Test
    fun `GIVEN intent OnItemClicked WHEN call sendIntent THEN navigates to details screen`() {
        runTest {


            charactersListViewModel.run {

                sideEffectFlow.test {
                    sendIntent(CharacterListViewIntent.OnItemClicked(id = ID))
                    assertEquals(awaitItem().id, ID)
                }
            }

        }
    }

    private companion object {
        const val STATUS_CODE = 1
        const val ERROR_MESSAGE = "Network Error!"
        const val ID = 1
    }
}