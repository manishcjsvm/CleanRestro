package com.assignment.presentation.mappers

import com.assignment.domain.entities.CharacterListEntity
import com.assignment.presentation.models.CharacterList
import javax.inject.Inject

/**
 * To map the character list entity object to character model which is required by presentation module.
 */
class CharacterListMapper @Inject constructor(private val characterMapper: CharacterMapper) {

    /**
     * To map the character list entity to character model.
     *
     * @param characterListEntity model from domain module.
     *
     * @return [CharacterList] model required by presentation module.
     */
    fun mapToCharacterList(characterListEntity: CharacterListEntity): CharacterList {
        return with(characterListEntity)
        {
            CharacterList(charactersList = characterEntityList.map {
                characterMapper.mapToCharacter(it)
            })
        }
    }
}