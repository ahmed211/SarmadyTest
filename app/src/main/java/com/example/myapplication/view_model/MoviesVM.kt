package com.example.myapplication.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data_layer.model.MoviesResponse
import com.example.myapplication.data_layer.model.Photo
import com.example.myapplication.data_layer.repo.ConnectionApi
import com.example.myapplication.utils.ResponseResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesVM : ViewModel() {

    var response = MutableLiveData<ResponseResource<MoviesResponse>>()

    fun getMovies(){
        val repo = ConnectionApi()
        viewModelScope.launch {
            val photo =  Photo("", "", "","", 0,
                "", 0, 0, 0, 1)
            val data = repo.getMovies()
            if (data?.status == 200) {
                val movies = data.data?.photos?.photo
                for (i in 1..movies?.size!!) {
                    if (i % 5 == 0) {
                        movies.add(i - 1, photo)
                    }
                }
            }
            response.value = data
        }
    }
}