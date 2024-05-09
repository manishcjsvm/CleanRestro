package com.assignment.domain.usecases

import com.assignment.common.APIResult
import com.assignment.domain.entities.CharacterListEntity

/**
 * Use Case to get the list of characters.
 */
interface GetDisneyCharactersListUseCase {

    suspend operator fun invoke(): APIResult<CharacterListEntity>
}