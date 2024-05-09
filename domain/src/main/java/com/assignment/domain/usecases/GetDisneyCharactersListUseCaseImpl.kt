package com.assignment.domain.usecases

import com.assignment.common.APIResult
import com.assignment.domain.entities.CharacterListEntity
import com.assignment.domain.repository.DisneyRepository
import javax.inject.Inject

class GetDisneyCharactersListUseCaseImpl @Inject constructor(private val repository: DisneyRepository) :
    GetDisneyCharactersListUseCase {
    override suspend fun invoke(): APIResult<CharacterListEntity> {
        return repository.getDisneyCharactersList()
    }
}