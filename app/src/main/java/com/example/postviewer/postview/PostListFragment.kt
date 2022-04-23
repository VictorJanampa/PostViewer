package com.example.postviewer.postview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.postviewer.databinding.PostListFragmentBinding

class PostListFragment : Fragment() {

    private lateinit var binding: PostListFragmentBinding
    private val viewModel: PostListViewModel by lazy {
        ViewModelProvider(this)[PostListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PostListFragmentBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.postList.adapter = PostListAdapter(PostListAdapter.OnClickListener{
            viewModel.displayComments(it)
        })

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