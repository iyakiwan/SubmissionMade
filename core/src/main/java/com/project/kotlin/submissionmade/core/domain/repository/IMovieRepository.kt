package com.project.kotlin.submissionmade.core.domain.repository

import com.project.kotlin.submissionmade.core.data.Resource
import com.project.kotlin.submissionmade.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getAllMovies(): Flow<Resource<List<Movie>>>

    fun getDetailMovie(movieId: String?): Flow<Movie>

    fun getFavoriteMovie(): Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)
}