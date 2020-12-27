package com.project.kotlin.submissionmade.core.domain.usecase

import androidx.lifecycle.LiveData
import com.project.kotlin.submissionmade.core.data.MovieRepository
import com.project.kotlin.submissionmade.core.data.Resource
import com.project.kotlin.submissionmade.core.domain.model.Movie

class MovieInteractor (private val movieRepository: MovieRepository): MovieUseCase {

    override fun getAllMovies(): LiveData<Resource<List<Movie>>> = movieRepository.getAllMovies()

    override fun getDetailMovie(movieId: String?): LiveData<Movie> = movieRepository.getDetailMovie(movieId)

    override fun getFavoriteMovie(): LiveData<List<Movie>> = movieRepository.getFavoriteMovie()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) = movieRepository.setFavoriteMovie(movie, state)
}