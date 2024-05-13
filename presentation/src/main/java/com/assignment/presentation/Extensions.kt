package com.assignment.presentation

import com.assignment.domain.entities.CharacterEntity
import com.assignment.domain.entities.CharacterListEntity
import com.assignment.presentation.models.Character
import com.assignment.presentation.models.CharacterList

/**
 * Mapper which maps domain model to presentation model.
 *
 * @return [CharacterList]
 */
fun CharacterListEntity.toCharacterList() = CharacterList(charactersList = characterEntityList.map {
    it.toCharacter()
})

/**
 * Mapper which maps domain model to presentation model.
 *
 * @return [Character]
 */
fun CharacterEntity.toCharacter() = Character(
    id = id,
    name = name,
    imageUrl = imageUrl,
    films = films,
    createdAt = createdAt,
    updatedAt = updatedAt
)