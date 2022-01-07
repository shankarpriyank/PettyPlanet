package com.example.pettyplanet.ui.createpost


import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pettyplanet.daos.PostDao
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


@HiltViewModel
class CreatePostViewModel @Inject
constructor(
    private val userID: String
) : ViewModel() {


    private val _refUpload = MutableLiveData<String>().apply {
        value = "Default"
    }
    val refUpload: LiveData<String> = _refUpload


    fun post(ImageUri: Uri?, Description: String) {
        Log.d("userID", userID)


        val postDao = PostDao()


        val storageRef = FirebaseStorage.getInstance().reference
        val photoref = storageRef.child("images/${System.currentTimeMillis()}-photo.jpg")



        photoref.putFile(ImageUri!!).continueWithTask { photoUploadTask ->

            Log.d("Storing Photo", "${photoUploadTask.result?.bytesTransferred}")



            photoref.downloadUrl


        }


            .addOnCompleteListener {
                if (it.isSuccessful) {


                    GlobalScope.launch(Dispatchers.IO) {


                        postDao.addPost(
                            Description,
                            photoref.downloadUrl.await().toString(),
                            userID
                        )
                        _refUpload.postValue("Successful")


                    }


                } else {
                    _refUpload.postValue("Unsuccessful")


                }


            }
    }


}













