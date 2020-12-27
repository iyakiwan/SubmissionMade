package com.project.kotlin.submissionmade.ui.favorite

import androidx.lifecycle.ViewModel
import com.project.kotlin.submissionmade.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val favoriteMovie = movieUseCase.getFavoriteMovie()
}