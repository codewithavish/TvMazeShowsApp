package com.example.tvmazeshowsapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tvmazeshowsapp.R
import com.example.tvmazeshowsapp.databinding.ActivityMainBinding
import com.example.tvmazeshowsapp.ui.adapters.TvMazeShowAdapter
import com.example.tvmazeshowsapp.ui.models.TvMazeResponseItem
import com.example.tvmazeshowsapp.ui.utils.DataWrapper
import com.example.tvmazeshowsapp.ui.viewmodels.TvMazeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private val viewModel: TvMazeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        initialiseData()
    }

    private fun initialiseData() {
        viewModel.tvMazeResponse.observe(this) { dataWrapper ->
            when (dataWrapper) {
                is DataWrapper.Loading -> {
                    // Show loading indicator or handle loading state
                }

                is DataWrapper.Success -> {
                    val listTvShows = dataWrapper.data ?: emptyList()
                    setupRecyclerView(listTvShows)
                }

                is DataWrapper.Error -> {
                    val errorMessage = dataWrapper.errorMessage ?: getString(R.string.default_error_message)
                }
            }
        }
    }

    private fun setupRecyclerView(listTvShows: List<TvMazeResponseItem>) {
        activityMainBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.HORIZONTAL,
                false
            )
            val tvMazeShowAdapter = TvMazeShowAdapter(listTvShows)
            adapter = tvMazeShowAdapter
        }
    }
}
