package com.example.tvmazeshowsapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvmazeshowsapp.ui.models.TvMazeResponseItem
import com.example.tvmazeshowsapp.ui.repositories.TvMazeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvMazeViewModel @Inject constructor(
    private val tvMazeRepository: TvMazeRepository): ViewModel() {

    private val _response = MutableLiveData<List<TvMazeResponseItem>>()
    val responseTvMazeShow: LiveData<List<TvMazeResponseItem>>
    get() = _response

    init {
        getAllTvShows()
    }

     private fun getAllTvShows() = viewModelScope.launch {
        tvMazeRepository.getTvMazeShows().let { response ->
            if (response.isSuccessful){
                response.body()

                _response.postValue(response.body())
            }else{
                Log.d("tag", "getAllTvShows Error: ${response.code()}")
            }
        }
    }
}