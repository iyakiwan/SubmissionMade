package com.project.kotlin.submissionmade.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResponse(

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("original_language")
	val originalLanguage: String,

	@field:SerializedName("original_title")
	val originalTitle: String,

	@field:SerializedName("video")
	val video: Boolean,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("genre_ids")
	val genreIds: List<Int>,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("backdrop_path")
	val backdropPath: String,

	@field:SerializedName("release_date")
	val releaseDate: String,

	@field:SerializedName("popularity")
	val popularity: Double,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("adult")
	val adult: Boolean,

	@field:SerializedName("vote_count")
	val voteCount: Int
) : Parcelable
