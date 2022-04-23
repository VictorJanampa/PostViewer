package com.example.postviewer.postview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postviewer.network.Post
import com.example.postviewer.network.PostApi
import kotlinx.coroutines.launch

enum class ApiStatus { LOADING, ERROR, DONE }

class PostListViewModel : ViewModel(){

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>>
        get() = _posts

    private val _navigateToSelectedItem = MutableLiveData<Post?>()
    val navigateToSelectedProperty: MutableLiveData<Post?>
        get() = _navigateToSelectedItem


    init {
        getPosts()
    }

    private fun getPosts() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                _posts.value = PostApi.retrofitService.getPosts()
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _posts.value = ArrayList()
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