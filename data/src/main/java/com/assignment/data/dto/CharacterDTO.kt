package com.assignment.data.dto

import com.google.gson.annotations.SerializedName

data class CharacterDTO(
    @SerializedName("_id") val id: Int, // _id is not a recommend field declaration syntax in kotlin, that why did this.
    val createdAt: String,
    val films: List<String>,
    val imageUrl: String?, // making it nullable because in open api, it gets null for few records.
    val name: String,
    val sourceUrl: String,
    val tvShows: List<String>,
    val updatedAt: String,
    val url: String
)