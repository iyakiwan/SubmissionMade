package com.project.kotlin.submissionmade.core.data.source.local

import com.project.kotlin.submissionmade.core.data.source.local.entity.MovieEntity
import com.project.kotlin.submissionmade.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource private constructor(private val movieDao: MovieDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(movieDao)
            }
    }

    fun getAllMovie(): Flow<List<MovieEntity>> = movieDao.getAllMovie()

    fun getDetailMovie(movieId: String?): Flow<MovieEntity> = movieDao.getDetailMovie(movieId)

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovie()

    suspend fun insertMovie(MovieList: List<MovieEntity>) = movieDao.insertMovie(MovieList)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }
}