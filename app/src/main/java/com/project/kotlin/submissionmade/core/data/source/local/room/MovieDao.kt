package com.project.kotlin.submissionmade.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.project.kotlin.submissionmade.core.data.source.local.entity.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAllMovie(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movie where isFavorite = 1")
    fun getFavoriteMovie(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE id = :movieId")
    fun getDetailMovie(movieId: String?): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(Movie: List<MovieEntity>)

    @Update
    fun updateFavoriteMovie(Movie: MovieEntity)
}