package com.example.cricketscorecount.sevices.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Team_info")
data class TeamName(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val teamName: String,
    val totalRun: Int,
    val overPlayed: Double,
    val wicket: Int,
    val batingStatus: Int
)
