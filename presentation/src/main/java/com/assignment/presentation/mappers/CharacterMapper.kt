package com.assignment.presentation.mappers

import com.assignment.domain.entities.CharacterEntity
import com.assignment.presentation.models.Character
import javax.inject.Inject

/**
 * To map the character entity object to character model which is required by presentation module.
 */
class CharacterMapper @Inject constructor() {

    /**
     * To map the character entity to character model.
     *
     * @param characterEntity model from domain module.
     *
     * @return [Character] model required by presentation module.
     */
    fun mapToCharacter(characterEntity: CharacterEntity): Character {
        return with(characterEntity)
        {
            Character(
                id = id,
                name = name,
                imageUrl = imageUrl,
                films = films,
                createdAt = createdAt,
                updatedAt = updatedAt
            )
        }
    }
}