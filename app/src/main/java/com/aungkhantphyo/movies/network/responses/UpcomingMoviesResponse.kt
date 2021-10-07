package com.aungkhantphyo.movies.network.responses

import com.aungkhantphyo.movies.network.dto.UpcomingMoviesDto
import com.squareup.moshi.Json

data class UpcomingMoviesResponse(
    val page:Int,
    val results:List<UpcomingMoviesDto>,
    @field:Json(name = "total_pages")
    val totalPages:Int?
)
