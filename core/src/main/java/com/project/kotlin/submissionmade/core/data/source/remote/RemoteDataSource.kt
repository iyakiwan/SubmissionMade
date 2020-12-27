package com.project.kotlin.submissionmade.core.data.source.remote

import android.util.Log
import com.project.kotlin.submissionmade.core.data.source.remote.network.ApiResponse
import com.project.kotlin.submissionmade.core.data.source.remote.network.ApiService
import com.project.kotlin.submissionmade.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    companion object {
        private const val LANGUAGE = "en-US"
        private const val API_KEY = "7b4a111dae6877683996f90a8ea25891"
        private const val PAGE = "1"
    }

    suspend fun getAllMovie(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow{
            try {
                val response = apiService.getListMovie(API_KEY, LANGUAGE, PAGE)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}