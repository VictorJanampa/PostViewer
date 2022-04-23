package com.example.postviewer.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.postviewer.network.Comment
import com.example.postviewer.network.Post


@Database(entities = [Post::class, Comment::class], version = 1, exportSchema = false)
abstract class PostDatabase : RoomDatabase() {

    abstract val postDatabaseDao: PostDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: PostDatabase? = null
        fun getInstance(context: Context): PostDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PostDatabase::class.java,
                        "post_database"
                    ).allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}