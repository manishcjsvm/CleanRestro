package com.assignment.data.repository

import com.assignment.common.logger.Logger
import com.assignment.data.ErrorConstants
import com.assignment.data.api.DisneyService
import com.assignment.data.toAPIResult
import com.assignment.data.toCharacterEntity
import com.assignment.data.toCharactersListEntity
import com.assignment.domain.APIResult
import com.assignment.domain.entities.CharacterEntity
import com.assignment.domain.entities.CharacterListEntity
import com.assignment.domain.repository.DisneyRepository
import javax.inject.Inject

private const val TAG = "DisneyRepositoryImpl==>"

/**
 * Concrete implementation of [DisneyRepository].
 *
 * @param disneyService api service.
 * @param logger to log messages and errors.
 *
 */
class DisneyRepositoryImpl @Inject constructor(
    private val disneyService: DisneyService,
    private val logger: Logger
) : DisneyRepository {

    override suspend fun getDisneyCharactersList(): APIResult<CharacterListEntity> {
        return try {

            val result = disneyService.getDisneyCharactersList()
            APIResult.Success(result.toCharactersListEntity())

        } catch (exception: Exception) {
            logger.error("$TAG ${ErrorConstants.FETCH_CHARACTERS_LIST_ERROR} ${exception.message}")
            exception.toAPIResult()
        }
    }

    override suspend fun getDisneyCharacterDetails(id: Int): APIResult<CharacterEntity> {
        return try {
            val result = disneyService.getDisneyCharacterDetails(id)
            APIResult.Success(result.data.toCharacterEntity())
        } catch (exception: Exception) {
            logger.error("$TAG ${ErrorConstants.FETCH_CHARACTER_DETAILS_ERROR} ${exception.message}")
            exception.toAPIResult()
        }
    }
}