package com.project.kotlin.submissionmade.core.data

import androidx.lifecycle.LiveData
import com.project.kotlin.submissionmade.core.data.source.local.LocalDataSource
import com.project.kotlin.submissionmade.core.data.source.local.entity.MovieEntity
import com.project.kotlin.submissionmade.core.data.source.remote.RemoteDataSource
import com.project.kotlin.submissionmade.core.data.source.remote.network.ApiResponse
import com.project.kotlin.submissionmade.core.data.source.remote.response.MovieResponse
import com.project.kotlin.submissionmade.core.utils.AppExecutors
import com.project.kotlin.submissionmade.core.utils.DataMapper

class MovieRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) {

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteData, localData, appExecutors)
            }
    }

    fun getAllMovies(): LiveData<Resource<List<MovieEntity>>> =
        object : NetworkBoundResource<List<MovieEntity>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<MovieEntity>> {
                return localDataSource.getAllMovie()
            }

            override fun shouldFetch(data: List<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovie()

            override fun saveCallResult(data: List<MovieResponse>) {
                val tourismList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(tourismList)
            }
        }.asLiveData()

    fun getDetailMovie(movieId: String?): LiveData<MovieEntity> {
        return localDataSource.getDetailMovie(movieId)
    }

    fun getFavoriteMovie(): LiveData<List<MovieEntity>> {
        return localDataSource.getFavoriteMovie()
    }

    fun setFavoriteMovie(tourism: MovieEntity, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(tourism, state) }
    }
}