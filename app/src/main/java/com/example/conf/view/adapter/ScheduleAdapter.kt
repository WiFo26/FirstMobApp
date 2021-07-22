package com.example.conf.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.conf.R
import com.example.conf.model.Conference
import java.text.SimpleDateFormat
import kotlin.collections.ArrayList

class ScheduleAdapter (val scheduleListener: ScheduleListener):RecyclerView.Adapter<ScheduleAdapter.ViewHolder>(){

    class ViewHolder (itemView:View) : RecyclerView.ViewHolder(itemView){
        val tvConferenceName = itemView.findViewById<TextView>(R.id.itemScheduleTitleConf)
        val tvTime = itemView.findViewById<TextView>(R.id.itemScheduleHour)
        val tvAMPM = itemView.findViewById<TextView>(R.id.itemScheduleAMPM)
        val tvSpeakerName = itemView.findViewById<TextView>(R.id.itemScheduleNameExp)
        val tvTag = itemView.findViewById<TextView>(R.id.itemTag)
    }

    var listConference = ArrayList<Conference>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(
        R.layout.item_schedule,parent,false))

    override fun onBindViewHolder(holder: ScheduleAdapter.ViewHolder, position: Int) {

        val conference = listConference[position]

        holder.tvConferenceName.text = conference.title
        holder.tvSpeakerName.text = conference.speaker
        holder.tvTag.text = conference.tag
        val simpleDateFormat = SimpleDateFormat("HH:mm")
        val simpleDateFormatAMPM = SimpleDateFormat("a")
        val hourFormat = simpleDateFormat.format(conference.datetime)

        holder.tvTime.text = hourFormat
        holder.tvAMPM.text = simpleDateFormatAMPM.format(conference.datetime).uppercase()

        holder.itemView.setOnClickListener {
            scheduleListener.onConferenceClicked(conference, position)
        }

    }

    fun updateData(data: List<Conference>){
        listConference.clear()
        listConference.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount() = listConference.size
    }
