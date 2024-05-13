package com.assignment.data.mappers

import com.assignment.data.fakes.FakeData
import com.assignment.data.toCharactersListEntity
import io.mockk.junit4.MockKRule
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterListEntityMapperTest {

    @get:Rule
    val mockkRule = MockKRule(this)


    private lateinit var fakeData: FakeData

    @Before
    fun setUp() {
        fakeData = FakeData()
    }

    @Test
    fun `GIVEN mapper with character list dto WHEN calls toCharactersListEntity THEN returns character list entity`() {

        //ARRANGE
        val charactersListDTO = fakeData.getCharactersListDTO()
        val characterListEntity = fakeData.getCharacterListEntity()

        //ACT
        val result = charactersListDTO.toCharactersListEntity()

        //ASSERT
        assertEquals(characterListEntity, result)
    }
}