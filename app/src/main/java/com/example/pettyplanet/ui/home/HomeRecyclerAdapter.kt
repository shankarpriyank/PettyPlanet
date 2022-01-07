package com.example.pettyplanet.ui.home

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pettyplanet.R
import com.example.pettyplanet.databinding.ItemPostBinding
import com.example.pettyplanet.models.Post

class HomeRecyclerAdapter(private val listener: PostClicked) :
    ListAdapter<Post, HomeRecyclerAdapter.FeedViewHolder>(FeedDiffCallBack()) {


    class FeedViewHolder(val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root)

    private class FeedDiffCallBack : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.toString() == newItem.toString()
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val inflater: LayoutInflater = parent.context.getSystemService(LayoutInflater::class.java)
        val binding = ItemPostBinding.inflate(inflater, parent, false)
        val viewholder = FeedViewHolder(binding)
        binding.root.setOnLongClickListener {
            listener.onItemClick(viewholder.adapterPosition)
            true
        }




        return viewholder
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val post = getItem(position)

        holder.binding.captionTextView.text = post.text
        holder.binding.username.text = post.createdBy.displayName


        Glide.with(holder.binding.root)
            .load(post.ImageURL) // image url
            .placeholder(R.drawable.img) // any placeholder to load at start
            .error(R.drawable.img)  // any image in case of error

            .into(holder.binding.imageView)

        Glide.with(holder.binding.root)
            .load(post.createdBy.imageUrl) // image url
            .placeholder(R.drawable.useimage_placeholder) // any placeholder to load at start
            .error(R.drawable.useimage_placeholder)  // any image in case of error

            .into(holder.binding.profileImage)


    }


}

interface PostClicked {
    fun onItemClick(post: Int)
}