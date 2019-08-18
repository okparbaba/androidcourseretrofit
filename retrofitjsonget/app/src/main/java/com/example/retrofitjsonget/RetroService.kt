package com.example.retrofitjsonget

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetroService {
    @GET("posts")
    fun getPosts():Call<List<Post>>


    @GET("posts/{id}")
    fun getPost(@Path("id") id:String):Call<Post>

}