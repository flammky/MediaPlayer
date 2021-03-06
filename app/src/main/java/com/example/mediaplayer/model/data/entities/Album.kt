package com.example.mediaplayer.model.data.entities

data class Album(
    val name: String,
    val song: List<Song>,
    val duration: Long = 0L
)
