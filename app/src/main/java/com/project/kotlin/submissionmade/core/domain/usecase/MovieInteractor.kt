package com.project.kotlin.submissionmade.core.domain.usecase

import com.project.kotlin.submissionmade.core.data.Resource
import com.project.kotlin.submissionmade.core.domain.model.Movie
import com.project.kotlin.submissionmade.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor (private val movieRepository: IMovieRepository): MovieUseCase {

    override fun getAllMovies(): Flow<Resource<List<Movie>>> = movieRepository.getAllMovies()

    override fun getDetailMovie(movieId: String?): Flow<Movie> = movieRepository.getDetailMovie(movieId)

    override fun getFavoriteMovie(): Flow<List<Movie>> = movieRepository.getFavoriteMovie()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) = movieRepository.setFavoriteMovie(movie, state)
}