package com.example.conf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.conf.model.Publication
import com.example.conf.model.Speaker
import com.example.conf.network.Callback
import com.example.conf.network.FirestoreService
import java.lang.Exception

class PubsViewModel :ViewModel(){
    val db = FirestoreService()
    var listPub: MutableLiveData<List<Publication>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh(){
        getPubFromFirebase()
    }

    private fun getPubFromFirebase() {
        db.getPubs(object : Callback<List<Publication>>{
            override fun onSucces(result: List<Publication>?) {
                listPub.postValue(result)
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