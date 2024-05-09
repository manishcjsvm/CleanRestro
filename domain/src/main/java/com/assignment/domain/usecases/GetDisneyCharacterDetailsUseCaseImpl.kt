package com.assignment.domain.usecases

import com.assignment.common.APIResult
import com.assignment.domain.entities.CharacterEntity
import com.assignment.domain.repository.DisneyRepository
import javax.inject.Inject

class GetDisneyCharacterDetailsUseCaseImpl @Inject constructor(private val disneyRepository: DisneyRepository) :
    GetDisneyCharacterDetailsUseCase {

    override suspend fun invoke(id: Int): APIResult<CharacterEntity> {
        return disneyRepository.getDisneyCharacterDetails(id)
    }
}