package com.example.gamebacklog.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.gamebacklog.model.Backlog

@Dao
interface BacklogDao {

    @Query("SELECT * FROM backlogTable")
    fun getGameBacklog(): LiveData<Backlog?>

    @Insert
    suspend fun insertBacklog(backlog: Backlog)

    @Update
    suspend fun updateBacklog(backlog: Backlog)
}