package com.example.pettyplanet.ui.profile


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pettyplanet.MainActivity
import com.example.pettyplanet.R
import com.example.pettyplanet.adapters.SavedPostClicked
import com.example.pettyplanet.adapters.SavedPostsRecyclerAdapter
import com.example.pettyplanet.databinding.FragmentMyprofileBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_myprofile.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

@AndroidEntryPoint
class MyProfileFragment : Fragment(), SavedPostClicked {

    private lateinit var profileViewModel: MyProfileViewModel
    private var _binding: FragmentMyprofileBinding? = null

    private val feedAdapter = SavedPostsRecyclerAdapter(this)

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        profileViewModel =
            ViewModelProvider(this).get(MyProfileViewModel::class.java)

        _binding = FragmentMyprofileBinding.inflate(inflater, container, false)
        val root: View = binding.root
//
//        val textView: TextView = binding.textNotifications
//        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })


        profileViewModel.updateFeed()


        binding.rvsaved.layoutManager = LinearLayoutManager(requireContext())
        binding.rvsaved.adapter = feedAdapter



        profileViewModel.feed.observe(viewLifecycleOwner) {

            feedAdapter.submitList(it)


        }

        root.logout_button.setOnClickListener {
            signout()
        }
        return root
    }

    private fun signout() {
        val account = GoogleSignIn.getLastSignedInAccount(requireContext())

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        // Build a GoogleSignInClient with the options specified by gso.
        val mGoogleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
        mGoogleSignInClient.signOut()
        val mainActivityIntent = Intent(requireContext(), MainActivity::class.java)
        startActivity(mainActivityIntent)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(post: Int) {

        val savedpost = feedAdapter.currentList[post]
        GlobalScope.launch(Dispatchers.IO) {
            profileViewModel.deleteSavedPost(savedpost)

        }

        MotionToast.darkToast(
            requireActivity(),
            "Post Deleted ☹",
            "Post Deleted Successfully",
            MotionToastStyle.ERROR,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.LONG_DURATION,
            ResourcesCompat.getFont(requireContext(), R.font.cherry_cream_soda)
        )


    }


}