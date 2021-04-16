package com.moviedb.movieapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moviedb.movieapp.models.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(movies : List<Movie>)

    @Query("SELECT * FROM movie LIMIT :limit OFFSET :offset")
    suspend fun getMovies(limit : Int, offset : Int) : List<Movie>
}