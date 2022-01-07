package com.example.pettyplanet.daos

import android.util.Log
import com.example.pettyplanet.models.Post
import com.example.pettyplanet.models.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await


class PostDao {

    val db = FirebaseFirestore.getInstance()
    val postCollections = db.collection("posts")

    @DelicateCoroutinesApi
    fun addPost(text: String, image: String, uid: String) {
        GlobalScope.launch(Dispatchers.IO) {


            val userDao = UserDao()
            val user = userDao.getUserById(uid).await().toObject(User::class.java)!!

            val currentTime = System.currentTimeMillis()
            val post = Post(text, user, currentTime, image)
            postCollections.document().set(post)
            Log.e("UserDoc", "${user.displayName}")
        }
    }


    suspend fun getAllPosts(): List<Post>? {


        val postlist = postCollections.get().await().toObjects(Post::class.java)


//              val wait = GlobalScope.launch {
//                postCollections.get().await()
//
//
//            }
        postCollections.addSnapshotListener { snapshot, error ->
            if (snapshot == null || error != null) {
                Log.e("FireStore ERR", "$error")
                return@addSnapshotListener
            } else {
                val posrlist = snapshot.toObjects(Post::class.java)
                for (post in posrlist) {
                    Log.e("Info", "$post")
                }

            }

        }

        return postlist


    }


}
