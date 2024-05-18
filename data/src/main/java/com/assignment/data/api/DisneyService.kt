package com.assignment.data.api

import com.assignment.data.dto.CharacterDTO
import com.assignment.data.dto.CharacterDetailsDTO
import com.assignment.data.dto.CharactersListDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Api service to fetch data from the server.
 */
interface DisneyService {

    /**
     * To get the characters list from the server.
     *
     * @return [CharactersListDTO] data module object.
     */
    @GET("character")
    suspend fun getDisneyCharactersList(): Response<CharactersListDTO>

    /**
     * To get the details of the character selected.
     *
     * @param id id of the character selected.
     *
     * @return [CharacterDTO] data module object.
     */
    @GET("character/{id}")
    suspend fun getDisneyCharacterDetails(@Path("id") id: Int): Response<CharacterDetailsDTO>
}