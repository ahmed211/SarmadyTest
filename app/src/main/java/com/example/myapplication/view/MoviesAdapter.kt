package com.example.myapplication.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.data_layer.model.Photo
import com.example.myapplication.databinding.AdsItemBinding
import com.example.myapplication.databinding.MovieItemBinding

private lateinit var contxt: Context
class MoviesAdapter(private val photos: ArrayList<Photo>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        contxt = parent.context
        return if (viewType == 0){
            MoviesViewHolder(
                MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        } else{
            AdsViewHolder(AdsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == 0){
            (holder as MoviesViewHolder).bind(photos[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return photos[position].itemType
    }


    class MoviesViewHolder(private val binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(photo: Photo){
            binding.apply {
                photo.apply {
                    val photoUrl = "https://farm$farm.static.flickr.com/$server/${id}_$secret.jpg"
                    binding.tvMovieTitle.text = photo.title
                    Glide.with(contxt)
                        .load(photoUrl)
                        .into(ivAdPhoto)
                }
            }
        }
    }

    class AdsViewHolder(binding: AdsItemBinding): RecyclerView.ViewHolder(binding.root)

}