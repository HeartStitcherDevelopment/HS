package com.wtfih.heartstitcher.data

import android.content.Context
import android.net.Uri
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import java.util.UUID

class StorageUtil {

    companion object {

        fun uploadToStorage(uri: Uri, context: Context, type: String, text: String, achievements: MutableList<Pair<String,String>>){
            val storage = Firebase.storage
            var storageRef = storage.reference
            val unique_image_name = UUID.randomUUID()
            var spaceRef: StorageReference
            val db = Firebase.firestore
            val id = Firebase.auth.currentUser!!.uid

            if(type == "image"){
                spaceRef = storageRef.child("images/$unique_image_name.jpg")
            }
            else{
                spaceRef = storageRef.child("videos/$unique_image_name.mp4")
            }

            val byteArray: ByteArray? = context.contentResolver
                .openInputStream(uri)
                ?.use { it.readBytes() }

            byteArray?.let{
                var uploadTask = spaceRef.putBytes(byteArray)
                uploadTask.addOnFailureListener{
                    Toast.makeText(
                        context,
                        "Upload failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }.addOnSuccessListener { taskSnapshot ->
                    spaceRef.downloadUrl.addOnSuccessListener {
                            uri ->
                        achievements.add(Pair(text, uri.toString()))
                        db.collection("users").document(id).update("achievements", achievements)
                    }
                    Toast.makeText(
                        context,
                        "Success!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}
