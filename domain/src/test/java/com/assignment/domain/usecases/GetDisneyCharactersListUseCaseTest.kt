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

class GetDisneyCharactersListUseCaseTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    private val disneyRepositoryMock: DisneyRepository = mockk()

    private lateinit var getDisneyCharactersListUseCase: GetDisneyCharactersListUseCase

    private val fakeData: FakeData = FakeData()

    @Before
    fun setUp() {
        getDisneyCharactersListUseCase = GetDisneyCharactersListUseCase(disneyRepositoryMock)
    }

    @Test
    fun `GIVEN nothing WHEN calls getDisneyCharactersListUseCase THEN verify repository called`() =
        runTest {

            // ARRANGE
            val characterListEntity = fakeData.getCharacterListEntity()
            coEvery { disneyRepositoryMock.getDisneyCharactersList() } returns flowOf(
                APIResult.Success(
                    characterListEntity
                )
            )

            //ACT
            getDisneyCharactersListUseCase()

            //ASSERT
            coVerify { disneyRepositoryMock.getDisneyCharactersList() }

        }
}