package com.assignment.presentation.models

data class Character(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val films: List<String>,
    val createdAt: String,
    val updatedAt: String
)