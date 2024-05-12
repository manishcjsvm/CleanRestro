package com.assignment.data.mappers

import com.assignment.data.fakes.FakeData
import io.mockk.every
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterListEntityMapperTest {

    @get:Rule
    val mockkRule = MockKRule(this)
    private lateinit var characterListEntityMapper: CharacterListEntityMapper

    private val characterEntityMapper: CharacterEntityMapper = mockk()

    @Before
    fun setUp() {
        characterListEntityMapper = CharacterListEntityMapper(characterEntityMapper)
    }

    @Test
    fun `GIVEN mapper with character list dto WHEN calls mapToEntity THEN returns character list entity`() {

        //ARRANGE
        val charactersListDTO = FakeData.getCharactersListDTO()
        val characterEntity = FakeData.getCharacterEntity()
        val characterListEntity = FakeData.getCharacterListEntity()
        every { characterEntityMapper.map(any()) } returns characterEntity

        //ACT
        val result = characterListEntityMapper.map(charactersListDTO)

        //ASSERT
        assertEquals(characterListEntity, result)
        verify { characterEntityMapper.map(any()) }
    }
}