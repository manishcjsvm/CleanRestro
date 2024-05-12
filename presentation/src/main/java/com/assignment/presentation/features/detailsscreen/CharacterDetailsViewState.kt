package com.assignment.presentation.features.detailsscreen

import com.assignment.domain.APIResult
import com.assignment.presentation.base.ViewState
import com.assignment.presentation.models.Character

/**
 * View state to send back to the view form the view model.
 *
 */
sealed class CharacterDetailsViewState : ViewState {

    /**
     * To show loading while operations.
     */
    data object Loading : CharacterDetailsViewState()

    /**
     * To be used when there will be success case.
     *
     * @param data data to be passed to the view to load.
     */
    data class Success(val data: Character) : CharacterDetailsViewState()

    /**
     * To be used where there will be error in response.
     *
     * @param errorCode error code to be passed.
     * @param errorMessage error message to be passed.
     */
    data class Error(val errorCode: Int, val errorMessage: String) : CharacterDetailsViewState()
}