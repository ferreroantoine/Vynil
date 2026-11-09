package com.example.myapplication.data.repository

import com.example.myapplication.data.Track
import com.example.myapplication.datasource.api.DeezerService
import com.example.myapplication.datasource.api.NetworkDataSource

interface TrackRepository {
    suspend fun getTracks(): List<Track>
    suspend fun getTrack(idTrack: Long): Track
    suspend fun searchTracks(query: String): List<Track>
}

class TrackRepositoryImpl(
    private val deezerService: DeezerService = NetworkDataSource.deezerService
) : TrackRepository {

    override suspend fun getTracks(): List<Track> =
        deezerService.getTracks().trackList

    override suspend fun getTrack(idTrack: Long): Track =
        deezerService.getTrack(idTrack)

    override suspend fun searchTracks(query: String): List<Track> =
        deezerService.searchTracks(query).trackList.distinctBy { it.id }
}
