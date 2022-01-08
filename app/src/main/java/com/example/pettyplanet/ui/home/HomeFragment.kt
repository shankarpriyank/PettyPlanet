package com.example.pettyplanet.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pettyplanet.adapters.HomeRecyclerAdapter
import com.example.pettyplanet.adapters.PostClicked
import com.example.pettyplanet.daos.PostDao
import com.example.pettyplanet.databinding.FragmentHomeBinding
import com.example.pettyplanet.models.SavedPosts
import com.example.pettyplanet.ui.createpost.postdao
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), PostClicked {


    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null


    private val feedAdapter = HomeRecyclerAdapter(this)

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        postdao = PostDao()
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.updateFeed()



        binding.feedRv.layoutManager = LinearLayoutManager(requireContext())
        binding.feedRv.adapter = feedAdapter



        homeViewModel.feed.observe(viewLifecycleOwner) {

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

    override fun onItemClick(post: Int) {
        val post = feedAdapter.currentList[post]
        val postTobeSaved = SavedPosts(
            post.text,
            post.createdBy.displayName!!,
            post.createdAt,
            post.ImageURL,
            post.createdBy.imageUrl,
            System.currentTimeMillis()
        )



        GlobalScope.launch(Dispatchers.IO) {

            homeViewModel.savepost(postTobeSaved)


        }

        Toast.makeText(requireContext(), "Posts Saved", Toast.LENGTH_LONG).show()


    }
}