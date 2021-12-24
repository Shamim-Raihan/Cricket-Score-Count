package com.example.cricketscorecount.sevices.model2

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


const val DATABASE_NAME = "cricket_score_count"
const val TABLE_NAME = "team_info"
const val COL_TEAMNAME = "team_name"
const val COL_TOTAL_RUN = "total_run"
const val COL_OVER_PLAYED = "over_played"
const val COL_WICKET = "wicket"
const val COL_BATING_STATUS = "bating_status"
const val COL_BATING_COMPLETION_STATUS = "completion_status"
const val ID = "id"


const val EXTRA_TABLE_NAME = "extra"
const val EXTRA_ID = "id"
const val EXTRA_OVER = "over"
const val EXTRA_WICKET = "wicket"


const val HISTORY_TABLE_NAME = "history"
const val HISTORY_TABLE_ID = "id"
const val HISTORY_TABLE_OVER_NO = "over_no"
const val HISTORY_TABLE_OVER_HISTORY = "over_hostory"
const val HISTORY_TABLE_TEAM_ID = "team_id"


class DatabaseHandler(var context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, 18) {
    override fun onCreate(p0: SQLiteDatabase?) {
        val creatTable = "CREATE TABLE " + TABLE_NAME +"("+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_TEAMNAME + " VARCHAR(255)," +
                COL_TOTAL_RUN + " INTEGER(255)," + COL_OVER_PLAYED + " DOUBLE(255)," + COL_WICKET + " INTEGER(255)," + COL_BATING_STATUS + " INTEGER(255)," + COL_BATING_COMPLETION_STATUS + " INTEGER(255))"
        p0?.execSQL(creatTable)

        val extraCreateTable = "CREATE TABLE " + EXTRA_TABLE_NAME +"("+ EXTRA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + EXTRA_OVER + " INTEGER(255)," + EXTRA_WICKET + " INTEGER(255))"
        p0?.execSQL(extraCreateTable)

        val historyTable = "CREATE TABLE " + HISTORY_TABLE_NAME + "(" + HISTORY_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + HISTORY_TABLE_OVER_NO + " VARCHAR(255)," +
                HISTORY_TABLE_OVER_HISTORY + " VARCHAR(255)," + HISTORY_TABLE_TEAM_ID + " INTEGER(255))"
        p0?.execSQL(historyTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun insertData(teamName2: TeamName2): Long {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_TEAMNAME, teamName2.teamName)
        cv.put(COL_TOTAL_RUN, teamName2.totalRun)
        cv.put(COL_OVER_PLAYED, teamName2.overPlayed)
        cv.put(COL_WICKET, teamName2.wicket)
        cv.put(COL_BATING_STATUS, teamName2.batingStatus)
        cv.put(COL_BATING_COMPLETION_STATUS, teamName2.completionStatus)

        return db.insert(TABLE_NAME, null, cv)
    }

    fun extraTable(extraTable: ExtraTable): Long {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(EXTRA_OVER, extraTable.over)
        cv.put(EXTRA_WICKET, extraTable.wicket)
        return db.insert(EXTRA_TABLE_NAME, null, cv)
    }

    fun historyTable(historyTable: HistoryTable): Long {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(HISTORY_TABLE_OVER_NO, historyTable.over_no)
        cv.put(HISTORY_TABLE_OVER_HISTORY, historyTable.over_history)
        cv.put(HISTORY_TABLE_TEAM_ID, historyTable.team_id)
        return db.insert(HISTORY_TABLE_NAME, null, cv)
    }



    fun deleteDatabse(){
        context.deleteDatabase(DATABASE_NAME)
    }


    fun getTeamId(teamNmae: String): Int{
        var teamId: Int = 0
        val db = readableDatabase
        var projection: Array<String> = arrayOf(ID)
        var selection: String = COL_TEAMNAME + " LIKE ?"
        var selection_args: Array<String> = arrayOf(teamNmae)
        var result = db.query(TABLE_NAME, projection, selection, selection_args, null, null, null)
        if (result.moveToFirst()){
            do {
                teamId = result.getInt(0)
            }while (result.moveToNext())
        }
        return teamId
    }

    fun getAllTeamName(): ArrayList<TeamName2> {
        var list: ArrayList<TeamName2> = ArrayList<TeamName2>()
        val db = this.readableDatabase
        val  query = "select * from " + TABLE_NAME
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()){
            do {
                var teamName2 = TeamName2()
                teamName2.teamName = result.getString(1)
                list.add(teamName2)
            }
            while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }

    fun updateBatingStatus(status: Int, battingTeam: String){
        val db = writableDatabase
        var query = "SELECT * FROM " + TABLE_NAME
        var result = db.rawQuery(query, null)
        if (result.moveToFirst()){
            do {
                var cv = ContentValues()
                cv.put(COL_BATING_STATUS, status)
                db.update(TABLE_NAME, cv, COL_TEAMNAME + "=?", arrayOf(battingTeam))
            }while (result.moveToNext())
        }
    }


    fun getBowlingTeamName(): String {
        val db = readableDatabase
        var bowlingTeam: String = ""
        val query = "SELECT * FROM " + TABLE_NAME + " WHERE "+ COL_BATING_STATUS + " = " + 0
        var result = db.rawQuery(query, null)
        if (result.moveToFirst()){
            do {
                bowlingTeam = result.getString(1)
            }while (result.moveToNext())
        }
        return bowlingTeam
    }

    fun getBatingTeamName(): String {
        val db = readableDatabase
        var batingTeam: String = ""
        val query = "SELECT * FROM " + TABLE_NAME + " WHERE "+ COL_BATING_STATUS + " = " + 1
        var result = db.rawQuery(query, null)
        if (result.moveToFirst()){
            do {
                batingTeam = result.getString(1)
            }while (result.moveToNext())
        }
        return batingTeam
    }

    fun getBatingTeamId(): Int {
        val db = readableDatabase
        var batingId: Int = 0
        val query = "SELECT * FROM " + TABLE_NAME + " WHERE "+ COL_BATING_STATUS + " = " + 1
        var result = db.rawQuery(query, null)
        if (result.moveToFirst()){
            do {
                batingId = result.getInt(0)
            }while (result.moveToNext())
        }
        return batingId
    }


    fun getBowlingTeamId(): Int {
        val db = readableDatabase
        var batingId: Int = 0
        val query = "SELECT * FROM " + TABLE_NAME + " WHERE "+ COL_BATING_STATUS + " = " + 0
        var result = db.rawQuery(query, null)
        if (result.moveToFirst()){
            do {
                batingId = result.getInt(0)
            }while (result.moveToNext())
        }
        return batingId
    }

    fun getRunWicketOver(): Cursor? {
        val db = readableDatabase
        val query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_BATING_STATUS + " = " + 1
        var result = db.rawQuery(query, null)
        return result
    }

    fun getBatingTeamRun(): String {
        val db = readableDatabase
        var totaltun: String = ""
        var query = "SELECT * FROM " + TABLE_NAME + " WHERE "+ COL_BATING_STATUS + " = " + 1
        var result = db.rawQuery(query, null)
        if (result.moveToFirst()){
            do {
                totaltun = result.getString(2)
            }while (result.moveToNext())
        }
        return totaltun
    }

    fun getbowlingTeamRun(): String {
        val db = readableDatabase
        var totaltun: String = ""
        var query = "SELECT * FROM " + TABLE_NAME + " WHERE "+ COL_BATING_STATUS + " = " + 0
        var result = db.rawQuery(query, null)
        if (result.moveToFirst()){
            do {
                totaltun = result.getString(2)
            }while (result.moveToNext())
        }
        return totaltun
    }


    fun updateTotalRun(totalRun: Int, batingTeam: String){
        val db = writableDatabase
        var query = "SELECT * FROM " + TABLE_NAME
        var result = db.rawQuery(query, null)

        if (result.moveToFirst()){
            do {
                var cv = ContentValues()
                cv.put(COL_TOTAL_RUN, totalRun)
                db.update(TABLE_NAME, cv, COL_TEAMNAME + "=?", arrayOf(batingTeam))
            }while (result.moveToNext())
        }
    }

    fun updateWicket(wicket: Int, batingTeam: String){
        val db = writableDatabase
        var query = "SELECT * FROM " + TABLE_NAME
        var result = db.rawQuery(query, null)

        if (result.moveToFirst()){
            do {
                var cv = ContentValues()
                cv.put(COL_WICKET, wicket)
                db.update(TABLE_NAME, cv, COL_TEAMNAME + "=?", arrayOf(batingTeam))
            }while (result.moveToNext())
        }
    }

    fun updateOver(over: Double, batingTeam: String){
        val db = writableDatabase
        var query = "SELECT * FROM " + TABLE_NAME
        var result = db.rawQuery(query, null)

        if (result.moveToFirst()){
            do {
                var cv = ContentValues()
                cv.put(COL_OVER_PLAYED, over)
                db.update(TABLE_NAME, cv, COL_TEAMNAME + "=?", arrayOf(batingTeam))
            }while (result.moveToNext())
        }
    }

    fun getMatchWicket(): Cursor? {
        val db = readableDatabase
        val query = "SELECT * FROM " + EXTRA_TABLE_NAME + " WHERE " + EXTRA_ID + " = " + 1
        var result = db.rawQuery(query, null)
        return result
    }

    fun getMatchOver(): Cursor? {
        val db = readableDatabase
        val query = "SELECT * FROM " + EXTRA_TABLE_NAME + " WHERE " + EXTRA_ID + " = " + 1
        var result = db.rawQuery(query, null)
        return result
    }


    fun getTeam1Run(teamName: String): Int {
        var run: Int = 0
        val db = readableDatabase
        val query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_TEAMNAME + " = " + teamName
        var result = db.rawQuery(query, null)
        if (result.moveToFirst()){
            do {
                run = result.getInt(2)
            }while (result.moveToNext())
        }
        return run
    }


    fun getTeam2Run(teamName: String): Int {
        var run: Int = 0
        val db = readableDatabase
        val query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_TEAMNAME + " = " + teamName
        var result = db.rawQuery(query, null)
        if (result.moveToFirst()){
            do {
                run = result.getInt(2)
            }while (result.moveToNext())
        }
        return run
    }


    fun updateCompletionStatus(status: Int, batingTeam: String){
        val db = writableDatabase
        var query = "SELECT * FROM " + TABLE_NAME
        var result = db.rawQuery(query, null)

        if (result.moveToFirst()){
            do {
                var cv = ContentValues()
                cv.put(COL_BATING_COMPLETION_STATUS, status)
                db.update(TABLE_NAME, cv, COL_TEAMNAME + "=?", arrayOf(batingTeam))
            }while (result.moveToNext())
        }
    }

    fun getCompletionStatus1(): Int {
        var status: Int = 0
        val db = readableDatabase
        val query = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = " + 1
        var result = db.rawQuery(query, null)
        if (result.moveToFirst()){
            do {
                status = result.getInt(6);
            }while (result.moveToNext())
        }
        return status
    }

    fun getCompletionStatus2(): Int {
        var status: Int = 0
        val db = readableDatabase
        val query = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = " + 2
        var result = db.rawQuery(query, null)
        if (result.moveToFirst()){
            do {
                status = result.getInt(6);
            }while (result.moveToNext())
        }
        return status
    }


    fun getTeam1Info(): Cursor? {
        val db = readableDatabase
        val query = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = " + 1
        var result = db.rawQuery(query, null)
        return result
    }

    fun getTeam2Info(): Cursor? {
        val db = readableDatabase
        val query = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = " + 2
        var result = db.rawQuery(query, null)
        return result
    }

    fun getTotalIWicket(): Int {
        var wicket: Int = 0
        val db = readableDatabase
        val query = "SELECT * FROM " + EXTRA_TABLE_NAME + " WHERE " + EXTRA_ID + " = " + 1
        var result = db.rawQuery(query, null)

        if (result.moveToFirst()){
            do {
                wicket = result.getInt(2)
            }while (result.moveToNext())
        }
        return wicket
    }


    fun getHistoryTableDataExistence(): Cursor? {
        val db = readableDatabase
        val query = "SELECT * FROM " + HISTORY_TABLE_NAME + " WHERE " + HISTORY_TABLE_ID + " = " + 1
        var result = db.rawQuery(query, null)
        return result
    }

    fun getBatingTeamOverPlayed(): Double? {
        var overPlayed: Double? = null
        val db = readableDatabase
        val query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_BATING_STATUS + " = " + 1
        var result = db.rawQuery(query, null)

        if (result.moveToFirst()){
            do {
                overPlayed = result.getDouble(3)
            }while (result.moveToNext())
        }
        return overPlayed
    }


    fun getBatingTeamOverHistory(): Cursor? {
        val db = readableDatabase
        val query = "SELECT * FROM " + HISTORY_TABLE_NAME + " WHERE " + HISTORY_TABLE_TEAM_ID + " = " + getBatingTeamId()
        var result = db.rawQuery(query, null)
        return result
    }
}



















