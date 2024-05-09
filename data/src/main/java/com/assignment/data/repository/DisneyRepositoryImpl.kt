package com.assignment.data.repository

import com.assignment.common.APIResult
import com.assignment.common.logger.Logger
import com.assignment.data.api.DisneyService
import com.assignment.data.mappers.CharacterEntityMapper
import com.assignment.data.mappers.CharacterListEntityMapper
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
            logger.debug("$TAG Fetching characters list from server...")
            disneyService.getDisneyCharactersList().run {

                logger.debug("$TAG Characters list fetched successfully with data : ${this.data}")
                APIResult.Success(characterListEntityMapper.mapToEntity(this))

            }
        } catch (exception: Exception) {
            logger.error("$TAG Fetch failed with error: ${exception.message}")
            APIResult.Error(exception)
        }
    }

    override suspend fun getDisneyCharacterDetails(id: Int): APIResult<CharacterEntity> {
        return try {
            logger.debug("$TAG Fetching character details with id=$id from server...")

            disneyService.getDisneyCharacterDetails(id).run {

                logger.debug("$TAG Character details fetched successfully with data : ${this.data}")

                APIResult.Success(characterEntityMapper.mapToEntity(this.data))
            }
        } catch (exception: Exception) {
            logger.error("$TAG Fetch failed with error: ${exception.message}")
            APIResult.Error(exception)
        }
    }
}