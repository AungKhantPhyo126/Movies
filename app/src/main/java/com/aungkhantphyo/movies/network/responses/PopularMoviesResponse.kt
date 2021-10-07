package com.aungkhantphyo.movies.network.responses

import com.aungkhantphyo.movies.network.dto.PopularMoviesDto
import com.aungkhantphyo.movies.network.dto.UpcomingMoviesDto

data class PopularMoviesResponse(
    val page:Int,
    val results:List<PopularMoviesDto>
)
