package com.moviedb.movieapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moviedb.movieapp.models.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao() : MovieDao

    companion object {
        val DATABASE_NAME: String = "movie_db"
    }
}