package com.example.cricketscorecount.sevices.model2

class TeamName2 {
    var id: Int = 0
    var teamName: String = ""
    var totalRun: Int = 0
    var overPlayed: Double = 0.0
    var wicket: Int = 0
    var batingStatus: Int = 0;
    var completionStatus: Int = 0

    constructor(){
    }

    constructor(
        teamName: String,
        totalRun: Int,
        overPlayed: Double,
        wicket: Int,
        batingStatus: Int,
        completionStatus: Int
    ) {
        this.teamName = teamName
        this.totalRun = totalRun
        this.overPlayed = overPlayed
        this.wicket = wicket
        this.batingStatus = batingStatus
        this.completionStatus = completionStatus
    }

}