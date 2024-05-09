package com.assignment.domain.entities

data class CharacterEntity(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val films: List<String>,
    val tvShows: List<String>,
    val createdAt: String,
    val updatedAt: String
)