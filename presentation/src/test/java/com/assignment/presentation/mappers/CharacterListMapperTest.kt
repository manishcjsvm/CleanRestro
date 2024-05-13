package com.assignment.presentation.mappers

import com.assignment.presentation.fakes.FakeData
import com.assignment.presentation.toCharacterList
import io.mockk.junit4.MockKRule
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterListMapperTest {

    @get:Rule
    val mockkRule = MockKRule(this)


    private lateinit var fakeData: FakeData

    @Before
    fun setUp() {
        fakeData = FakeData()
    }

    @Test
    fun `GIVEN mapper with character list entity WHEN calls toCharacterList RETURNS characters list`() {

        //ARRANGE
        val charactersListEntity = fakeData.getCharactersListEntity()
        val charactersList = fakeData.getCharactersList()

        //ACT
        val result = charactersListEntity.toCharacterList()

        //ASSERT
        assertEquals(charactersList, result)
    }
}