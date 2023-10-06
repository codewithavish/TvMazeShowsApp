package com.example.tvmazeshowsapp.ui.di

import com.example.tvmazeshowsapp.ui.network.TvMazeApi
import com.example.tvmazeshowsapp.ui.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit() : Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesNasaApi(retrofit: Retrofit) : TvMazeApi{
        return retrofit.create(TvMazeApi::class.java)
    }

}