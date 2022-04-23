package com.example.postviewer.postview

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.postviewer.database.PostDatabaseDao

class PostListViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostListViewModel::class.java)) {
            return PostListViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}