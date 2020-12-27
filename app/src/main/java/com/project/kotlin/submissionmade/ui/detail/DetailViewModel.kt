package com.project.kotlin.submissionmade.ui.detail

import androidx.lifecycle.ViewModel
import com.project.kotlin.submissionmade.core.data.MovieRepository
import com.project.kotlin.submissionmade.core.data.source.local.entity.MovieEntity

class DetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun setFavoriteMovie(movie: MovieEntity, newStatus:Boolean) = movieRepository.setFavoriteMovie(movie, newStatus)

    fun getDetailMovie(movieId: String) = movieRepository.getDetailMovie(movieId)
}