package com.assignment.data.mappers

import com.assignment.common.Mapper
import com.assignment.data.dto.CharacterDTO
import com.assignment.domain.entities.CharacterEntity
import javax.inject.Inject

/**
 * To map the character dto object to character entity which is required by domain module.
 */
class CharacterEntityMapper @Inject constructor() : Mapper<CharacterDTO,CharacterEntity> {

    /**
     * To map the character dto to character entity.
     *
     * @param from model from data module.
     *
     * @return [CharacterEntity] model required by domain module.
     */

   override fun map(from: CharacterDTO): CharacterEntity {
        return with(from)
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