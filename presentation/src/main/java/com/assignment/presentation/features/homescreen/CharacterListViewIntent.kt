package com.assignment.presentation.features.homescreen

import com.assignment.presentation.base.ViewIntent

sealed interface CharacterListViewIntent : ViewIntent {

    /**
     * Intent to load character details.
     *
     */
    data object LoadData : CharacterListViewIntent

    /**
     * Intent to pass click event on character to the view model.
     *
     * @param id id of the selected character.
     */
    data class OnItemClicked(val id: Int) : CharacterListViewIntent

}