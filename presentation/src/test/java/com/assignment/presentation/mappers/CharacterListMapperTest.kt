package com.assignment.presentation.mappers

import com.assignment.presentation.fakes.FakeData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterListMapperTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    private lateinit var characterListMapper: CharacterListMapper

    @MockK
    private lateinit var characterMapper: CharacterMapper

    @Before
    fun setUp() {
        characterListMapper = CharacterListMapper(characterMapper = characterMapper)
    }

    @Test
    fun `GIVEN mapper with character list entity WHEN calls mapToCharacterList RETURNS characters list`() {

        //ARRANGE
        val charactersListEntity = FakeData.getCharactersListEntity()
        val charactersList = FakeData.getCharactersList()
        val characterEntity = FakeData.getCharacterEntity()
        val character = FakeData.getCharacter()
        coEvery { characterMapper.mapToCharacter(characterEntity) } returns character

        //ACT
        val result = characterListMapper.mapToCharacterList(charactersListEntity)

        //ASSERT
        assertEquals(charactersList, result)
        coVerify { characterMapper.mapToCharacter(characterEntity) }
    }
}