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

class GetDisneyCharacterDetailsUseCaseImplTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    private lateinit var disneyRepository:DisneyRepository

    private lateinit var getDisneyCharacterDetailsUseCaseImpl: GetDisneyCharacterDetailsUseCaseImpl

    @Before
    fun setUp() {
        getDisneyCharacterDetailsUseCaseImpl =
            GetDisneyCharacterDetailsUseCaseImpl(disneyRepository)
    }

    @Test
    fun `GIVEN disney repository WHEN calls getCharacterDetails THEN returns character entity as success`() {

        runTest {

            // ARRANGE
            val characterEntity = FakeData.getCharacterEntity()
            coEvery { disneyRepository.getDisneyCharacterDetails(ID) } returns APIResult.Success(
                characterEntity
            )

            //ACT
            val result = disneyRepository.getDisneyCharacterDetails(ID)

            //ASSERT
            assertTrue(result is APIResult.Success)
            assertEquals(characterEntity, (result as APIResult.Success).data)

        }

    }

    @Test
    fun `GIVEN disney repository WHEN calls getCharactersDetails THEN throws exception as error`() {

        runTest {

            // ARRANGE
            val exception = Exception(EXCEPTION_MESSAGE)
            coEvery { disneyRepository.getDisneyCharacterDetails(ID) } returns APIResult.Error(
                exception
            )

            //ACT
            val result = disneyRepository.getDisneyCharacterDetails(ID)

            //ASSERT
            assertTrue(result is APIResult.Error)
            assertEquals(exception, (result as APIResult.Error).exception)

        }
    }


    companion object {
        const val ID = 1
        const val EXCEPTION_MESSAGE = "Network Exception!"

    }
}