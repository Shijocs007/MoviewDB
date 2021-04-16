package com.moviedb.movieapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * class used as retrofit http response for movie and,
 * entity for room database
 * */
@Entity
data class Movie(
    @Expose @SerializedName("popularity") val popularity : Double?,
    @Expose @SerializedName("vote_count") val vote_count : Int?,
    @Expose @SerializedName("poster_path") val poster_path : String?,
    @Expose @PrimaryKey(autoGenerate = false) @SerializedName("id") val id : Int?,
    @Expose @SerializedName("adult") val adult : Boolean?,
    @Expose @SerializedName("backdrop_path") val backdrop_path : String?,
    @Expose @SerializedName("original_language") val original_language : String?,
    @Expose @SerializedName("original_title") val original_title : String?,
    @Expose @SerializedName("title") val title : String?,
    @Expose @SerializedName("overview") val overview : String?,
    @Expose @SerializedName("release_date") val release_date : String?
)