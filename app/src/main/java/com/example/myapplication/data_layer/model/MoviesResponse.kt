package com.example.myapplication.data_layer.model

data class MoviesResponse(val photos: Photos)

data class Photos (
    val page: Long,
    val pages: Long,
    val perpage: Long,
    val total: String,
    val photo: ArrayList<Photo>
)

data class Photo(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Long,
    val title: String,
    val ispublic: Long,
    val isfriend: Long,
    val isfamily: Long,
    var itemType: Int
)