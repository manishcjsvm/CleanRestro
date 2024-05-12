package com.assignment.data.mappers

import com.assignment.common.Mapper
import com.assignment.data.dto.CharactersListDTO
import com.assignment.domain.entities.CharacterListEntity
import javax.inject.Inject

/**
 * To map the character list dto object to character list entity which is required by domain module.
 */
class CharacterListEntityMapper @Inject constructor(private val characterEntityMapper: CharacterEntityMapper) :
    Mapper<CharactersListDTO, CharacterListEntity> {

    /**
     * To map the character list dto to character list entity.
     *
     * @param from model from data module.
     *
     * @return [CharacterListEntity] model required by domain module.
     */
    override fun map(from: CharactersListDTO): CharacterListEntity {
        return with(from)
        {
            CharacterListEntity(characterEntityList = data.map {
                characterEntityMapper.map(it)
            })
        }
    }
}