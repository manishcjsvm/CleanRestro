package com.assignment.presentation.mappers

import com.assignment.common.Mapper
import com.assignment.domain.entities.CharacterEntity
import com.assignment.presentation.models.Character
import javax.inject.Inject

/**
 * To map the character entity object to character model which is required by presentation module.
 */
class CharacterMapper @Inject constructor() : Mapper<CharacterEntity, Character> {

    /**
     * To map the character entity to character model.
     *
     * @param from model from domain module.
     *
     * @return [Character] model required by presentation module.
     */
    override fun map(from: CharacterEntity): Character {
        return with(from)
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