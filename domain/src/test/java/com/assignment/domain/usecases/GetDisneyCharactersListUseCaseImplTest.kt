package com.assignment.domain.usecases

import com.assignment.common.APIResult
import com.assignment.domain.fakes.FakeData
import com.assignment.domain.repository.DisneyRepository
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetDisneyCharactersListUseCaseImplTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    private lateinit var disneyRepository: DisneyRepository

    private lateinit var getDisneyCharactersListUseCaseImpl: GetDisneyCharactersListUseCase

    @Before
    fun setUp() {
        getDisneyCharactersListUseCaseImpl = GetDisneyCharactersListUseCaseImpl(disneyRepository)
    }

    @Test
    fun `GIVEN disney repository WHEN calls getCharactersList THEN returns characters entity list as success`() {

        runTest {

            // ARRANGE
            val characterListEntity = FakeData.getCharacterListEntity()
            coEvery { disneyRepository.getDisneyCharactersList() } returns APIResult.Success(
                characterListEntity
            )

            //ACT
            val result = disneyRepository.getDisneyCharactersList()

            //ASSERT
            assertTrue(result is APIResult.Success)
            assertEquals(characterListEntity, (result as APIResult.Success).data)

        }
    }

    @Test
    fun `GIVEN disney repository WHEN calls getCharactersList THEN throws exception as error`() {

        runTest {

            // ARRANGE
            val exception = Exception(EXCEPTION_MESSAGE)
            coEvery { disneyRepository.getDisneyCharactersList() } returns APIResult.Error(
                exception
            )

            //ACT
            val result = disneyRepository.getDisneyCharactersList()

            //ASSERT
            assertTrue(result is APIResult.Error)
            assertEquals(exception, (result as APIResult.Error).exception)

        }
    }

    companion object {
        const val EXCEPTION_MESSAGE = "Network Exception!"
    }

}