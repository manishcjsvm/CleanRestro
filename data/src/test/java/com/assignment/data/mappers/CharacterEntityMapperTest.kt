package com.assignment.data.mappers

import com.assignment.data.fakes.FakeData
import com.assignment.data.toCharacterEntity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CharacterEntityMapperTest {

    private lateinit var fakeData: FakeData

    @Before
    fun setUp() {
        fakeData = FakeData()
    }

    @Test
    fun `GIVEN mapper with character dto WHEN calls toCharacterEntity THEN returns character entity as success`() {

        //ARRANGE
        val characterDTO = fakeData.getCharacterDTO()
        val characterEntity = fakeData.getCharacterEntity()

        //ACT
        val result = characterDTO.toCharacterEntity()

        //ASSERT
        assertEquals(characterEntity, result)

    }
}