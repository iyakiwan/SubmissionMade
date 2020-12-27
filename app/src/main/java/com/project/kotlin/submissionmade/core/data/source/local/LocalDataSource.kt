package com.project.kotlin.submissionmade.core.data.source.local

import androidx.lifecycle.LiveData
import com.project.kotlin.submissionmade.core.data.source.local.entity.MovieEntity
import com.project.kotlin.submissionmade.core.data.source.local.room.MovieDao

class LocalDataSource private constructor(private val movieDao: MovieDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(movieDao)
            }
    }

    fun getAllMovie(): LiveData<List<MovieEntity>> = movieDao.getAllMovie()

    fun getDetailMovie(movieId: String?): LiveData<MovieEntity> = movieDao.getDetailMovie(movieId)

    fun getFavoriteMovie(): LiveData<List<MovieEntity>> = movieDao.getFavoriteMovie()

    fun insertMovie(MovieList: List<MovieEntity>) = movieDao.insertMovie(MovieList)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }
}