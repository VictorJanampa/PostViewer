package com.example.postviewer

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.postviewer.commentview.CommentListAdapter
import com.example.postviewer.network.Comment
import com.example.postviewer.network.Post
import com.example.postviewer.postview.PostListAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Post>?) {
    val adapter = recyclerView.adapter as PostListAdapter
    adapter.submitList(data)
}
@BindingAdapter("listCommentData")
fun bindCommentRecyclerView(recyclerView: RecyclerView, data: List<Comment>?) {
    val adapter = recyclerView.adapter as CommentListAdapter
    adapter.submitList(data)
}