package com.example.retrofitgetimage

import retrofit2.Call
import retrofit2.http.GET

interface RetroService {
    @GET("photos")
    fun getPhotos(): Call<List<Photo>>
}