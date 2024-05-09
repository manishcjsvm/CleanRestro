package com.assignment.presentation.features.homescreen

import com.assignment.presentation.base.SideEffect

sealed interface CharactersListSideEffect : SideEffect {

    /**
     * To be used as side effect to navigation from characters list screen to character details screen.
     *
     * @param id id of the selected character.
     */
    data class NavigateToCharacterDetails(val id: Int) : CharactersListSideEffect
}