package com.example.gamebacklog.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.gamebacklog.database.BacklogRepository

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val backlogRepository = BacklogRepository(application.applicationContext)
    val backlog = backlogRepository.getGameBacklog()

}