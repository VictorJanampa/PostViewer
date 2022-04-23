package com.example.postviewer.postview

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postviewer.database.PostDatabase
import com.example.postviewer.database.PostDatabaseDao
import com.example.postviewer.network.Post
import com.example.postviewer.network.PostApi
import kotlinx.coroutines.NonDisposableHandle.parent
import kotlinx.coroutines.launch

enum class ApiStatus { LOADING, ERROR, DONE }

class PostListViewModel(application: Application) : ViewModel(){

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>>
        get() = _posts

    private val _navigateToSelectedItem = MutableLiveData<Post?>()
    val navigateToSelectedProperty: MutableLiveData<Post?>
        get() = _navigateToSelectedItem

    val database = PostDatabase.getInstance(application).postDatabaseDao

    init {
        fetchPosts()
    }

    fun fetchPosts() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                _posts.value = PostApi.retrofitService.getPosts().also {
                    for (post in it){
                        database.insertPost(post)
                    }
                }
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                e.message?.let { Log.i("Andrio", it) }
                val data=database.getAllPosts()
                Log.i("Andrio", data.toString())
                _posts.value = data ?: ArrayList()
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