package com.assignment.presentation.features.homescreen

import com.assignment.presentation.base.SideEffect

/**
 * To be used as side effect to navigation from characters list screen to character details screen.
 *
 * @param id id of the selected character.
 */
data class NavigateToCharacterDetailsSideEffect(val id: Int) : SideEffect