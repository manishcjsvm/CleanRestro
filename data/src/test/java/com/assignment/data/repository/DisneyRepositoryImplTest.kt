package com.assignment.data.repository

import com.assignment.common.APIResult
import com.assignment.common.logger.Logger
import com.assignment.data.api.DisneyService
import com.assignment.data.fakes.FakeData
import com.assignment.data.mappers.CharacterEntityMapper
import com.assignment.data.mappers.CharacterListEntityMapper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DisneyRepositoryImplTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    private lateinit var disneyService: DisneyService

    @MockK
    private lateinit var characterListEntityMapper: CharacterListEntityMapper

    @MockK
    private lateinit var characterEntityMapper: CharacterEntityMapper

    @MockK
    private val logger = mockk<Logger>()

    private lateinit var disneyRepositoryImpl: DisneyRepositoryImpl


    @Before
    fun setUp() {

        every { logger.debug(any()) } returns Unit
        every { logger.error(any()) } returns Unit
        disneyRepositoryImpl =
            DisneyRepositoryImpl(
                disneyService,
                characterListEntityMapper,
                characterEntityMapper,
                logger
            )
    }

    @Test
    fun `GIVEN disney service WHEN calls getCharactersListDTO() THEN returns characters list dto as success`() {


        runTest {

            //ARRANGE
            val charactersListDTO = FakeData.getCharactersListDTO()
            val charactersListEntity = FakeData.getCharacterListEntity()
            coEvery { disneyService.getDisneyCharactersList() } returns charactersListDTO
            coEvery { characterListEntityMapper.mapToEntity(charactersListDTO) } returns charactersListEntity

            //ACT
            val apiResult = disneyRepositoryImpl.getDisneyCharactersList()

            //ASSERT
            assertTrue(apiResult is APIResult.Success)
            assertEquals(charactersListEntity, (apiResult as APIResult.Success).data)
            coVerify { disneyService.getDisneyCharactersList() }
            verify { characterListEntityMapper.mapToEntity(charactersListDTO) }

        }
    }

    @Test
    fun `GIVEN disney service WHEN calls getCharactersListDTO() THEN throws exceptions as error`() {

        runTest {

            //ARRANGE
            val exception = Exception(EXCEPTION_MESSAGE)
            coEvery { disneyService.getDisneyCharactersList() } throws exception

            //ACT
            val result = disneyRepositoryImpl.getDisneyCharactersList()

            //ASSERT
            assertTrue(result is APIResult.Error)
            assertEquals(exception, (result as APIResult.Error).exception)
            coVerify { disneyService.getDisneyCharactersList() }

        }
    }

    @Test
    fun `GIVEN disney service WHEN calls getDisneyCharacterDetails() THEN returns character as success`() {
        runTest {

            //ARRANGE
            val characterDetailsDTO = FakeData.getCharacterDetailsDTO()
            val charactersDTO = FakeData.getCharacterDTO()
            val characterEntity = FakeData.getCharacterEntity()
            coEvery { disneyService.getDisneyCharacterDetails(ID) } returns characterDetailsDTO
            coEvery { characterEntityMapper.mapToEntity(charactersDTO) } returns characterEntity

            //ACT
            val apiResult = disneyRepositoryImpl.getDisneyCharacterDetails(ID)

            //ASSERT
            assertTrue(apiResult is APIResult.Success)
            assertEquals(characterEntity, (apiResult as APIResult.Success).data)
            coVerify { disneyService.getDisneyCharacterDetails(ID) }
            verify { characterEntityMapper.mapToEntity(charactersDTO) }

        }
    }

    @Test
    fun `GIVEN disney service WHEN calls getDisneyCharacterDetails() THEN throws exceptions as error`() {

        runTest {

            //ARRANGE
            val exception = Exception(EXCEPTION_MESSAGE)
            coEvery { disneyService.getDisneyCharacterDetails(ID) } throws exception

            //ACT
            val result = disneyRepositoryImpl.getDisneyCharacterDetails(ID)

            //ASSERT
            assertTrue(result is APIResult.Error)
            assertEquals(exception, (result as APIResult.Error).exception)
            coVerify { disneyService.getDisneyCharacterDetails(ID) }

        }
    }

    companion object {
        const val EXCEPTION_MESSAGE = "Network Exception!"
        const val ID = 1
    }

}