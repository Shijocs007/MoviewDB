package com.moviedb.movieapp.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.moviedb.movieapp.viewmodels.MovieViewModel
import com.moviedb.movieapp.R
import com.moviedb.movieapp.adapter.MoviePagedListAdapter
import com.moviedb.movieapp.databinding.ActivityMovieListBinding
import com.moviedb.movieapp.network.NetworkState
import com.moviedb.movieapp.utils.SpacingItemDecoration
import com.moviedb.movieapp.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieListActivity : AppCompatActivity() {

    private  val TAG = "MovieListActivity"

    private val viewModel : MovieViewModel by viewModels()
    lateinit var binding : ActivityMovieListBinding

    @Inject
    lateinit var movieAdapter : MoviePagedListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_movie_list
        )
        binding.viewmodel = viewModel


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
    }

}
