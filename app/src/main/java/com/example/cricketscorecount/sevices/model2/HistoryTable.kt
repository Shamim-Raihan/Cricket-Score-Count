package com.example.cricketscorecount.sevices.model2

class HistoryTable {

    var id: Int = 0
    var over_no: String = ""
    var over_history: String = ""
    var team_id: Int = 0


    constructor(over_no: String, over_history: String, team_id: Int) {
        this.over_no = over_no
        this.over_history = over_history
        this.team_id = team_id
    }
}