package com.assignment.domain.usecases

import com.assignment.domain.repository.DisneyRepository
import javax.inject.Inject

/**
 *  To get the characters list.
 *
 *  @param disneyRepository to get the details from the server.
 */
class GetDisneyCharactersListUseCase @Inject constructor(private val disneyRepository: DisneyRepository) {
    suspend operator fun invoke() = disneyRepository.getDisneyCharactersList()
}