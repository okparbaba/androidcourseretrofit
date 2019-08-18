package com.example.retrofitjsonget

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class PostAdapter(var c:Context,var list: List<Post>):RecyclerView.Adapter<PostAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        ViewHolder(LayoutInflater.from(c).inflate(R.layout.list_item,parent,false))
    override fun getItemCount()=list.size
    override fun onBindViewHolder(h: ViewHolder, p: Int) {
        h.ti.text = list[p].title
        h.bo.text = list[p].body
        h.itemView.setOnClickListener {
            val intent = Intent(c,PostDetailActivity::class.java)
            intent.putExtra("id",list[p].id)
            c.startActivity(intent)
        }
    }
    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        val ti = view.tvTitle as TextView
        val bo = view.tvBody as TextView
    }
}