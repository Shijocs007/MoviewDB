package com.moviedb.movieapp.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.moviedb.movieapp.R
import com.moviedb.movieapp.adapter.MoviePagedListAdapter
import com.moviedb.movieapp.databinding.ActivityMovieListBinding
import com.moviedb.movieapp.models.Movie
import com.moviedb.movieapp.network.NetworkState
import com.moviedb.movieapp.utils.SpacingItemDecoration
import com.moviedb.movieapp.utils.Utils
import com.moviedb.movieapp.utils.Utils.KEY_SORT
import com.moviedb.movieapp.viewmodels.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MovieListActivity : AppCompatActivity() {

    private  val TAG = "MovieListActivity"

    private val viewModel : MovieViewModel by viewModels()
    lateinit var binding : ActivityMovieListBinding

    @Inject
    lateinit var movieAdapter : MoviePagedListAdapter

    @Inject
    lateinit var preferences: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_movie_list
        )
        binding.viewmodel = viewModel

      //  setSupportActionBar(binding.toolbar)
       // supportActionBar?.title = "Movie DB"
        binding.toolbar.title = "Movie DB"
        binding.toolbar.inflateMenu(R.menu.main_manu);

        val gridLayoutManager = GridLayoutManager(this, 3)

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val viewType = movieAdapter.getItemViewType(position)
                if (viewType == movieAdapter.MOVIE_VIEW_TYPE) return  1    // Movie_VIEW_TYPE will occupy 1 out of 3 span
                else return 3                                              // NETWORK_VIEW_TYPE will occupy all 3 span
            }
        }

        binding.recyclerView.layoutManager = gridLayoutManager
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.addItemDecoration(
            SpacingItemDecoration(
                3,
                Utils.dpToPx(this, 2),
                true
            )
        )
        binding.recyclerView.adapter = movieAdapter

        viewModel.moviePagedList.observe(this, Observer {
            val test = it
            movieAdapter.submitList(it)
        })

        viewModel.networkState.observe(this, Observer {
            binding.mainProgressBar.visibility = if (viewModel.listIsEmpty() && it == NetworkState.LOADING) View.VISIBLE else View.GONE
            binding.errorMessage.visibility = if (viewModel.listIsEmpty() && (it == NetworkState.ERROR || it ==NetworkState.NO_INTERNET)) View.VISIBLE else View.GONE

            if (!viewModel.listIsEmpty()) {
                movieAdapter.setNetworkState(it)
            }
        })

        binding.toolbar.setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener { item ->

            when(item.itemId) {
                R.id.title -> preferences.putString(KEY_SORT, "original_title.asc")
                R.id.date -> preferences.putString(KEY_SORT, "primary_release_date.desc")
                R.id.popularity -> preferences.putString(KEY_SORT, "popularity.desc")
                R.id.rating -> preferences.putString(KEY_SORT, "vote_average.desc")
            }
            preferences.commit()
            viewModel.fetchSortedMovieList()
            false
        })
    }

}
