package com.assignment.data.mappers

import com.assignment.data.fakes.FakeData
import com.assignment.data.toCharacterEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class CharacterEntityMapperTest {

    private val fakeData: FakeData = FakeData()

    @Test
    fun `GIVEN character dto WHEN calls toCharacterEntity as extension function THEN returns character entity`() {

        //ARRANGE
        val characterDTO = fakeData.getCharacterDTO()
        val characterEntity = fakeData.getCharacterEntity()

        //ACT
        val result = characterDTO.toCharacterEntity()

        //ASSERT
        assertEquals(characterEntity, result)

    }
}