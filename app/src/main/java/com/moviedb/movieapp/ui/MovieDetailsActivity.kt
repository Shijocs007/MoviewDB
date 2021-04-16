package com.moviedb.movieapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.moviedb.movieapp.R
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        intent.extras?.let {
            Glide.with(this)
                .load(it.getString("poster", ""))
                .into(movie_poster);
            movie_title.text = it.getString("title", "")
            release_date.text = it.getString("release_date", "")
            overview.text = it.getString("overview", "")
        }
    }
}