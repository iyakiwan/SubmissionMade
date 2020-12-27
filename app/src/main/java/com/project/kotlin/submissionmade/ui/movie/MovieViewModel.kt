package com.project.kotlin.submissionmade.ui.movie

import androidx.lifecycle.ViewModel
import com.project.kotlin.submissionmade.core.domain.usecase.MovieUseCase

class MovieViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val listMovies = movieUseCase.getAllMovies()
}