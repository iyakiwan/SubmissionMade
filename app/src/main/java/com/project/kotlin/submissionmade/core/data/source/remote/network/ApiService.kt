package com.project.kotlin.submissionmade.core.data.source.remote.network

import com.project.kotlin.submissionmade.core.data.source.remote.response.ListMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    //Get Data Movie
    @GET("movie/popular")
    fun getListMovie(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("page") page: String?
    ): Call<ListMovieResponse>

    //Get Data Detail Movie
    /*@GET("movie/{movieId}")
    fun getDetailMovie(
        @Path("movieId") movieId: String?,
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?
    ): Call<DetailMovieResponse?>?*/
}
