package com.example.myapplication.data_layer.repo

import com.example.myapplication.R
import com.example.myapplication.data_layer.model.MoviesResponse
import com.example.myapplication.utils.App
import com.example.myapplication.utils.Constants.BASE_URL
import com.example.myapplication.utils.NetworkUtils
import com.example.myapplication.utils.ResponseResource
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception


class ConnectionApi {

    private lateinit var apiService: ApiService

    suspend fun getMovies(): ResponseResource<MoviesResponse>? {
        apiService = APIClient.getClient()?.create(ApiService::class.java)!!
        var data: ResponseResource<MoviesResponse>? = null

        val url = BASE_URL + "?method=flickr.photos.search&format=json&nojsoncallback=50&text=Color&page=1&" +
                "per_page=20&api_key=d17378e37e555ebef55ab86c4180e8dc"

        if (NetworkUtils.isInternetAvailable()) {
            data = try {
                val response = withContext(Dispatchers.IO) { apiService.getMovies(url).await() }
                ResponseResource.success(response)
            } catch (e: Exception) {
                ResponseResource.error(
                    App.context!!.resources.getString(R.string.network_error),
                    null,
                    400
                )
            }
        } else{
            data = ResponseResource.error(
                App.context!!.resources.getString(R.string.no_internet),
                null,
                500
            )
        }

        return data
    }
}