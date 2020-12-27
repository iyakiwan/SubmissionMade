package com.project.kotlin.submissionmade.core.di

import android.content.Context
import com.project.kotlin.submissionmade.core.data.MovieRepository
import com.project.kotlin.submissionmade.core.data.source.local.LocalDataSource
import com.project.kotlin.submissionmade.core.data.source.local.room.MovieDatabase
import com.project.kotlin.submissionmade.core.data.source.remote.RemoteDataSource
import com.project.kotlin.submissionmade.core.data.source.remote.network.ApiConfig
import com.project.kotlin.submissionmade.core.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): MovieRepository {
        val database = MovieDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()

        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}
