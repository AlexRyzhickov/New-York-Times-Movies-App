package com.atex.nytimesmovies.entities

data class MovieList(
    val status: String?,
    val copyrightString: String?,
    val has_more: Boolean?,
    val num_results: String?,
    val results: List<Movie>
)

data class Movie(val display_title: String?, val multimedia: Multimedia?)

data class Multimedia(val src: String?)