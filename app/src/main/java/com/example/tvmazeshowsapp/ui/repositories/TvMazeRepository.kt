package com.example.tvmazeshowsapp.ui.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.tvmazeshowsapp.ui.network.TvMazeApi
import com.example.tvmazeshowsapp.ui.utils.DataWrapper
import java.lang.Exception
import javax.inject.Inject

class TvMazeRepository @Inject constructor(private val apiService: TvMazeApi) {

    suspend fun getTvMazeShows() = apiService.getTvShows()

}
