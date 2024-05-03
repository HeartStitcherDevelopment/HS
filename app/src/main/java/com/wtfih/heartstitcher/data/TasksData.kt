package com.wtfih.heartstitcher.data

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class UserDataViewModel: ViewModel(){
    @SuppressLint("MutableCollectionMutableState")
    val state = mutableStateOf(HashMap<String,String>())

    init{
        getData()
    }
    fun getData(){
        viewModelScope.launch {
            state.value = getUserFromFireStore()
        }
    }
}

suspend fun getUserFromFireStore():HashMap<String,String>{
    val database = FirebaseFirestore.getInstance()
    val currenUser = Firebase.auth.currentUser!!.uid
    var hashmap = HashMap<String,String>()
    try{
        val documentSnapshot = database.collection("users")
            .document(currenUser)
            .get().await()
        if (documentSnapshot.exists()) {
            val data = documentSnapshot.data
            if (data != null) {
                hashmap.putAll(data as Map<String, String>)
            }
            Log.d("getDataFromFirestore", "Document found")
        } else {
            Log.d("getDataFromFirestore", "Document not found")
        }
    }catch(e: FirebaseFirestoreException){
        Log.d("error", "getDataFromFirestore: $e")
    }
    return hashmap
}