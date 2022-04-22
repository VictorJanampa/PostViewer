package com.example.postviewer.commentview

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.postviewer.R

class CommentListFragment : Fragment() {

    companion object {
        fun newInstance() = CommentListFragment()
    }

    private lateinit var viewModel: CommentListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.comment_list_fragment, container, false)
    }

}