package com.example.postviewer.commentview

import android.app.Application
import androidx.lifecycle.*
import com.example.postviewer.network.Comment

import com.example.postviewer.network.Post
import com.example.postviewer.network.PostApi
import kotlinx.coroutines.launch

enum class ApiStatus { LOADING, ERROR, DONE }

class CommentListViewModel (post: Post, app: Application) : AndroidViewModel(app){

    private val _selectedPost = MutableLiveData<Post>()

    init {
        _selectedPost.value = post
    }
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val _posts = MutableLiveData<List<Comment>>()
    val posts: LiveData<List<Comment>>
        get() = _posts

    init {
        getComments()
    }
    fun getComments() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                _posts.value = _selectedPost.value?.let { PostApi.retrofitService.getPostComments(it.id) }
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _posts.value = ArrayList()
            }
        }
    }
}