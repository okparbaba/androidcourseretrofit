package com.example.retrofitgetimage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*

class PhotoAdapter(val photoList:List<Photo>,val context: Context):RecyclerView.Adapter<PhotoAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item,parent,false))
    override fun getItemCount()=photoList.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = photoList[position].title
        Picasso.get().load(photoList[position].url).into(holder.image)
    }

    class ViewHolder(v:View):RecyclerView.ViewHolder(v) {
        val title = v.tvTitle
        val image = v.ivPhoto
    }
}