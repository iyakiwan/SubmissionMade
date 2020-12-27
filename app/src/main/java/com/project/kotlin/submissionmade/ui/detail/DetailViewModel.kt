package com.project.kotlin.submissionmade.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.project.kotlin.submissionmade.core.domain.model.Movie
import com.project.kotlin.submissionmade.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: Movie, newStatus:Boolean) = movieUseCase.setFavoriteMovie(movie, newStatus)

    fun getDetailMovie(movieId: String) = movieUseCase.getDetailMovie(movieId).asLiveData()
}