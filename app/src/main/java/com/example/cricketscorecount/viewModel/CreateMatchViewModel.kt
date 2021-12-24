package com.example.cricketscorecount.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cricketscorecount.sevices.model.TeamDatabase
import com.example.cricketscorecount.sevices.model.TeamName
import com.example.cricketscorecount.sevices.repository.TeamRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateMatchViewModel(application: Application): AndroidViewModel(application){

    public val repository: TeamRepository

    init {
        val teamDao = TeamDatabase.getDatabase(application).teamDao()
        repository = TeamRepository(teamDao)
    }



    fun addTeam(teamName: TeamName){
        viewModelScope.launch(Dispatchers.IO){
            repository.addTeam(teamName)
        }
    }
}