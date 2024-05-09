package com.assignment.presentation.features.detailsscreen

import com.assignment.presentation.base.ViewIntent

sealed interface CharacterDetailsViewIntent : ViewIntent {

    /**
     * Intent to load character details.
     *
     * @param id id of the selected character.
     *
     */
    data class LoadData(val id: Int) : CharacterDetailsViewIntent

}