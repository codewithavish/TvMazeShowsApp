package com.example.tvmazeshowsapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tvmazeshowsapp.R
import com.example.tvmazeshowsapp.databinding.ActivityMainBinding
import com.example.tvmazeshowsapp.ui.adapters.TvMazeShowAdapter
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
        viewModel.responseTvMazeShow.observe(this) { listTvShows ->
            activityMainBinding.rvEpisodes.apply {
                layoutManager = LinearLayoutManager(
                    this@MainActivity, LinearLayoutManager.HORIZONTAL,
                    false
                )
                val tvMazeShowAdapter = TvMazeShowAdapter(listTvShows)
                adapter = tvMazeShowAdapter

            }
        }
    }
}
