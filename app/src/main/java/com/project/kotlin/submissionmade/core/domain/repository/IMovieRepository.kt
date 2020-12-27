package com.project.kotlin.submissionmade.core.domain.repository

import androidx.lifecycle.LiveData
import com.project.kotlin.submissionmade.core.data.Resource
import com.project.kotlin.submissionmade.core.domain.model.Movie

interface IMovieRepository {
    fun getAllMovies(): LiveData<Resource<List<Movie>>>

    fun getDetailMovie(movieId: String?): LiveData<Movie>

    fun getFavoriteMovie(): LiveData<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)
}