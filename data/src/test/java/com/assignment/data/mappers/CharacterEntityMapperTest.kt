package com.assignment.data.mappers

import com.assignment.data.fakes.FakeData
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CharacterEntityMapperTest {


    private lateinit var characterEntityMapper: CharacterEntityMapper

    @Before
    fun setUp() {
        characterEntityMapper = CharacterEntityMapper()
    }

    @Test
    fun `GIVEN character entity mapper with character dto WHEN calls mapToEntity THEN returns character entity as success`() {

        //ARRANGE
        val characterDTO = FakeData.getCharacterDTO()
        val characterEntity = FakeData.getCharacterEntity()

        //ACT
        val result = characterEntityMapper.map(characterDTO)

        //ASSERT
        assertEquals(characterEntity, result)

    }
}