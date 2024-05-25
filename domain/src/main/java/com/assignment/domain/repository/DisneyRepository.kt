package com.assignment.domain.repository

import com.assignment.domain.common.APIResult
import com.assignment.domain.entities.CharacterEntity
import com.assignment.domain.entities.CharacterListEntity
import kotlinx.coroutines.flow.Flow

/**
 * Repository which deals with the fetching of characters and their details from the
 * server.
 */
interface DisneyRepository {

    /**
     * To get the list of characters from the server.
     *
     * @return [Flow] of [APIResult]
     */
    suspend fun getDisneyCharactersList(): Flow<APIResult<CharacterListEntity>>

    /**
     * To get the details of the character selected from the server.
     *
     * @param id id of the character.
     *
     * @return [Flow] of [APIResult]
     */
    suspend fun getDisneyCharacterDetails(id: Int): Flow<APIResult<CharacterEntity>>

}
