package com.project.kotlin.submissionmade.core.data.source.remote.network

import com.project.kotlin.submissionmade.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    //Get Data Movie
    @GET("movie/popular")
    suspend fun getListMovie(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("page") page: String?
    ): ListMovieResponse
}
