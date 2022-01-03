package com.example.pettyplanet.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pettyplanet.R
import com.example.pettyplanet.daos.PostDao
import com.example.pettyplanet.databinding.FragmentHomeBinding
import com.example.pettyplanet.ui.dashboard.postdao
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {



    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null


    private val feedAdapter = HomeRecyclerAdapter()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        postdao = PostDao()
       // postdao.getAllPosts()
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.updateFeed()



        binding.feedRv.layoutManager = LinearLayoutManager(requireContext())
        binding.feedRv.adapter = feedAdapter


//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

//
        homeViewModel.feed.observe(viewLifecycleOwner) {
            it?.forEach {


            }
//            it.forEach { Post ->
//
//            }
            feedAdapter.submitList(it)


        }
        return root
    }

    override fun onStart() {
        super.onStart()
        homeViewModel.updateFeed()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}