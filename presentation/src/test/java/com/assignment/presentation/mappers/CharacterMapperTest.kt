package com.assignment.presentation.mappers

import com.assignment.presentation.fakes.FakeData
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CharacterMapperTest {

    private lateinit var characterMapper: CharacterMapper

    @Before
    fun setUp() {
        characterMapper = CharacterMapper()
    }

    @Test
    fun `GIVEN mapper with character entity WHEN calls mapToCharacter RETURNS character`() {

        //ARRANGE
        val characterEntity = FakeData.getCharacterEntity()
        val character = FakeData.getCharacter()

        //ACT
        val result = characterMapper.map(characterEntity)

        //ASSERT
        assertEquals(character, result)
    }

}