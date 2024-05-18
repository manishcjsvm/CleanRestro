package com.assignment.presentation.mappers

import com.assignment.presentation.fakes.FakeData
import com.assignment.presentation.toCharacter
import org.junit.Assert.assertEquals
import org.junit.Test

class CharacterMapperTest {


    private val fakeData: FakeData = FakeData()

    @Test
    fun `GIVEN mapper with character entity WHEN calls toCharacter RETURNS character`() {

        //ARRANGE
        val characterEntity = fakeData.getCharacterEntity()
        val character = fakeData.getCharacter()

        //ACT
        val result = characterEntity.toCharacter()

        //ASSERT
        assertEquals(character, result)
    }

}