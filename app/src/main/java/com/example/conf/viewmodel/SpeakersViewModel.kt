package com.example.conf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.conf.model.Speaker
import com.example.conf.network.Callback
import com.example.conf.network.FirestoreService
import java.lang.Exception

class SpeakersViewModel : ViewModel(){
    val db = FirestoreService()
    var listSpeaker: MutableLiveData<List<Speaker>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh(){
        getSpeakerFromFirebase()
    }

    private fun getSpeakerFromFirebase() {
        db.getSpeakers(object : Callback<List<Speaker>> {
            override fun onSucces(result: List<Speaker>?) {
                listSpeaker.postValue(result)
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }

        })
    }

    private fun processFinished() {
        isLoading.value = true
    }
}