package com.example.movierow.models

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class Favorites : ViewModel() {

    private val favorites = mutableStateListOf<Movie>()

    val favs: List<Movie> get() = favorites

    fun checkMovie(movie: Movie): Boolean{
        return favorites.any { m -> m.id == movie.id }
    }

    fun add(movie: Movie) {
        if (checkMovie(movie) == false){
            favorites.add(movie)
        }
    }

    fun delete(movie: Movie){
        favorites.remove(movie)
    }

}