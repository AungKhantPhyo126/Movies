package com.aungkhantphyo.movies.network

import com.aungkhantphyo.movies.network.responses.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey:String,
        @Query("page") pageNumber: Int
    ): Response<MovieResponse>

    @GET("popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") pageNumber: Int
    ):Response<MovieResponse>
}