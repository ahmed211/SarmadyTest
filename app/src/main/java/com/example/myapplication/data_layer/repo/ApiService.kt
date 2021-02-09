package com.example.myapplication.data_layer.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data_layer.model.MoviesResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET
    fun getMovies(@Url url: String): Deferred<MoviesResponse>
}