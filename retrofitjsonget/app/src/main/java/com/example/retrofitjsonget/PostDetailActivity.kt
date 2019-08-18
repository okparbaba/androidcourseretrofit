package com.example.retrofitjsonget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_post_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)
        val id = intent.getStringExtra("id") ?: "1"
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(RetroService::class.java)
        retrofitService.getPost(id)
            .enqueue(object:Callback<Post>{
                override fun onFailure(call: Call<Post>, t: Throwable) {
                    Toast.makeText(this@PostDetailActivity,"Req fail",Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    if (response.isSuccessful){
                        val post:Post = response.body()!!
                        tvTitle.text = post.title
                        tvBody.text = post.body
                    }
                }

            })
    }
}
