package com.project.kotlin.submissionmade.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val movieId: String,
    val title: String,
    val poster: String,
    val rating: String,
    val release: String,
    val language: String,
    val adult: Boolean,
    val popularity: String,
    val backdrop: String,
    val overview: String,
    val isFavorite: Boolean
) : Parcelable