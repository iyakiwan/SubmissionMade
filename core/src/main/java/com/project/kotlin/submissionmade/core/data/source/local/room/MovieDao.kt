package com.project.kotlin.submissionmade.core.data.source.local.room

import androidx.room.*
import com.project.kotlin.submissionmade.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAllMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie where isFavorite = 1")
    fun getFavoriteMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE id = :movieId")
    fun getDetailMovie(movieId: String?): Flow<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(Movie: List<MovieEntity>)

    @Update
    fun updateFavoriteMovie(Movie: MovieEntity)
}