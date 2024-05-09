package com.assignment.presentation.features.homescreen

import com.assignment.presentation.base.ViewState
import com.assignment.presentation.models.CharacterList

sealed class CharacterListViewState : ViewState {

    /**
     * To show loading while operations.
     */
    data object Loading : CharacterListViewState()

    /**
     * To be used when there will be success case.
     *
     * @param data data to be passed to the view to load.
     */
    data class Success(val data: CharacterList) : CharacterListViewState()

    /**
     * To be used when there will be an error case.
     *
     * @param exception to be passed to the view.
     */
    data class Error(val exception: Exception) : CharacterListViewState()

}