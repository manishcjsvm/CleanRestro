package com.assignment.presentation.features.detailsscreen

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
    object Loading : CharacterDetailsViewState()

    /**
     * To be used when there will be success case.
     *
     * @param data data to be passed to the view to load.
     */
    data class Success(val data: Character) : CharacterDetailsViewState()

    /**
     * To be used when there will be an error case.
     *
     * @param exception to be passed to the view.
     */
    data class Error(val exception: Exception) : CharacterDetailsViewState()

}