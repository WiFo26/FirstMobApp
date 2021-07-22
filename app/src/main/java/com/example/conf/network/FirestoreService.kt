package com.example.conf.network

import com.example.conf.model.Conference
import com.example.conf.model.Publication
import com.example.conf.model.Speaker
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

const val SPEAKER_COLLECTION_NAME = "speakers1"
const val PUBLICATION_COLLECTION_NAME = "publications"
const val CONFERENCE_COLLECTION_NAME = "conferences1"
class FirestoreService {
    private val db = Firebase.firestore

    fun getPubs(callback: Callback<List<Publication>>){
        db.collection(PUBLICATION_COLLECTION_NAME)
            .orderBy("pubPos")
            .get()
            .addOnSuccessListener { result ->
                for (doc in result){
                    val list = result.toObjects(Publication::class.java)
                    callback.onSucces(list)
                    break
                }
            }
    }

    fun getSpeakers(callback: Callback<List<Speaker>>){
        db.collection(SPEAKER_COLLECTION_NAME)
            .orderBy("category")
            .get()
            .addOnSuccessListener { result ->
                for (doc in result){
                    val list = result.toObjects(Speaker::class.java)
                    callback.onSucces(list)
                    break
                }
            }
    }
    fun getSchedule(callback: Callback<List<Conference>>){
        db.collection(CONFERENCE_COLLECTION_NAME)
            .orderBy("datetime")
            .get()
            .addOnSuccessListener { result ->
                for (doc in result){
                    val list = result.toObjects(Conference::class.java)
                    callback.onSucces(list)
                    break
                }
            }

    }
}