package com.assignment.domain.repository

import com.assignment.domain.APIResult
import com.assignment.domain.entities.CharacterEntity
import com.assignment.domain.entities.CharacterListEntity

/**
 * Repository which deals with the fetching of characters and their details from the
 * server.
 */
interface DisneyRepository {

    /**
     * To get the list of characters from the server.
     *
     * @return [APIResult]
     */
    suspend fun getDisneyCharactersList(): APIResult<CharacterListEntity>

    /**
     * To get the details of the character selected from the server.
     *
     * @param id id of the character.
     *
     * @return [APIResult]
     */
    suspend fun getDisneyCharacterDetails(id: Int): APIResult<CharacterEntity>

}
