package com.assignment.domain.usecases

import com.assignment.domain.APIResult
import com.assignment.domain.fakes.FakeData
import com.assignment.domain.repository.DisneyRepository
import io.mockk.coEvery
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetDisneyCharactersListUseCaseTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    private val disneyRepository: DisneyRepository = mockk()

    private lateinit var getDisneyCharactersListUseCase: GetDisneyCharactersListUseCase

    private lateinit var fakeData: FakeData

    @Before
    fun setUp() {
        fakeData = FakeData()
        getDisneyCharactersListUseCase = GetDisneyCharactersListUseCase(disneyRepository)
    }

    @Test
    fun `GIVEN disney repository WHEN calls getCharactersList THEN returns characters entity list as success`() {

        runTest {

            // ARRANGE
            val characterListEntity = fakeData.getCharacterListEntity()
            coEvery { disneyRepository.getDisneyCharactersList() } returns APIResult.Success(
                characterListEntity
            )

            //ACT
            val result = getDisneyCharactersListUseCase()

            //ASSERT
            assertTrue(result is APIResult.Success)
            assertEquals(characterListEntity, (result as APIResult.Success).data)

        }
    }

    @Test
    fun `GIVEN disney repository WHEN calls getCharactersList THEN throws exception as error`() {

        runTest {

            // ARRANGE
            coEvery { disneyRepository.getDisneyCharactersList() } returns APIResult.Error(
                ERROR_CODE, ERROR_MESSAGE
            )

            //ACT
            val result = getDisneyCharactersListUseCase()

            //ASSERT
            assertTrue(result is APIResult.Error)
            assertEquals(ERROR_MESSAGE, (result as APIResult.Error).errorMessage)

        }
    }

    private companion object {
        const val ERROR_CODE = 1
        const val ERROR_MESSAGE = "Something went wrong! Please try again!"
    }

}