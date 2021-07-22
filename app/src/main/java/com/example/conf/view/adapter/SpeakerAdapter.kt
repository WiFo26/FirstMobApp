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
import com.example.conf.model.Speaker

class SpeakerAdapter(val speakerListener: SpeakerListener):RecyclerView.Adapter<SpeakerAdapter.ViewHolder> (){

    var listSpeaker = ArrayList<Speaker>()

    class ViewHolder (itemView:View):RecyclerView.ViewHolder(itemView){
        val ivImage = itemView.findViewById<ImageView>(R.id.itemIMG)
        val tvName = itemView.findViewById<TextView>(R.id.itemSpeaker)
        val tvJob = itemView.findViewById<TextView>(R.id.itemJob)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_speaker,parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val speaker = listSpeaker[position] as Speaker

        holder.tvName.text = speaker.name
        holder.tvJob.text = speaker.jobtitle

        Glide.with(holder.itemView.context)
            .load(speaker.image)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.ivImage)

        holder.itemView.setOnClickListener{
            speakerListener.onSpeakerClicked(speaker,position)
        }
    }

    fun updateData(data: List<Speaker>){
        listSpeaker.clear()
        listSpeaker.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = listSpeaker.size
}