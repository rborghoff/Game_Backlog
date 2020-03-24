package com.example.gamebacklog.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.gamebacklog.model.Backlog

class BacklogRepository (context: Context) {
    private val backlogDao: BacklogDao
    init{
        val database = BacklogRoomDatabase.getDatabase(context)
      backlogDao = database!!.backlogDao()
    }

    fun getGameBacklog(): LiveData<Backlog?>{
        return backlogDao.getGameBacklog()
    }
    suspend fun updateGameBacklog(backlog: Backlog){
        backlogDao.updateBacklog(backlog)
    }
}