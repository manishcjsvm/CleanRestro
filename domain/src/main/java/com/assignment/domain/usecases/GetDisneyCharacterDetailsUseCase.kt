package com.assignment.domain.usecases

import com.assignment.common.APIResult
import com.assignment.domain.entities.CharacterEntity

/**
 * Use Case to get the details of the character selected
 */
interface GetDisneyCharacterDetailsUseCase {

    suspend operator fun invoke(id: Int): APIResult<CharacterEntity>
}