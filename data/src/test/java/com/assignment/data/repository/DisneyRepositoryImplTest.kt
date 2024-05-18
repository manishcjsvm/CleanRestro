package com.assignment.data.repository

import com.assignment.common.logger.Logger
import com.assignment.data.api.DisneyService
import com.assignment.data.common.Utils
import com.assignment.data.dto.CharacterDetailsDTO
import com.assignment.data.dto.CharactersListDTO
import com.assignment.data.fakes.FakeData
import com.assignment.domain.common.APIResult
import io.mockk.coEvery
import io.mockk.junit4.MockKRule
import io.mockk.justRun
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class DisneyRepositoryImplTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    private val disneyService: DisneyService = mockk()

    private val loggerMock: Logger = mockk()

    private lateinit var disneyRepositoryImpl: DisneyRepositoryImpl

    private val fakeData: FakeData = FakeData()

    private val charactersListDTOResponseMock = mockk<Response<CharactersListDTO>>()

    private val characterDetailsDTOResponseMock = mockk<Response<CharacterDetailsDTO>>()

    private val utils = Utils(loggerMock)

    @Before
    fun setUp() {

        justRun { loggerMock.debug(any()) }
        justRun { loggerMock.error(any()) }

        disneyRepositoryImpl =
            DisneyRepositoryImpl(
                disneyService,
                loggerMock,
                utils
            )
    }

    @Test
    fun `GIVEN nothing WHEN calls getDisneyCharactersList() gets successful response with non null body THEN returns ApiResult as success`() =
        runTest {

            //ARRANGE
            val charactersListDTO = fakeData.getCharactersListDTO()

            coEvery { charactersListDTOResponseMock.isSuccessful } returns true
            coEvery { charactersListDTOResponseMock.body() } returns charactersListDTO
            coEvery { disneyService.getDisneyCharactersList() } returns charactersListDTOResponseMock

            //ACT
            val apiResult = disneyRepositoryImpl.getDisneyCharactersList()

            //ASSERT
            assertTrue(apiResult is APIResult.Success)
        }

    @Test
    fun `GIVEN nothing WHEN calls getDisneyCharactersList() gets successful response with null body THEN returns APIResult as error`() =
        runTest {

            //ARRANGE
            coEvery { charactersListDTOResponseMock.isSuccessful } returns true
            coEvery { charactersListDTOResponseMock.body() } returns null
            coEvery { disneyService.getDisneyCharactersList() } returns charactersListDTOResponseMock

            //ACT
            val apiResult = disneyRepositoryImpl.getDisneyCharactersList()

            //ASSERT
            assertTrue(apiResult is APIResult.Error)
        }


    @Test
    fun `GIVEN nothing WHEN calls getDisneyCharactersList() throws an exception THEN returns APIResult as error`() =

        runTest {

            //ARRANGE
            val exception = Exception(ERROR_MESSAGE)
            coEvery { disneyService.getDisneyCharactersList() } throws exception

            //ACT
            val result = disneyRepositoryImpl.getDisneyCharactersList()

            //ASSERT
            assertTrue(result is APIResult.Error)
        }


    @Test
    fun `GIVEN character id WHEN calls getDisneyCharacterDetails() gets successful response with non null body THEN returns APIResult as success`() =
        runTest {

            //ARRANGE
            val characterDetailsDTO = fakeData.getCharacterDetailsDTO()

            coEvery { characterDetailsDTOResponseMock.isSuccessful } returns true
            coEvery { characterDetailsDTOResponseMock.body() } returns characterDetailsDTO
            coEvery { disneyService.getDisneyCharacterDetails(ID) } returns characterDetailsDTOResponseMock

            //ACT
            val apiResult = disneyRepositoryImpl.getDisneyCharacterDetails(ID)

            //ASSERT
            assertTrue(apiResult is APIResult.Success)


        }

    @Test
    fun `GIVEN character id WHEN calls getDisneyCharacterDetails() gets successful response with null body THEN returns APIResult as error`() =

        runTest {

            //ARRANGE
            coEvery { characterDetailsDTOResponseMock.isSuccessful } returns true
            coEvery { characterDetailsDTOResponseMock.body() } returns null
            coEvery { disneyService.getDisneyCharacterDetails(ID) } returns characterDetailsDTOResponseMock

            //ACT
            val apiResult = disneyRepositoryImpl.getDisneyCharacterDetails(ID)

            //ASSERT
            assertTrue(apiResult is APIResult.Error)

        }

    @Test
    fun `GIVEN character id WHEN calls getDisneyCharacterDetails() throws an exception THEN returns APIResult as error`() =

        runTest {

            //ARRANGE
            val exception = Exception(ERROR_MESSAGE)
            coEvery { disneyService.getDisneyCharacterDetails(ID) } throws exception

            //ACT
            val result = disneyRepositoryImpl.getDisneyCharacterDetails(ID)

            //ASSERT
            assertTrue(result is APIResult.Error)
        }


    private companion object {
        const val ERROR_MESSAGE = "Something went wrong! Please try again!"
        const val ID = 1
    }

}