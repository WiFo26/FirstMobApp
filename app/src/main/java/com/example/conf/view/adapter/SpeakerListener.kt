package com.example.conf.view.adapter

import com.example.conf.model.Speaker

interface SpeakerListener {
    fun onSpeakerClicked(speaker: Speaker,position:Int)
}