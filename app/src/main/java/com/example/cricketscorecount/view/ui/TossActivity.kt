package com.example.cricketscorecount.view.ui

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Process
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.cricketscorecount.R
import com.example.cricketscorecount.sevices.model2.DatabaseHandler

class TossActivity : AppCompatActivity() {


    private lateinit var teamNameSpinner: Spinner
    private lateinit var submitButton: Button
    private lateinit var batingTeam: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toss)


        teamNameSpinner = findViewById(R.id.teamName_id)
        submitButton = findViewById(R.id.submitButton_ID)



        var db = DatabaseHandler(this)
        var teamName = db.getAllTeamName()

        val teamList = arrayOfNulls<String>(2)

        teamList[0] = teamName[0].teamName
        teamList[1] = teamName[1].teamName

        if (teamNameSpinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, teamList
            )
            teamNameSpinner.adapter = adapter

            teamNameSpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                    batingTeam = teamList[position].toString()
                }
                override fun onNothingSelected(parent: AdapterView<*>) {
                }
            }
        }

        submitButton.setOnClickListener(){
            db = DatabaseHandler(this)
            var id: Int = db.getTeamId(batingTeam)
            db = DatabaseHandler(this)
            db.updateBatingStatus(1, batingTeam)
            val intent = Intent(this, ScoreCountActivity::class.java).apply {

            }
            startActivity(intent)
            finish()
        }
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
        Log.d("TeamNamePickerActivity", "onDestroy: called")
        var db = DatabaseHandler(this)
        db.deleteDatabse()
    }
}