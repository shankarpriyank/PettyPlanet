package com.example.pettyplanet.ui.dashboard

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pettyplanet.databinding.FragmentCreatepostBinding
import kotlinx.android.synthetic.main.fragment_createpost.*
import androidx.core.content.FileProvider
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog
import java.io.File
import com.bumptech.glide.Glide

import android.R
import android.R.attr

import android.R.attr.data
import android.app.Activity
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import androidx.core.app.ActivityCompat.*
import com.bumptech.glide.request.target.Target
import java.io.IOException


const val PERMISSION_READ_REQUEST_CODE = 1
const val PERMISSION_WRITE_REQUEST_CODE = 2

 const val IMAGE_REQUEST_CODE = 123
 const val REQUEST_CODE = 10

private const val FILE_NAME = "photo.jpg"
private lateinit var photoFile: File




class CreatePostFragment : Fragment(),EasyPermissions.PermissionCallbacks{

    private lateinit var dashboardViewModel: CreatePostViewModel
    private var _binding: FragmentCreatepostBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(CreatePostViewModel::class.java)

        _binding = FragmentCreatepostBinding.inflate(inflater, container, false)
        val root: View = binding.root
        requestReadWritePermission()

//        val textView: TextView = binding.textDashboard
//        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        binding.opengallery.setOnClickListener {
            if (!hasReadWritePermission()){
                Toast.makeText(requireContext(),"Need Permissions",Toast.LENGTH_LONG).show()


            }else{
                pickImageFromGallery()
            }
        }

        binding.openCamera.setOnClickListener {
            if (!hasReadWritePermission()){
                Toast.makeText(requireContext(),"Need Permissions",Toast.LENGTH_LONG).show()


            }else{
                triggercamera()


            }
        }

        return root
    }

    private fun triggercamera() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        photoFile = getPhotoFile(FILE_NAME)



        val fileProvider = FileProvider.getUriForFile(requireContext(), "com.example.pettyplanet.fileprovider", photoFile)
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)
        if (takePictureIntent.resolveActivity(requireContext().packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_CODE)
        } else {
            Toast.makeText(requireContext(), "Unable to open camera", Toast.LENGTH_SHORT).show()
        }

    }


    private fun getPhotoFile(fileName: String): File {
        // Use `getExternalFilesDir` on Context to access package-specific directories.
        val storageDirectory = getExternalFilesDirs(requireContext(),Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, ".jpg", storageDirectory.first())
    }




    private fun hasReadWritePermission(): Boolean {
        var flag: Boolean = true

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
             flag =  EasyPermissions.hasPermissions(
                requireContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA
            )

        }else{
             flag =  EasyPermissions.hasPermissions(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA
            )

        }
        return flag



    }


private fun requestReadWritePermission() {

    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
        EasyPermissions.requestPermissions(
            this,
            "This application cannot work without ReadWrite Permission.",
            PERMISSION_WRITE_REQUEST_CODE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA
        )
    }

    else{

        EasyPermissions.requestPermissions(
            this,
            "This application cannot work without ReadWrite Permission.",
            PERMISSION_READ_REQUEST_CODE,
            Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA
        )
    }

}

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            SettingsDialog.Builder(requireActivity()).build().show()
        } else {
            requestReadWritePermission()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        Toast.makeText(
            requireContext(),
            "Permission Granted!",
            Toast.LENGTH_SHORT
        ).show()

    }



    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"

        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {

            val selectedImage: Uri? = data!!.data

            try {
                Glide.with(this)
                    .load(selectedImage)
                    .into(binding.postImage)
            } catch (e: Exception) {
                Log.e("Err","$e")

            }








        }
        else{
            if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
//            val takenImage = data?.extras?.get("data") as Bitmap
                val takenImage = BitmapFactory.decodeFile(photoFile.absolutePath)
                Glide.with(this)
                    .load(takenImage)
                    .into(binding.postImage)
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }



    override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
}
}


