package com.example.postviewer.postview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.postviewer.databinding.PostListFragmentBinding

class PostListFragment : Fragment() {

    private lateinit var binding: PostListFragmentBinding
    private lateinit var viewModel: PostListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PostListFragmentBinding.inflate(inflater)

        binding.lifecycleOwner = this

        val application = requireNotNull(this.activity).application

        val viewModelFactory = PostListViewModelFactory(application)

        viewModel = ViewModelProvider(
            this, viewModelFactory)[PostListViewModel::class.java]

        binding.viewModel = viewModel

        binding.postList.adapter = PostListAdapter(PostListAdapter.OnClickListener{
            viewModel.displayComments(it)
        })

        binding.refreshLayout.setOnRefreshListener {
            binding.refreshLayout.isRefreshing=false
            viewModel.fetchPosts()
        }

        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner) {
            if (null != it) {
                this.findNavController()
                    .navigate(PostListFragmentDirections.actionPostListFragmentToCommentListFragment(it))
                viewModel.displayCommentsComplete()
            }
        }

        return binding.root
    }

}