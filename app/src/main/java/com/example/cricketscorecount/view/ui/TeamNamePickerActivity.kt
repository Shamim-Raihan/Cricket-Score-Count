package com.example.cricketscorecount.view.ui


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.cricketscorecount.R
import com.example.cricketscorecount.sevices.model2.DatabaseHandler
import com.example.cricketscorecount.sevices.model2.ExtraTable
import com.example.cricketscorecount.sevices.model2.TeamName2


class TeamNamePickerActivity : AppCompatActivity() {

    private lateinit var teamOneNameEd: EditText
    private lateinit var teamTwoNameEd: EditText
    private lateinit var submitButton: Button
    private lateinit var numberOfPlayerEd: EditText
    private lateinit var numberOfOverEd: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_name_picker)

        teamOneNameEd = findViewById(R.id.teamOne_id)
        teamTwoNameEd = findViewById(R.id.teamTwo_id)
        submitButton = findViewById(R.id.submitButton_id)
        numberOfPlayerEd = findViewById(R.id.numberOfPlayer_id)
        numberOfOverEd = findViewById(R.id.numberOfOver_id)

        submitButton.setOnClickListener {
            addNewTeamName()
        }
    }

    private fun addNewTeamName() {
        val teamOne = teamOneNameEd.text.toString()
        val teamTwo = teamTwoNameEd.text.toString()
        val totalRun: Int = 0
        val overPlayed: Double = 0.0
        val wicket: Int = 0
        val batingStatus = 0
        val completionStatus: Int = 0
        val numberOfPlayer = numberOfPlayerEd.text.toString()
        val numberOfOver = numberOfOverEd.text.toString()
        if (TextUtils.isEmpty(teamOne)){
            teamOneNameEd.setError("Enter first team name")
            teamOneNameEd.requestFocus()
            return
        }

        if (TextUtils.isEmpty(teamTwo)){
            teamTwoNameEd.setError("Enter second team name")
            teamTwoNameEd.requestFocus()
            return
        }

        if (TextUtils.isEmpty(numberOfPlayer)){
            numberOfPlayerEd.setError("Enter number of player")
            numberOfPlayerEd.requestFocus()
            return
        }

        if (TextUtils.isEmpty(numberOfOver)){
            numberOfOverEd.setError("Enter number of over")
            numberOfOverEd.requestFocus()
            return
        }

        var teamNameOne = TeamName2(teamOne, totalRun, overPlayed, wicket, batingStatus, completionStatus)
        var db = DatabaseHandler(this)
        var result: Long = db.insertData(teamNameOne)
        teamOneFun(result)


        var teamNameTwo = TeamName2(teamTwo, totalRun, overPlayed, wicket, batingStatus, completionStatus)
        var result2: Long = db.insertData(teamNameTwo)
        teamTwoFun(result2)

        var extraTable = ExtraTable(numberOfOver.toInt(), numberOfPlayer.toInt())
        var db2 = DatabaseHandler(this)
        var result3: Long = db2.extraTable(extraTable)
        extraTableFun(result3)

        val intent = Intent(this, TossActivity::class.java).apply {

        }
        startActivity(intent)
        finish()

    }

    private fun extraTableFun(result3: Long) {
        if (result3 == -1.toLong()){
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }
        else{
        }
    }

    private fun teamTwoFun(result2: Long) {
        if (result2 == -1.toLong()){
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }
        else{
        }
    }

    private fun teamOneFun(result: Long) {
        if (result == -1.toLong()){
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }
        else{
        }
    }

    private fun inputCheck(teamOne: String, teamTwo: String, numberOfPlayer: String, numberOfOver: String){
        if (TextUtils.isEmpty(teamOne)){
            teamOneNameEd.setError("Enter first team name")
            teamOneNameEd.requestFocus()
            return
        }

        if (TextUtils.isEmpty(teamTwo)){
            teamOneNameEd.setError("Enter second team name")
            teamOneNameEd.requestFocus()
            return
        }

        if (TextUtils.isEmpty(numberOfPlayer)){
            teamOneNameEd.setError("Enter number of player")
            teamOneNameEd.requestFocus()
            return
        }

        if (TextUtils.isEmpty(numberOfOver)){
            teamOneNameEd.setError("Enter number of over")
            teamOneNameEd.requestFocus()
            return
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TeamNamePickerActivity", "onDestroy: called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("TeamNamePickerActivity", "onResume: called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("TeamNamePickerActivity", "onPause: called")
    }

    override fun onStart() {
        super.onStart()
        Log.d("TeamNamePickerActivity", "onStart: called")
    }
}























