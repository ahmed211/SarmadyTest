package com.example.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.utils.NetworkUtils.isInternetAvailable
import com.example.myapplication.view_model.MoviesVM

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var moviesVM: MoviesVM
    private val handler = Handler(Looper.getMainLooper())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialization()
        listeners()
        getMovies()
    }


    private fun initialization() {
        moviesVM = ViewModelProvider(this)[MoviesVM::class.java]
    }

    private fun listeners() {
        binding.includeLayoutError.buttonTryAgain.setOnClickListener {
            binding.includeLayoutError.root.visibility = View.GONE
            binding.rvMovies.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            handler.postDelayed({
                getMovies()
            }, 500)
        }
    }

    private fun getMovies() {
        moviesVM.getMovies()
        moviesVM.response.observe(this, Observer { response ->
            if (response.status == 200) {
                binding.rvMovies.adapter = MoviesAdapter(response.data?.photos?.photo!!)
                binding.progressBar.visibility = View.GONE
                binding.includeLayoutError.root.visibility = View.GONE
                binding.rvMovies.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
                binding.rvMovies.visibility = View.GONE
                binding.includeLayoutError.root.visibility = View.VISIBLE
                binding.includeLayoutError.tvError.text = response.message
            }
        })
    }
}