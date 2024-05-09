package com.assignment.data.mappers

import com.assignment.data.dto.CharactersListDTO
import com.assignment.domain.entities.CharacterListEntity
import javax.inject.Inject

/**
 * To map the character list dto object to character list entity which is required by domain module.
 */
class CharacterListEntityMapper @Inject constructor(private val characterEntityMapper: CharacterEntityMapper) {

    /**
     * To map the character list dto to character list entity.
     *
     * @param charactersListDTO model from data module.
     *
     * @return [CharacterListEntity] model required by domain module.
     */
    fun mapToEntity(charactersListDTO: CharactersListDTO): CharacterListEntity {
        return with(charactersListDTO)
        {
            CharacterListEntity(characterEntityList = data.map {
                characterEntityMapper.mapToEntity(it)
            })
        }
    }
}