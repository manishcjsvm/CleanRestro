package com.assignment.presentation.mappers

import com.assignment.common.Mapper
import com.assignment.domain.entities.CharacterListEntity
import com.assignment.presentation.models.CharacterList
import javax.inject.Inject

/**
 * To map the character list entity object to character model which is required by presentation module.
 */
class CharacterListMapper @Inject constructor(private val characterMapper: CharacterMapper) :
    Mapper<CharacterListEntity, CharacterList> {

    /**
     * To map the character list entity to character model.
     *
     * @param from model from domain module.
     *
     * @return [CharacterList] model required by presentation module.
     */
    override fun map(from: CharacterListEntity): CharacterList {
        return with(from)
        {
            CharacterList(charactersList = characterEntityList.map {
                characterMapper.map(it)
            })
        }
    }
}