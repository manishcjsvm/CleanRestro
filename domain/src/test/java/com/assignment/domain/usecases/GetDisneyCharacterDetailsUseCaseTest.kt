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

class GetDisneyCharacterDetailsUseCaseTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    private val disneyRepository: DisneyRepository = mockk()

    private lateinit var getDisneyCharacterDetailsUseCase: GetDisneyCharacterDetailsUseCase

    private lateinit var fakeData: FakeData

    @Before
    fun setUp() {
        fakeData = FakeData()

        getDisneyCharacterDetailsUseCase =
            GetDisneyCharacterDetailsUseCase(disneyRepository)
    }

    @Test
    fun `GIVEN character id WHEN calls getDisneyCharacterDetailsUseCase THEN returns character entity as success`() {

        runTest {

            // ARRANGE
            val characterEntity = fakeData.getCharacterEntity()
            coEvery { disneyRepository.getDisneyCharacterDetails(ID) } returns APIResult.Success(
                characterEntity
            )

            //ACT
            val result = getDisneyCharacterDetailsUseCase(ID)

            //ASSERT
            assertTrue(result is APIResult.Success)
            assertEquals(characterEntity, (result as APIResult.Success).data)

        }

    }

    @Test
    fun `GIVEN character id WHEN calls getDisneyCharacterDetailsUseCase THEN throws exception as error`() {

        runTest {

            // ARRANGE
            coEvery { disneyRepository.getDisneyCharacterDetails(ID) } returns APIResult.Error(
                ERROR_CODE, ERROR_MESSAGE
            )

            //ACT
            val result = getDisneyCharacterDetailsUseCase(ID)

            //ASSERT
            assertTrue(result is APIResult.Error)
            assertEquals(ERROR_MESSAGE, (result as APIResult.Error).errorMessage)

        }
    }


    private companion object {
        const val ID = 1
        const val ERROR_CODE = 1
        const val ERROR_MESSAGE = "Something went wrong! Please try again!"
    }
}