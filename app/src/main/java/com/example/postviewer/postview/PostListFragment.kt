package com.example.postviewer.postview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.postviewer.R
import com.example.postviewer.databinding.PostListFragmentBinding

class PostListFragment : Fragment() {

    private lateinit var viewModel: PostListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: PostListFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.post_list_fragment,container,false)

        binding.text.setOnClickListener {
            Navigation.findNavController(it).navigate(PostListFragmentDirections.actionPostListFragmentToCommentListFragment())
        }
        return binding.root
    }

}