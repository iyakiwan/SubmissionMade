package com.project.kotlin.submissionmade.core.data

import com.project.kotlin.submissionmade.core.data.source.local.LocalDataSource
import com.project.kotlin.submissionmade.core.data.source.remote.RemoteDataSource
import com.project.kotlin.submissionmade.core.data.source.remote.network.ApiResponse
import com.project.kotlin.submissionmade.core.data.source.remote.response.MovieResponse
import com.project.kotlin.submissionmade.core.domain.model.Movie
import com.project.kotlin.submissionmade.core.domain.repository.IMovieRepository
import com.project.kotlin.submissionmade.core.utils.AppExecutors
import com.project.kotlin.submissionmade.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    override fun getAllMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovie().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovie()

            override suspend  fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()

    override fun getDetailMovie(movieId: String?): Flow<Movie> {
        return localDataSource.getDetailMovie(movieId).map { DataMapper.mapEntityToDomain(it) }
    }

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }
}