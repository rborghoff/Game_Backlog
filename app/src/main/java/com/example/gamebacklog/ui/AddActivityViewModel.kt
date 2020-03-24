package com.example.gamebacklog.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.gamebacklog.database.BacklogRepository
import com.example.gamebacklog.model.Backlog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddActivityViewModel (application: Application): AndroidViewModel(application) {

    private val backlogRepository = BacklogRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    val backlog = MutableLiveData<Backlog?>()
    val error = MutableLiveData<String?>()
    val succes = MutableLiveData<Boolean>()

    fun updateBacklog(){
        if(isEntryValid()){
            mainScope.launch {
                withContext(Dispatchers.IO){
                    backlogRepository.updateGameBacklog(backlog.value!!)
                }
                succes.value = true
            }
        }
    }
    private fun isEntryValid():Boolean{
        return when {
            backlog.value == null -> {
                error.value = "Note must not be null"
                true
            }
            backlog.value!!.title.isBlank() -> {
                error.value = "Title must not be empty"
                true
            }
            backlog.value!!.platform.isBlank() ->{
                error.value = "Platform must not be empty"
                true
            }

            else -> true
        }
    }
}