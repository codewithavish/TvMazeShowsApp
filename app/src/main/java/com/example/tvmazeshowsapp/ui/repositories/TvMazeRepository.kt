package com.example.tvmazeshowsapp.ui.repositories

import com.example.tvmazeshowsapp.ui.models.TvMazeResponse
import com.example.tvmazeshowsapp.ui.network.TvMazeApi
import com.example.tvmazeshowsapp.ui.utils.DataWrapper
import com.google.gson.JsonParseException
import java.io.IOException
import javax.inject.Inject

class TvMazeRepository @Inject constructor(private val apiService: TvMazeApi) {

    suspend fun getTvMazeShows(): DataWrapper<TvMazeResponse> {
        return try {
            val response = apiService.getTvShows()
            if (response.isSuccessful) {
                val shows = response.body()
                if (shows != null) {
                    DataWrapper.Success(shows)
                } else {
                    DataWrapper.Error("Empty response body")
                }
            } else {
                DataWrapper.Error("Error ${response.code()}: ${response.message()}")
            }
        } catch (e: IOException) {
            DataWrapper.Error("Network error: ${e.message}")
        } catch (e: JsonParseException) {
            DataWrapper.Error("JSON parsing error: ${e.message}")
        } catch (e: Exception) {
            DataWrapper.Error("Failed to fetch TV shows: ${e.message}")
        }
    }

}
