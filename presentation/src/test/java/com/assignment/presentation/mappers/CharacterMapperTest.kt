package com.assignment.presentation.mappers

import com.assignment.presentation.fakes.FakeData
import com.assignment.presentation.toCharacter
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CharacterMapperTest {


    private lateinit var fakeData: FakeData
    @Before
    fun setUp() {
        fakeData = FakeData()
    }

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