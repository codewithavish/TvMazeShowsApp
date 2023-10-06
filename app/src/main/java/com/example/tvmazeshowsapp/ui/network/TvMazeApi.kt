package com.example.tvmazeshowsapp.ui.network

import com.example.tvmazeshowsapp.ui.models.TvMazeResponse
import retrofit2.Response
import retrofit2.http.GET

interface TvMazeApi {
    @GET("shows")
    suspend fun getTvShows(): Response<TvMazeResponse>
}