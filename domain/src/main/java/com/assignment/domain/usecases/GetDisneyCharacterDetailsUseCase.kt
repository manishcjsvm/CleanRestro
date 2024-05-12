package com.assignment.domain.usecases

import com.assignment.domain.APIResult
import com.assignment.domain.entities.CharacterEntity
import com.assignment.domain.repository.DisneyRepository
import javax.inject.Inject

/**
 * To get the character details.
 *
 * @param disneyRepository to get the details from the server.
 */
class GetDisneyCharacterDetailsUseCase @Inject constructor(private val disneyRepository: DisneyRepository) {
    suspend operator fun invoke(id: Int): APIResult<CharacterEntity> {
        return disneyRepository.getDisneyCharacterDetails(id)
    }
}