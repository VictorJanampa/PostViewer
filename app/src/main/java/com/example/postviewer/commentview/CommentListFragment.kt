package com.example.postviewer.commentview

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.postviewer.R
import com.example.postviewer.databinding.CommentListFragmentBinding
import com.example.postviewer.databinding.PostListFragmentBinding
import com.example.postviewer.postview.PostListAdapter

class CommentListFragment : Fragment() {

    private lateinit var binding: CommentListFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CommentListFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val application = requireNotNull(activity).application

        val post = CommentListFragmentArgs.fromBundle(requireArguments()).post
        val viewModelFactory = CommentListViewModelFactory(post, application)
        val viewModel = ViewModelProvider(
            this, viewModelFactory)[CommentListViewModel::class.java]
        binding.viewModel = viewModel
        binding.refreshLayout.setOnRefreshListener {
            binding.refreshLayout.isRefreshing=false
            viewModel.getComments()
        }
        binding.postList.adapter = CommentListAdapter()

        return binding.root
    }

}