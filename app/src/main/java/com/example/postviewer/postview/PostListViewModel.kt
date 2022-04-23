package com.example.postviewer.postview

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postviewer.database.PostDatabase
import com.example.postviewer.network.Post
import com.example.postviewer.network.PostApi
import kotlinx.coroutines.launch

class PostListViewModel(application: Application) : ViewModel(){

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>>
        get() = _posts

    private val _navigateToSelectedItem = MutableLiveData<Post?>()
    val navigateToSelectedProperty: MutableLiveData<Post?>
        get() = _navigateToSelectedItem

    private val database = PostDatabase.getInstance(application).postDatabaseDao

    init {
        fetchPosts()
    }

    fun fetchPosts() {
        viewModelScope.launch {
            try {
                _posts.value = PostApi.retrofitService.getPosts().also {
                    for (post in it){
                        database.insertPost(post)
                    }
                }
            } catch (e: Exception) {
                val data=database.getAllPosts()
                _posts.value = data
            }
        }
    }

    fun displayComments(post: Post) {
        _navigateToSelectedItem.value = post
    }

    fun displayCommentsComplete() {
        _navigateToSelectedItem.value = null
    }
}