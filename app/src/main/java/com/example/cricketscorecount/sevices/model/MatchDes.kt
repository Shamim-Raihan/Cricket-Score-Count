package com.example.cricketscorecount.sevices.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "match_des")
data class MatchDes(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val over: Int,
    val wicket: Int
)