package com.example.retrofitgetimage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitgetimage.RetrofitBuild.Companion.retrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var photoAdapter: PhotoAdapter
    private lateinit var pList:ArrayList<Photo>
    private lateinit var lm:LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pList = ArrayList()
        lm = LinearLayoutManager(this)
        photoAdapter = PhotoAdapter(pList,this@MainActivity)
        rvPhotos.layoutManager = lm
        rvPhotos.adapter = photoAdapter
        retrofitClient<RetroService>().getPhotos()
            .enqueue(object:Callback<List<Photo>>{
                override fun onFailure(call: Call<List<Photo>>, t: Throwable) {}
                override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                    if (response.isSuccessful){
                        val pl:List<Photo> = response.body()!!
                        pList.addAll(pl)
                        photoAdapter.notifyDataSetChanged()
                    }
                }

            })
    }
}
