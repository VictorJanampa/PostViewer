package com.example.postviewer.commentview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.postviewer.databinding.CommentItemBinding
import com.example.postviewer.databinding.PostItemBinding
import com.example.postviewer.databinding.PostListFragmentBinding
import com.example.postviewer.network.Comment
import com.example.postviewer.network.Post

class CommentListAdapter :
    ListAdapter<Comment,CommentListAdapter.PostViewHolder>(DiffCallback){

    companion object DiffCallback : DiffUtil.ItemCallback<Comment>() {
        override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class PostViewHolder(private var binding: CommentItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Comment) {
            binding.post = post
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(CommentItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}

