package com.example.tvmazeshowsapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvmazeshowsapp.ui.models.TvMazeResponse
import com.example.tvmazeshowsapp.ui.repositories.TvMazeRepository
import com.example.tvmazeshowsapp.ui.utils.DataWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvMazeViewModel @Inject constructor(
    private val tvMazeRepository: TvMazeRepository): ViewModel() {

    private val _tvMazeResponse = MutableLiveData<DataWrapper<TvMazeResponse>>()
    val tvMazeResponse: LiveData<DataWrapper<TvMazeResponse>>
        get() = _tvMazeResponse

    init {
        getAllTvShows()
    }

     private fun getAllTvShows() {
         _tvMazeResponse.value = DataWrapper.Loading()
         viewModelScope.launch {
             try {
                 val response = tvMazeRepository.getTvMazeShows()
                 _tvMazeResponse.value = DataWrapper.Success(response.data)
             } catch (e: Exception) {
                 _tvMazeResponse.value = DataWrapper.Error("Failed to fetch TV shows: ${e.message}")
                 Log.e("TvMazeViewModel", "Error fetching TV shows", e)
             }
         }
     }
}