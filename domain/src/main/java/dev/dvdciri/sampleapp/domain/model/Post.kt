package dev.dvdciri.sampleapp.domain.model

data class Post(
    val id: Int,
    val authorId: Int,
    val title: String,
    val body: String
)