package com.example.cricketscorecount.sevices.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.stream.Stream

@Entity(tableName = "match_history")
data class MatchHistory(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val overNo: Int,
    val overHistory: String,
    val teamID: Int

)
