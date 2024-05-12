package com.assignment.presentation.features.homescreen

import com.assignment.presentation.base.ViewState
import com.assignment.presentation.features.detailsscreen.CharacterDetailsViewState
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
     * To be used where there will be error in response.
     *
     * @param errorCode error code to be passed.
     * @param errorMessage error message to be passed.
     */
    data class Error(val errorCode: Int, val errorMessage: String) : CharacterListViewState()
}