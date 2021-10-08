package com.aungkhantphyo.movies.domain

data class Movie(
    val id:String,
    val title:String,
    val posterPath:String,
    val type:String,
    val overview:String,
    val isFav:Boolean
)