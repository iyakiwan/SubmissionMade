package com.project.kotlin.submissionmade.ui.favorite

import androidx.lifecycle.ViewModel
import com.project.kotlin.submissionmade.core.data.MovieRepository

class FavoriteViewModel(movieRepository: MovieRepository) : ViewModel() {
    val favoriteMovie = movieRepository.getFavoriteMovie()
}