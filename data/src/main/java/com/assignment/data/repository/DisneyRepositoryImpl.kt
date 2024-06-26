package com.assignment.data.repository

import com.assignment.common.logger.Logger
import com.assignment.data.api.DisneyService
import com.assignment.data.common.Utils
import com.assignment.data.common.toCharacterEntity
import com.assignment.data.common.toCharactersListEntity
import com.assignment.domain.repository.DisneyRepository
import javax.inject.Inject

/**
 * Concrete implementation of [DisneyRepository].
 *
 * @param disneyService api service.
 * @param logger to log messages and errors.
 *
 */
class DisneyRepositoryImpl @Inject constructor(
    private val disneyService: DisneyService,
    private val logger: Logger,
    private val utils: Utils
) : DisneyRepository {

    override suspend fun getDisneyCharactersList() =
        utils.callSafely({ disneyService.getDisneyCharactersList() }, { toCharactersListEntity() })

    override suspend fun getDisneyCharacterDetails(id: Int) =
        utils.callSafely({ disneyService.getDisneyCharacterDetails(id) },
            { data.toCharacterEntity() })

}
