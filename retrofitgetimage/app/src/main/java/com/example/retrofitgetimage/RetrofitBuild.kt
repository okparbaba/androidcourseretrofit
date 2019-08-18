package com.example.retrofitgetimage

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuild {
    companion object{
        inline fun <reified T>retrofitClient(): T {
            val ret = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(T::class.java)
            return ret
        }
    }
}