package com.project.kotlin.submissionmade.ui.movie

import androidx.lifecycle.ViewModel
import com.project.kotlin.submissionmade.core.data.MovieRepository

class MovieViewModel(movieRepository: MovieRepository) : ViewModel() {
    val listMovies = movieRepository.getAllMovies()
}