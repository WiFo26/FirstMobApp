package com.example.conf.view.adapter

import com.example.conf.model.Conference

interface ScheduleListener{
    fun onConferenceClicked(conference: Conference,position: Int)
}