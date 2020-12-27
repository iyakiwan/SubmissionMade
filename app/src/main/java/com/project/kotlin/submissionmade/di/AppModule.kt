package com.project.kotlin.submissionmade.di

import com.project.kotlin.submissionmade.core.domain.usecase.MovieInteractor
import com.project.kotlin.submissionmade.core.domain.usecase.MovieUseCase
import com.project.kotlin.submissionmade.ui.detail.DetailViewModel
import com.project.kotlin.submissionmade.ui.favorite.FavoriteViewModel
import com.project.kotlin.submissionmade.ui.movie.MovieViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}