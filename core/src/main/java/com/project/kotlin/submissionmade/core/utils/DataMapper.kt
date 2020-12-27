package com.project.kotlin.submissionmade.core.utils

import com.project.kotlin.submissionmade.core.data.source.local.entity.MovieEntity
import com.project.kotlin.submissionmade.core.data.source.remote.response.MovieResponse
import com.project.kotlin.submissionmade.core.domain.model.Movie

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

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                movieId = it.movieId,
                title = it.title,
                poster = it.poster,
                rating = it.rating,
                release = it.release,
                language = it.language,
                adult = it.adult,
                popularity = it.popularity,
                backdrop = it.backdrop,
                overview = it.overview,
                isFavorite = it.isFavorite
            )
        }

    fun mapEntityToDomain(input: MovieEntity): Movie =
        Movie(
            movieId = input.movieId,
            title = input.title,
            poster = input.poster,
            rating = input.rating,
            release = input.release,
            language = input.language,
            adult = input.adult,
            popularity = input.popularity,
            backdrop = input.backdrop,
            overview = input.overview,
            isFavorite = input.isFavorite
        )


    fun mapDomainToEntity(input: Movie) = MovieEntity(
        movieId = input.movieId,
        title = input.title,
        poster = input.poster,
        rating = input.rating,
        release = input.release,
        language = input.language,
        adult = input.adult,
        popularity = input.popularity,
        backdrop = input.backdrop,
        overview = input.overview,
        isFavorite = input.isFavorite
    )
}