package com.aungkhantphyo.movies.network.responses

import com.aungkhantphyo.movies.network.dto.MoviesDto
import com.squareup.moshi.Json

data class MovieResponse(
    val page:Int,
    val results:List<MoviesDto>,
    @field:Json(name = "total_pages")
    val totalPages:Int?
)
