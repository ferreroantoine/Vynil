package com.example.myapplication.datasource.api

import com.example.myapplication.data.Track
import com.example.myapplication.data.Tracks
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DeezerService {

    @GET("chart/0/tracks")
    suspend fun getTracks(): Tracks

    @GET("track/{id}")
    suspend fun getTrack(@Path("id") idTrack: Long): Track

    @GET("search")
    suspend fun searchTracks(@Query("q") query: String): Tracks

}
