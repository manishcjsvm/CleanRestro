package com.assignment.data.mappers

import com.assignment.data.dto.CharacterDTO
import com.assignment.domain.entities.CharacterEntity
import javax.inject.Inject

/**
 * To map the character dto object to character entity which is required by domain module.
 */
class CharacterEntityMapper @Inject constructor() {

    /**
     * To map the character dto to character entity.
     *
     * @param characterDTO model from data module.
     *
     * @return [CharacterEntity] model required by domain module.
     */
    fun mapToEntity(characterDTO: CharacterDTO): CharacterEntity {
        return with(characterDTO)
        {
            CharacterEntity(
                id = id,
                name = name,
                imageUrl = imageUrl ?: "",
                films = films,
                tvShows = tvShows,
                createdAt = createdAt,
                updatedAt = updatedAt
            )
        }
    }
}