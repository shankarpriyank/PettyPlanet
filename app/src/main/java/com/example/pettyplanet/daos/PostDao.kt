package com.example.pettyplanet.daos

import android.util.Log
import com.example.pettyplanet.models.Post
import com.example.pettyplanet.models.User
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class PostDao {

        val db = FirebaseFirestore.getInstance()
        val postCollections = db.collection("posts")

        @DelicateCoroutinesApi
        fun addPost(text: String,image : String,uid:String) {
            GlobalScope.launch(Dispatchers.IO) {




                val userDao = UserDao()
                val user = userDao.getUserById(uid).await().toObject(User::class.java)!!

                val currentTime = System.currentTimeMillis()
                val post = Post(text, user, currentTime,image)
                postCollections.document().set(post)
                Log.e("UserDoc","${user.displayName}")
            }
        }

        fun getPostById(postId: String): Task<DocumentSnapshot> {
            return postCollections.document(postId).get()
        }



    }
