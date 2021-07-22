package com.example.conf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.conf.model.Conference
import com.example.conf.network.Callback
import com.example.conf.network.FirestoreService
import java.lang.Exception

class ScheduleViewModel :ViewModel(){
    val db = FirestoreService()
    var listSchedule: MutableLiveData<List<Conference>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh(){
        getScheduleFromFirebase()
    }

    private fun getScheduleFromFirebase() {
        db.getSchedule(object :  Callback<List<com.example.conf.model.Conference>> {
            override fun onSucces(result: List<Conference>?) {
                listSchedule.postValue(result)
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