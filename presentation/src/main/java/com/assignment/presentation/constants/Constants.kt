package com.assignment.presentation.constants

import com.assignment.presentation.models.Character
import com.assignment.presentation.models.CharacterList

object Constants {

    // To be passed along with route in navigation from characters list screen to character details screen.
    const val characterId = "characterId"


    fun getCharacter(): Character {
        return Character(
            id = ID,
            name = NAME,
            imageUrl = IMAGE_URL,
            films = FILMS,
            createdAt = CREATED_AT,
            updatedAt = UPDATED_AT
        )
    }

    fun getCharactersList(): CharacterList {
        return CharacterList(buildList {
            add(getCharacter())
            add(getCharacter())
            add(getCharacter())
            add(getCharacter())
        })
    }

    private const val ID = 1
    private const val CREATED_AT = "2021-04-12T01:31:30.547Z"
    private val FILMS = buildList { add("Hercules (film)") }
    private const val IMAGE_URL =
        "https://static.wikia.nocookie.net/disney/images/d/d3/Vlcsnap-2015-05-06-23h04m15s601.png"
    private const val NAME = "Achilles"
    private const val UPDATED_AT = "2021-12-20T20:39:18.033Z"
    const val ERROR_MESSAGE = "Unexpected error occurred!"
    const val TITLE = "Base Screen"

}