package com.example.cricketscorecount.sevices.model2

class ExtraTable {

    var id: Int = 0
    var over: Int = 0
    var wicket: Int = 0



    constructor(over: Int, wicket: Int) {
        this.over = over
        this.wicket = wicket
    }

    constructor(){

    }
}