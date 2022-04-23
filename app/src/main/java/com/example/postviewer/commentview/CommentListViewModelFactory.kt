package com.example.postviewer.commentview

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.postviewer.network.Post

class CommentListViewModelFactory(
    private val post: Post,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommentListViewModel::class.java)) {
            return CommentListViewModel(post, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
