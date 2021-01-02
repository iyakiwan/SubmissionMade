package com.project.kotlin.submissionmade.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var movieId: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "poster")
    var poster: String,

    @ColumnInfo(name = "rating")
    var rating: String,

    @ColumnInfo(name = "release")
    var release: String,

    @ColumnInfo(name = "language")
    var language: String,

    @ColumnInfo(name = "adult")
    var adult: Boolean,

    @ColumnInfo(name = "popularity")
    var popularity: String,

    @ColumnInfo(name = "backdrop")
    var backdrop: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)
