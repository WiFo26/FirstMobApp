package com.example.conf.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.conf.R
import com.example.conf.model.Publication
import com.example.conf.model.Speaker

class PubAdapter ():RecyclerView.Adapter<PubAdapter.ViewHolder>(){

    var listPubs = ArrayList<Publication>()

    class ViewHolder (itemView:View):RecyclerView.ViewHolder(itemView) {
        val ivImage = itemView.findViewById<ImageView>(R.id.imgHome)
        val tvTitle = itemView.findViewById<TextView>(R.id.titleHome)
        val tvMsg = itemView.findViewById<TextView>(R.id.messageHome)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_home,parent
    ,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val publication = listPubs[position]

        holder.tvTitle.text = publication.title
        holder.tvMsg.text = publication.message

        Glide.with(holder.itemView.context)
            .load(publication.image)
            .into(holder.ivImage)
    }
    fun updateData(data: List<Publication>){
        listPubs.clear()
        listPubs.addAll(data)
        notifyDataSetChanged()
    }
    override fun getItemCount() : Int = listPubs.size

}