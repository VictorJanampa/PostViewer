package com.example.postviewer.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.postviewer.network.Comment
import com.example.postviewer.network.Post

@Dao
interface PostDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPost(post: Post)

    @Query("SELECT * from post_table")
    fun getAllPosts(): List<Post>

    @Query("DELETE FROM post_table")
    fun clearPosts()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertComment(comments: Comment)

    @Query("SELECT * from comment_table")
    fun getAllComments(): LiveData<List<Comment>>

    @Query("DELETE FROM comment_table")
    fun clearComments()
}