package com.assignment.data.fakes

import com.assignment.data.dto.CharacterDTO
import com.assignment.data.dto.CharacterDetailsDTO
import com.assignment.data.dto.CharactersListDTO
import com.assignment.domain.entities.CharacterEntity
import com.assignment.domain.entities.CharacterListEntity

object FakeData {

    fun getCharactersListDTO(): CharactersListDTO {
        return CharactersListDTO(buildList {
            add(getCharacterDTO())
        })
    }

    fun getCharacterDTO(): CharacterDTO {
        return CharacterDTO(
            id = ID,
            createdAt = CREATED_AT,
            films = FILMS,
            imageUrl = IMAGE_URL,
            name = NAME,
            sourceUrl = SOURCE_URL,
            tvShows = TV_SHOWS,
            updatedAt = UPDATED_AT,
            url = URL
        )
    }

    fun getCharacterDetailsDTO(): CharacterDetailsDTO {
        return CharacterDetailsDTO(getCharacterDTO())
    }

    fun getCharacterListEntity(): CharacterListEntity {
        return CharacterListEntity(buildList {
            add(getCharacterEntity())
        })
    }

    fun getCharacterEntity(): CharacterEntity {
        return CharacterEntity(
            id = ID,
            name = NAME,
            imageUrl = IMAGE_URL,
            films = FILMS,
            tvShows = TV_SHOWS,
            createdAt = CREATED_AT,
            updatedAt = UPDATED_AT
        )
    }

    private const val ID = 1
    private const val CREATED_AT = "2021-04-12T01:31:30.547Z"
    private val FILMS = buildList { add("Hercules (film)") }
    private const val IMAGE_URL =
        "https://static.wikia.nocookie.net/disney/images/d/d3/Vlcsnap-2015-05-06-23h04m15s601.png"
    private const val NAME = "Achilles"
    private const val SOURCE_URL = "https://disney.fandom.com/wiki/Achilles_(Hercules)"
    private val TV_SHOWS = buildList { add("Hercules (TV series)") }
    private const val UPDATED_AT = "2021-12-20T20:39:18.033Z"
    private const val URL = "https://api.disneyapi.dev/characters/112"
}

