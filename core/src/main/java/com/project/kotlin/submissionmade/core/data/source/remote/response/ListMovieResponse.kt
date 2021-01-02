package com.project.kotlin.submissionmade.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListMovieResponse(
    @field:SerializedName("results")
    val results: List<MovieResponse>
) : Parcelable