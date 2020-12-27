package com.project.kotlin.submissionmade.core.utils

import com.project.kotlin.submissionmade.core.data.source.local.entity.MovieEntity
import com.project.kotlin.submissionmade.core.data.source.remote.response.MovieResponse

object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                movieId = it.id.toString(),
                title = it.title,
                poster = it.posterPath,
                rating = it.voteAverage.toString(),
                release = it.releaseDate,
                language = it.originalLanguage,
                adult = it.adult,
                popularity = it.popularity.toString(),
                backdrop = it.backdropPath,
                overview = it.overview,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }
}