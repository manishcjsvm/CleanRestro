package com.assignment.data.repository

import com.assignment.common.logger.Logger
import com.assignment.data.api.DisneyService
import com.assignment.data.mappers.CharacterEntityMapper
import com.assignment.data.mappers.CharacterListEntityMapper
import com.assignment.data.toAPIResult
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
 * @param characterListEntityMapper which maps character list dto to character list entity.
 * @param characterEntityMapper which maps character dto to character entity.
 * @param logger to log messages and errors.
 *
 */
class DisneyRepositoryImpl @Inject constructor(
    private val disneyService: DisneyService,
    private val characterListEntityMapper: CharacterListEntityMapper,
    private val characterEntityMapper: CharacterEntityMapper,
    private val logger: Logger
) : DisneyRepository {

    override suspend fun getDisneyCharactersList(): APIResult<CharacterListEntity> {
        return try {

            val result = disneyService.getDisneyCharactersList()
            APIResult.Success(characterListEntityMapper.map(result))

        } catch (exception: Exception) {
            logger.error("$TAG Fetch failed with error: ${exception.message}")
            exception.toAPIResult()
        }
    }

    override suspend fun getDisneyCharacterDetails(id: Int): APIResult<CharacterEntity> {
        return try {
            val result = disneyService.getDisneyCharacterDetails(id)
            APIResult.Success(characterEntityMapper.map(result.data))
        } catch (exception: Exception) {
            logger.error("$TAG Fetch failed with error: ${exception.message}")
            exception.toAPIResult()
        }
    }
}