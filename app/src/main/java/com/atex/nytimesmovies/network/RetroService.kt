package com.atex.nytimesmovies.network

import com.atex.nytimesmovies.entities.MovieList
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {
    @GET("all.json")
    suspend fun getDataFromAPI(@Query("offset") offset: Int, @Query("api-key") key: String): MovieList
}