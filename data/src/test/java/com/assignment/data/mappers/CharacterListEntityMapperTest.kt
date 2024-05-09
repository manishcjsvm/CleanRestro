package com.assignment.data.mappers

import com.assignment.data.fakes.FakeData
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterListEntityMapperTest {

    @get:Rule
    val mockkRule = MockKRule(this)
    private lateinit var characterListEntityMapper: CharacterListEntityMapper

    @MockK
    private lateinit var characterEntityMapper: CharacterEntityMapper

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
        every { characterEntityMapper.mapToEntity(any()) } returns characterEntity

        //ACT
        val result = characterListEntityMapper.mapToEntity(charactersListDTO)

        //ASSERT
        assertEquals(characterListEntity, result)
        verify { characterEntityMapper.mapToEntity(any()) }
    }
}