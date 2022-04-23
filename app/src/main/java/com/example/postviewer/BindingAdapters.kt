package com.example.postviewer

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.postviewer.network.Post
import com.example.postviewer.postview.PostListAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Post>?) {
    val adapter = recyclerView.adapter as PostListAdapter
    adapter.submitList(data)
}