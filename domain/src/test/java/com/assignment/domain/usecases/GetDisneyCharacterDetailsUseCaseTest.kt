package com.assignment.domain.usecases

import com.assignment.domain.common.APIResult
import com.assignment.domain.fakes.FakeData
import com.assignment.domain.repository.DisneyRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetDisneyCharacterDetailsUseCaseTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    private val disneyRepositoryMock: DisneyRepository = mockk()

    private lateinit var getDisneyCharacterDetailsUseCase: GetDisneyCharacterDetailsUseCase

    private val fakeData: FakeData = FakeData()

    @Before
    fun setUp() {

        getDisneyCharacterDetailsUseCase =
            GetDisneyCharacterDetailsUseCase(disneyRepositoryMock)
    }

    @Test
    fun `GIVEN character id WHEN calls getDisneyCharacterDetailsUseCase THEN verify repository called`() =
        runTest {

            // ARRANGE
            val characterEntity = fakeData.getCharacterEntity()
            coEvery { disneyRepositoryMock.getDisneyCharacterDetails(ID) } returns flowOf(
                APIResult.Success(
                    characterEntity
                )
            )

            //ACT
            getDisneyCharacterDetailsUseCase(ID)

            //ASSERT
            coVerify { disneyRepositoryMock.getDisneyCharacterDetails(ID) }

        }


    private companion object {
        const val ID = 1
    }
}