package com.assignment.data.mappers

import com.assignment.data.common.toCharactersListEntity
import com.assignment.data.fakes.FakeData
import org.junit.Assert.assertEquals
import org.junit.Test

class CharacterListEntityMapperTest {


    private val fakeData: FakeData = FakeData()


    @Test
    fun `GIVEN character list dto WHEN calls toCharactersListEntity as extension function THEN returns character list entity`() {

        //ARRANGE
        val charactersListDTO = fakeData.getCharactersListDTO()
        val characterListEntity = fakeData.getCharacterListEntity()

        //ACT
        val result = charactersListDTO.toCharactersListEntity()

        //ASSERT
        assertEquals(characterListEntity, result)
    }
}