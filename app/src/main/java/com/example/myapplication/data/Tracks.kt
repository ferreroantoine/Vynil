package com.example.myapplication.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Tracks(@SerialName("data") val trackList: List<Track>)

@Serializable
data class Track(
    val id: Long,
    val title: String,
    val preview: String? = null,
    val duration: Int? = null,
    val artist: Artist? = null,
    val album: Album? = null
)

@Serializable
data class Artist(
    val id: Long,
    val name: String,
    @SerialName("picture_medium") val pictureMedium: String? = null
)

@Serializable
data class Album(
    val id: Long,
    val title: String,
    @SerialName("cover_medium") val coverMedium: String? = null
)
