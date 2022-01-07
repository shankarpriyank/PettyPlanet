package com.example.pettyplanet.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pettyplanet.MainActivity
import com.example.pettyplanet.databinding.FragmentMyprofileBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.android.synthetic.main.fragment_myprofile.view.*


class MyProfileFragment : Fragment() {

    private lateinit var notificationsViewModel: MyProfileViewModel
    private var _binding: FragmentMyprofileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProvider(this).get(MyProfileViewModel::class.java)

        _binding = FragmentMyprofileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })


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


}