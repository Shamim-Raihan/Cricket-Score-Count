package com.example.cricketscorecount.view.ui

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Process
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.cricketscorecount.R
import com.example.cricketscorecount.sevices.model2.DatabaseHandler

class ResultActivity : AppCompatActivity() {

    lateinit var resultTv: TextView
    lateinit var team1RunWicketTv: TextView
    lateinit var team1OverTv: TextView
    lateinit var team2RunWicketTv: TextView
    lateinit var team2OverTv: TextView
    lateinit var exitAppButton: Button
    lateinit var nextMatchButon: Button

    var team1Name: String = ""
    var team1Run: Int = 0
    var team1Wicket: Int = 0
    var team1Over: Double = 0.0

    var team2Name: String = ""
    var team2Run: Int = 0
    var team2Wicket: Int = 0
    var team2Over: Double = 0.0
    val db = DatabaseHandler(this)

    var lastBatingTeamName: String = ""
    var matchWicket: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        resultTv = findViewById(R.id.resultId)
        team1RunWicketTv = findViewById(R.id.team1RunWicketResultId)
        team1OverTv = findViewById(R.id.team1OverResultId)
        team2RunWicketTv = findViewById(R.id.team2RunWicketResultId)
        team2OverTv = findViewById(R.id.team2OverResultId)
        exitAppButton = findViewById(R.id.exitAppId)
        nextMatchButon = findViewById(R.id.nextMatchId)


        lastBatingTeamName = db.getBatingTeamName()
        matchWicket = db.getTotalIWicket()


        exitAppButton.setOnClickListener(){
            var db = DatabaseHandler(this)
            db.deleteDatabse()
            finish()
        }

        nextMatchButon.setOnClickListener(){
            var db = DatabaseHandler(this)
            db.deleteDatabse()
            val intent = Intent(this, TeamNamePickerActivity::class.java).apply {  }
            startActivity(intent)
            finish()
        }

        var result = db.getTeam1Info()
        if (result != null){
            if (result.moveToFirst()){
                do {
                    team1Name = result.getString(1)
                    team1Run = result.getInt(2)
                    team1Over = result.getDouble(3)
                    team1Wicket = result.getInt(4)
                }while (result.moveToNext())
            }
        }

        var result2 = db.getTeam2Info()
        if (result2 != null){
            if (result2.moveToFirst()){
                do {
                    team2Name = result2.getString(1)
                    team2Run = result2.getInt(2)
                    team2Over = result2.getDouble(3)
                    team2Wicket = result2.getInt(4)
                }while (result2.moveToNext())
            }
        }


        if (team1Run > team2Run){
            val wicketDifference =  matchWicket - team1Wicket
            if (lastBatingTeamName.equals(team1Name)){
                resultTv.setText("Team " + team1Name + " won by " + wicketDifference + " wicket")
            }
            else {
                val runDifference = team1Run - team2Run
                resultTv.setText("Team " + team1Name + " won by " + runDifference + " run")
            }
        }

        else if (team2Run > team1Run){
            val wicketDifference =  matchWicket - team2Wicket
            if (lastBatingTeamName.equals(team2Name)){
                resultTv.setText("Team " + team2Name + " won by " + wicketDifference + " wicket")
            }
            else {
                val runDifference = team2Run - team1Run
                resultTv.setText("Team " + team2Name + " won by " + runDifference + " run")
            }
        }

        team1RunWicketTv.setText(team1Name + " (" + team1Run + "-" + team1Wicket + ")")
        team1OverTv.setText("Over - " + team1Over)

        team2RunWicketTv.setText(team2Name + " (" + team2Run + "-" + team2Wicket + ")")
        team2OverTv.setText("Over - " + team2Over)

    }


    override fun onBackPressed() {
        //super.onBackPressed();
        IsFinish("Want to close app?")
    }

    fun IsFinish(alertmessage: String?) {
        val dialogClickListener =
            DialogInterface.OnClickListener { dialog, which ->
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> Process.killProcess(Process.myPid())
                    DialogInterface.BUTTON_NEGATIVE -> {}
                }
            }
        val builder = AlertDialog.Builder(this)
        builder.setMessage(alertmessage)
            .setPositiveButton("Yes", dialogClickListener)
            .setNegativeButton("No", dialogClickListener).show()
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d("ResultActivity", "onDestroy: called")
        var db = DatabaseHandler(this)
        db.deleteDatabse()
    }


}