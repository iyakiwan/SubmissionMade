package com.project.kotlin.submissionmade.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.kotlin.submissionmade.core.data.MovieRepository
import com.project.kotlin.submissionmade.core.di.Injection
import com.project.kotlin.submissionmade.ui.detail.DetailViewModel
import com.project.kotlin.submissionmade.ui.favorite.FavoriteViewModel
import com.project.kotlin.submissionmade.ui.movie.MovieViewModel

class ViewModelFactory private constructor(private val movieRepository: MovieRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance
                ?: synchronized(this) {
                instance
                    ?: ViewModelFactory(
                        Injection.provideRepository(
                            context
                        )
                    )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(movieRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(movieRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(movieRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}