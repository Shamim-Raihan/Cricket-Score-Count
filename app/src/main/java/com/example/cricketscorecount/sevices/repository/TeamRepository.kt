package com.example.cricketscorecount.sevices.repository

import com.example.cricketscorecount.sevices.model.TeamDao
import com.example.cricketscorecount.sevices.model.TeamName

class TeamRepository(private val teamDao: TeamDao) {

    suspend fun addTeam(teamName: TeamName){
        teamDao.addTeam(teamName)
    }
}