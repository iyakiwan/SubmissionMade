package com.project.kotlin.submissionmade.core.domain.usecase


import com.project.kotlin.submissionmade.core.data.MovieRepository
import com.project.kotlin.submissionmade.core.data.Resource
import com.project.kotlin.submissionmade.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

class MovieInteractor (private val movieRepository: MovieRepository): MovieUseCase {

    override fun getAllMovies(): Flow<Resource<List<Movie>>> = movieRepository.getAllMovies()

    override fun getDetailMovie(movieId: String?): Flow<Movie> = movieRepository.getDetailMovie(movieId)

    override fun getFavoriteMovie(): Flow<List<Movie>> = movieRepository.getFavoriteMovie()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) = movieRepository.setFavoriteMovie(movie, state)
}