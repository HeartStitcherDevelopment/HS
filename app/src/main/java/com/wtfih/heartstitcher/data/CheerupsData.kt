package com.wtfih.heartstitcher.data


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class CheerupsDataViewModel:ViewModel(){
    @SuppressLint("MutableCollectionMutableState")
    val state = mutableStateOf(HashMap<String,String>())

    init{
        getData()
    }
    private fun getData(){
        viewModelScope.launch {
            state.value = getCheerupsFromFireStore()
        }
    }
}


suspend fun getCheerupsFromFireStore():HashMap<String,String>{
    val database = FirebaseFirestore.getInstance()
    var hashmap = HashMap<String,String>()
    try{
        val documentSnapshot = database.collection("cheerups")
            .document("cheerups_db")
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



