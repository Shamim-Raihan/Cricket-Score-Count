package com.example.cricketscorecount.view.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.cricketscorecount.R
import com.example.cricketscorecount.sevices.model2.DatabaseHandler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val letsPlayButton = findViewById<Button>(R.id.lets_paly_button_id)
        letsPlayButton.setOnClickListener {



            val teamNamePickerActivity = Intent(this, TeamNamePickerActivity::class.java).apply {
            }
            startActivity(teamNamePickerActivity)
            finish()
        }
    }
}