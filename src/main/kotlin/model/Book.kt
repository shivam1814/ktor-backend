package com.example.model

import kotlinx.serialization.*

@Serializable
data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val publications: String
)
