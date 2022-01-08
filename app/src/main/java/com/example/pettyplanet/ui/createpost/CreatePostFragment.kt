package com.example.pettyplanet.ui.createpost

import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat.getExternalFilesDirs
import androidx.core.content.FileProvider
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.pettyplanet.R
import com.example.pettyplanet.databinding.FragmentCreatepostBinding
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_feed.*
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle
import java.io.File


@AndroidEntryPoint
class CreatePostFragment : Fragment(), EasyPermissions.PermissionCallbacks {


    val CHANNEL_ID = "NotificationChannel"
    val CHANNEL_NAME = "PostInfo"
    val PERMISSION_READ_REQUEST_CODE = 1
    val PERMISSION_WRITE_REQUEST_CODE = 2

    val IMAGE_REQUEST_CODE = 123
    val REQUEST_CODE = 10

    private val FILE_NAME = "photo.jpg"
    private lateinit var photoFile: File

    var mImageUri: Uri? = null


    private lateinit var createpostViewModel: CreatePostViewModel
    private var _binding: FragmentCreatepostBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        createpostViewModel =
            ViewModelProvider(this).get(CreatePostViewModel::class.java)

        _binding = FragmentCreatepostBinding.inflate(inflater, container, false)
        val root: View = binding.root
        requestReadWritePermission()


        binding.opengallery.setOnClickListener {
            if (!hasReadWritePermission()) {


                Toast.makeText(requireContext(), "Need Permissions", Toast.LENGTH_LONG).show()


            } else {
                pickImageFromGallery()
            }
        }

        binding.openCamera.setOnClickListener {
            if (!hasReadWritePermission()) {
                Toast.makeText(requireContext(), "Need Permissions", Toast.LENGTH_LONG).show()


            } else {
                triggercamera()


            }
        }


        binding.postButton.setOnClickListener {


            val input = binding.desc.text.toString()


            Log.e("Input", input)

            if (!(input == "" || mImageUri == null)) {

                requireActivity().nav_view.menu.forEach { it.isEnabled = false }




                MotionToast.darkToast(
                    requireActivity(),
                    "Upload Has Started",
                    "Please Stay Patient while the post is being uploaded",
                    MotionToastStyle.INFO,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION,
                    ResourcesCompat.getFont(requireContext(), R.font.cherry_cream_soda)
                )



                createpostViewModel.post(mImageUri, binding.desc.text.toString())


                createpostViewModel.refUpload.observe(viewLifecycleOwner, Observer {
                    if (it == "Successful") {

                        requireActivity().nav_view.menu.forEach { menuItem ->
                            menuItem.isEnabled = true
                        }

                        MotionToast.darkToast(
                            requireActivity(),
                            "Hurray success 😍",
                            "Upload Completed successfully!",
                            MotionToastStyle.SUCCESS,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(requireContext(), R.font.cherry_cream_soda)
                        )


                    }

                    if (it == "Unsuccessful") {
                        requireActivity().nav_view.menu.forEach { menuItem ->
                            menuItem.isEnabled = true
                        }


                        MotionToast.darkToast(
                            requireActivity(),
                            "Failed ☹",
                            "Upload Unsuccessful!",
                            MotionToastStyle.ERROR,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(requireContext(), R.font.cherry_cream_soda)
                        )


                    }


                })


            } else {
                Toast.makeText(
                    requireContext(),
                    "Choose An Image And write a description first",
                    Toast.LENGTH_LONG
                ).show()
            }


        }





        return root


    }


    private fun triggercamera() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        photoFile = getPhotoFile(FILE_NAME)


        val fileProvider = FileProvider.getUriForFile(
            requireContext(),
            "com.example.pettyplanet.fileprovider",
            photoFile
        )
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)
        if (takePictureIntent.resolveActivity(requireContext().packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_CODE)
        } else {
            Toast.makeText(requireContext(), "Unable to open camera", Toast.LENGTH_SHORT).show()
        }


    }


    private fun getPhotoFile(fileName: String): File {
        // Use `getExternalFilesDir` on Context to access package-specific directories.
        val storageDirectory =
            getExternalFilesDirs(requireContext(), Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, ".jpg", storageDirectory.first())
    }


    private fun hasReadWritePermission(): Boolean {
        var flag: Boolean = true

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            flag = EasyPermissions.hasPermissions(
                requireContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            )

        } else {
            flag = EasyPermissions.hasPermissions(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA
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
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA
            )
        } else {

            EasyPermissions.requestPermissions(
                this,
                "This application cannot work without ReadWrite Permission.",
                PERMISSION_READ_REQUEST_CODE,
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA
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
            mImageUri = selectedImage!!

            try {
                Glide.with(this)
                    .load(selectedImage)
                    .into(binding.postImage)
            } catch (e: Exception) {
                Log.e("Err", "$e")

            }


        } else {
            if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
                //   val takenImage = data?.extras?.get("data") as Bitmap

                val takenImage = BitmapFactory.decodeFile(photoFile.absolutePath)

                mImageUri = Uri.fromFile(photoFile)
                Glide.with(this)
                    .load(takenImage)
                    .into(binding.postImage)
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }


    override fun onResume() {
        super.onResume()
        createpostViewModel.status.observe(viewLifecycleOwner) {
            if (it == "Uploading") {
                binding.postButton.isEnabled = false
                binding.opengallery.isEnabled = false
                binding.openCamera.isEnabled = false
                binding.postImage.visibility = View.INVISIBLE
                binding.uploadAnimation.visibility = View.VISIBLE
                binding.postImage.setImageResource(R.drawable.img)
            }
            if (it == "Uploading Stopped") {
                binding.postButton.isEnabled = true
                binding.opengallery.isEnabled = true
                binding.openCamera.isEnabled = true
                binding.postImage.visibility = View.VISIBLE
                binding.uploadAnimation.visibility = View.INVISIBLE
            }


        }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}


