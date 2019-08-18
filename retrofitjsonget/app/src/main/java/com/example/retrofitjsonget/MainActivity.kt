package com.example.retrofitjsonget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var postAdapter: PostAdapter
    private lateinit var pList:ArrayList<Post>
    private lateinit var lManager:LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressbar.visibility = VISIBLE
        pList = ArrayList()
        lManager = LinearLayoutManager(this@MainActivity)
        postAdapter = PostAdapter(this@MainActivity,pList)
        rvPosts.layoutManager = lManager
        rvPosts.adapter = postAdapter
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(RetroService::class.java)
        retrofitService.getPosts()
            .enqueue(object : Callback<List<Post>> {
                override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                    progressbar.visibility = GONE
                    Toast.makeText(this@MainActivity, "Error Request", Toast.LENGTH_LONG).show()
                }
                override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                    progressbar.visibility = GONE
                    if (response.isSuccessful) {
                        val postList: List<Post> = response.body()!!
                        pList.addAll(postList)
                        postAdapter.notifyDataSetChanged()
                    } else {
                        Toast.makeText(this@MainActivity, "Fail to request", Toast.LENGTH_LONG).show()
                    }
                }

            })

    }
}
