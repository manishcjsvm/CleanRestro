package com.assignment.presentation.features.homescreen

import app.cash.turbine.test
import com.assignment.common.APIResult
import com.assignment.domain.usecases.GetDisneyCharactersListUseCase
import com.assignment.presentation.fakes.FakeData
import com.assignment.presentation.mappers.CharacterListMapper
import com.assignment.presentation.rules.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
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

class CharactersListViewModelTest {


    @get:Rule
    val mockkRule = MockKRule(this)

    @get:Rule
    val testDispatcherRule = MainDispatcherRule()

    private lateinit var charactersListViewModel: CharactersListViewModel

    @MockK
    private lateinit var getDisneyCharactersListUseCase: GetDisneyCharactersListUseCase

    @MockK
    private lateinit var characterListMapper: CharacterListMapper


    @Before
    fun setUp() {


        val charactersListEntity = FakeData.getCharactersListEntity()
        val charactersList = FakeData.getCharactersList()

        coEvery { getDisneyCharactersListUseCase() } returns APIResult.Success(
            charactersListEntity
        )
        coEvery { characterListMapper.mapToCharacterList(charactersListEntity) } returns charactersList

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
                APIResult.Error(Exception(EXCEPTION_MESSAGE))
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
                    assertTrue(awaitItem() is CharactersListSideEffect.NavigateToCharacterDetails)
                }
            }

        }
    }

    companion object {
        const val EXCEPTION_MESSAGE = "Network Exception!"
        const val ID = 1
    }
}