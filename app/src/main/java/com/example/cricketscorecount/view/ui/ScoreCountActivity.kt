package com.example.cricketscorecount.view.ui

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Process
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.cricketscorecount.R
import com.example.cricketscorecount.sevices.model2.DatabaseHandler
import com.example.cricketscorecount.sevices.model2.HistoryTable
import org.w3c.dom.Text
import java.lang.StringBuilder

class ScoreCountActivity : AppCompatActivity() {

    private lateinit var bowlingTeamNameTv: TextView
    private lateinit var batingTeamNameTv: TextView
    private lateinit var totalRunTv: TextView
    private lateinit var teamWicketTv: TextView
    private lateinit var overPlayedTv: TextView

    private lateinit var thisOverTv: TextView
    private lateinit var currentRunRateTv: TextView

    private lateinit var runNeededTv: TextView
    private lateinit var remainBallTv: TextView
    private lateinit var requiredRunRateTv: TextView

    private lateinit var runOneTv: TextView
    private lateinit var runTwoTv: TextView
    private lateinit var runThreeTv: TextView
    private lateinit var runFourTv: TextView
    private lateinit var runSixTv: TextView

    private lateinit var wdTv: TextView
    private lateinit var nbTv: TextView

    private lateinit var noRunTv: TextView
    private lateinit var wicketTv: TextView

    private lateinit var previousBallBtn: Button
    private lateinit var historyBtn: Button
    private lateinit var nextBallBtn: Button
    private lateinit var batingTeam: String

    private lateinit var comparisonLayout: LinearLayout
    private lateinit var thisOverLayput: LinearLayout
    private lateinit var relativeLayout: RelativeLayout

    private var one: Int = 0
    private var two: Int = 0
    private var three: Int = 0
    private var four: Int = 0
    private var six: Int = 0
    private var noBall: Int = 0
    private var checkOnClicked: Int = 0
    private var overHistory: String = ""
    private var overChecker: Int = 0


    private lateinit var wicketDropedTv: TextView


    private lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_count)

        bowlingTeamNameTv = findViewById(R.id.team1)
        batingTeamNameTv = findViewById(R.id.team2)
        totalRunTv = findViewById(R.id.totalRunId)
        teamWicketTv = findViewById(R.id.teamWicketId)
        overPlayedTv = findViewById(R.id.totalOverId)
        thisOverTv = findViewById(R.id.thisOverId)
        currentRunRateTv = findViewById(R.id.currentRunRateId)
        runNeededTv = findViewById(R.id.runNeededId)
        remainBallTv = findViewById(R.id.remainBallId)
        requiredRunRateTv = findViewById(R.id.requiredRunRateId)
        runOneTv = findViewById(R.id.runOneId)
        runTwoTv = findViewById(R.id.runTwoId)
        runThreeTv = findViewById(R.id.runThreeId)
        runFourTv = findViewById(R.id.runFourId)
        runSixTv = findViewById(R.id.runSixId)

        wdTv = findViewById(R.id.wdId)
        nbTv = findViewById(R.id.nbId)

        noRunTv = findViewById(R.id.noRunId)
        wicketTv = findViewById(R.id.wicketId)

        previousBallBtn = findViewById(R.id.previousBtnId)
        historyBtn = findViewById(R.id.historyBtnId)
        nextBallBtn = findViewById(R.id.nextBallId)

        comparisonLayout = findViewById(R.id.comparisonLayoutId)
        thisOverLayput = findViewById(R.id.thisOverLayoutId)
        relativeLayout = findViewById(R.id.relativeLayoutId)
        dialog = Dialog(this)


//        thisOverLayput.visibility = View.VISIBLE
//
//        relativeLayout.setOnClickListener(){
//            if (thisOverLayput.visibility == View.VISIBLE){
//                thisOverLayput.visibility = View.INVISIBLE
//                comparisonLayout.visibility = View.VISIBLE
//
//            }
//            if (comparisonLayout.visibility == View.VISIBLE){
//                comparisonLayout.visibility = View.INVISIBLE
//                thisOverLayput.visibility = View.VISIBLE
//
//            }
//        }
//
//        thisOverLayput.setOnClickListener(){
//            thisOverLayput.visibility = View.INVISIBLE
//            comparisonLayout.visibility = View.VISIBLE
//
//        }
//
//        comparisonLayout.setOnClickListener(){
//            comparisonLayout.visibility = View.INVISIBLE
//            thisOverLayput.visibility = View.VISIBLE
//
//        }


        var db = DatabaseHandler(this)
        batingTeam = db.getBatingTeamName()

        bowlingTeamNameSeter()
        batingTeamNameSeter()
        runWickerOverSeter()

        var drawable: Drawable? = getDrawable(R.drawable.run_selector_selected)
        var drawable2: Drawable? = getDrawable(R.drawable.run_selector_design)

        runOneTv.setOnClickListener() {
            var currentRun: Int = 0
            runOneTv.setBackground(drawable)
            var preRun = db.getBatingTeamRun().toInt()
            currentRun = preRun + 1
            db.updateTotalRun(currentRun, batingTeam)

            var matchOver = getMatchOver()
            var over = getOver()
            checker(matchOver, over)
            inningsChecker()
            checkOnClicked = 1
            overHistory = overHistory + "1"
        }


        runTwoTv.setOnClickListener() {
            var currentRun: Int = 0
            runTwoTv.setBackground(drawable)
            var preRun = db.getBatingTeamRun().toInt()
            currentRun = preRun + 2
            db.updateTotalRun(currentRun, batingTeam)

            var matchOver = getMatchOver()
            var over = getOver()
            checker(matchOver, over)
            inningsChecker()
            checkOnClicked = 1
            overHistory = overHistory + "2"
        }

        runThreeTv.setOnClickListener() {
            var currentRun: Int = 0
            runThreeTv.setBackground(drawable)
            var preRun = db.getBatingTeamRun().toInt()
            currentRun = preRun + 3
            db.updateTotalRun(currentRun, batingTeam)

            var matchOver = getMatchOver()
            var over = getOver()
            checker(matchOver, over)
            inningsChecker()
            checkOnClicked = 1
            overHistory = overHistory + "3"
        }

        runFourTv.setOnClickListener() {
            var currentRun: Int = 0
            runFourTv.setBackground(drawable)
            var preRun = db.getBatingTeamRun().toInt()
            currentRun = preRun + 4
            db.updateTotalRun(currentRun, batingTeam)

            var matchOver = getMatchOver()
            var over = getOver()
            checker(matchOver, over)
            inningsChecker()
            checkOnClicked = 1
            overHistory = overHistory + "4"
        }

        runSixTv.setOnClickListener() {
            var currentRun: Int = 0
            runSixTv.setBackground(drawable)
            var preRun = db.getBatingTeamRun().toInt()
            currentRun = preRun + 6
            db.updateTotalRun(currentRun, batingTeam)

            var matchOver = getMatchOver()
            var over = getOver()
            checker(matchOver, over)
            inningsChecker()
            checkOnClicked = 1
            overHistory = overHistory + "6"
        }

        wdTv.setOnClickListener() {
            var currentRun: Int = 0
            wdTv.setBackground(drawable)
            var preRun = db.getBatingTeamRun().toInt()
            currentRun = preRun + 1
            db.updateTotalRun(currentRun, batingTeam)
            noBall = 1
            checkOnClicked = 1
            overHistory = overHistory + "wd"
        }

        nbTv.setOnClickListener() {
            var currentRun: Int = 0
            nbTv.setBackground(drawable)
            var preRun = db.getBatingTeamRun().toInt()
            currentRun = preRun + 1
            db.updateTotalRun(currentRun, batingTeam)
            noBall = 1
            checkOnClicked = 1
            overHistory = overHistory + "nb"
        }

        noRunTv.setOnClickListener() {
            var currentRun: Int = 0
            noRunTv.setBackground(drawable)
            var preRun = db.getBatingTeamRun().toInt()
            currentRun = preRun + 0
            db.updateTotalRun(currentRun, batingTeam)

            var matchOver = getMatchOver()
            var over = getOver()
            checker(matchOver, over)
            inningsChecker()
            checkOnClicked = 1
            overHistory = overHistory + "0"
        }

        wicketTv.setOnClickListener() {
            var currentRun: Int = 0
            wicketTv.setBackground(drawable)
            var preRun = db.getBatingTeamRun().toInt()
            currentRun = preRun + 0
            db.updateTotalRun(currentRun, batingTeam)
            var matchWicket = getMatchWicket()
            var wicket = getWicket()
            if (matchWicket - 2 == wicket) {
                nextBallBtn.setText("next innings")
                var updateWicket: Int = wicket + 1
                db.updateWicket(updateWicket, batingTeam)
            } else {
                var updateWicket: Int = wicket + 1
                db.updateWicket(updateWicket, batingTeam)
            }
            var matchOver = getMatchOver()
            var over = getOver()
            checker(matchOver, over)
            inningsChecker()
            checkOnClicked = 1
            overHistory = overHistory + "w"
        }

        nextBallBtn.setOnClickListener() {
            var over: Double = 0.0
            var matchOver: Int = 0

            if (checkOnClicked == 0) {
                Toast.makeText(applicationContext, "please select a action", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }

            checkOnClicked = 0
            if (noBall == 0) {

                var matchOver = getMatchOver()
                var over = getOver()

                var matchWicket = getMatchWicket()
                var wicket = getWicket()
                val bowlingTeamRun = db.getbowlingTeamRun()

                if (((matchOver == (over + .5).toInt()) || (matchWicket - 1 == wicket)) && bowlingTeamRun.toInt() == 0) {
                    val updateOver = over + .5
                    val finalOver = String.format("%.1f", updateOver)
                    db.updateOver(finalOver.toDouble(), batingTeam)



                    db.updateBatingStatus(0, batingTeam)
                    var batingTeam = db.getBowlingTeamName()
                    db.updateBatingStatus(1, batingTeam)

                    val team1Status = db.getCompletionStatus1()
                    val team2Status = db.getCompletionStatus2()


                    if (((team1Status == 1) && (team2Status == 1))) {
                        var batingTeamId: Int = db.getBatingTeamId()
                        var historyTable = HistoryTable(finalOver, overHistory, batingTeamId)
                        var db = DatabaseHandler(this)
                        var result = db.historyTable(historyTable)
                        overHistory = ""

                        val intent = Intent(this, ResultActivity::class.java).apply { }
                        startActivity(intent)
                        finish()
                    } else {

                        var batingTeamId: Int = db.getBowlingTeamId()
                        var historyTable = HistoryTable(finalOver, overHistory, batingTeamId)
                        var db = DatabaseHandler(this)
                        var result = db.historyTable(historyTable)
                        overHistory = ""

                        val intent = Intent(this, ScoreCountActivity::class.java).apply { }
                        startActivity(intent)
                    }
                } else {
                    val to = over * 10
                    val overCheck = (to % 10).toInt()
                    if (overCheck == 5) {
                        val updateOver = over + .5
                        val finalOver = String.format("%.1f", updateOver)
                        db.updateOver(finalOver.toDouble(), batingTeam)

                        var batingTeamId: Int = db.getBatingTeamId()
                        var historyTable = HistoryTable(finalOver.toString(), overHistory, batingTeamId)
                        var db = DatabaseHandler(this)
                        var result = db.historyTable(historyTable)
                        overHistory = ""


                        val batingTeamRun = db.getBatingTeamRun()
                        val bowlingTeamRun = db.getbowlingTeamRun()

                        val forWicket = db.getRunWicketOver()
                        val forMatchW = db.getMatchWicket()
                        var wicket: Int = 0
                        var mWicket: Int = 0

                        if (forWicket != null){
                            if (forWicket.moveToFirst()){
                                do {
                                    wicket = forWicket.getInt(4)
                                }while (forWicket.moveToNext())
                            }
                        }

                        if (forMatchW != null){
                            if (forMatchW.moveToFirst()){
                                do {
                                    mWicket = forMatchW.getInt(2)
                                }while (forMatchW.moveToNext())
                            }
                        }

                        if (bowlingTeamRun.toInt() != 0){
                            if ((batingTeamRun.toInt() > bowlingTeamRun.toInt()) || (wicket == (mWicket-1))) {


                                var batingTeamId: Int = db.getBatingTeamId()
                                var historyTable = HistoryTable(finalOver.toString(), overHistory, batingTeamId)
                                var db = DatabaseHandler(this)
                                var result = db.historyTable(historyTable)
                                overHistory = ""

                                val intent = Intent(this, ResultActivity::class.java).apply { }
                                startActivity(intent)
                                finish()
                            }
                        }

                    } else {
                        val updateOver = over + .1
                        val finalOver = String.format("%.1f", updateOver.toDouble())
                        db.updateOver(finalOver.toDouble(), batingTeam)

                        val batingTeamRun = db.getBatingTeamRun()
                        val bowlingTeamRun = db.getbowlingTeamRun()

                        val forWicket = db.getRunWicketOver()
                        val forMatchW = db.getMatchWicket()
                        var wicket: Int = 0
                        var mWicket: Int = 0

                        if (forWicket != null){
                            if (forWicket.moveToFirst()){
                                do {
                                    wicket = forWicket.getInt(4)
                                }while (forWicket.moveToNext())
                            }
                        }

                        if (forMatchW != null){
                            if (forMatchW.moveToFirst()){
                                do {
                                    mWicket = forMatchW.getInt(2)
                                }while (forMatchW.moveToNext())
                            }
                        }

                        if (bowlingTeamRun.toInt() != 0){
                            if ((batingTeamRun.toInt() > bowlingTeamRun.toInt()) || (wicket == (mWicket-1))) {

                                var batingTeamId: Int = db.getBatingTeamId()
                                var historyTable = HistoryTable(finalOver.toString(), overHistory, batingTeamId)
                                var db = DatabaseHandler(this)
                                var result = db.historyTable(historyTable)
                                overHistory = ""

                                val intent = Intent(this, ResultActivity::class.java).apply { }
                                startActivity(intent)
                                finish()
                            }
                        }
                    }

                    runWickerOverSeter()
                    runOneTv.setBackground(drawable2)
                    runTwoTv.setBackground(drawable2)
                    runThreeTv.setBackground(drawable2)
                    runFourTv.setBackground(drawable2)
                    runSixTv.setBackground(drawable2)
                    wdTv.setBackground(drawable2)
                    nbTv.setBackground(drawable2)
                    noRunTv.setBackground(drawable2)
                    wicketTv.setBackground(drawable2)

                    currentRunRate()

                    thisOverTv.setText(overHistory)

                    if (overHistory.equals("")){
                        overHistory = overHistory + ""
                    }
                    else {
                        overHistory = overHistory + ","
                    }
                }
            }
            if (noBall == 1) {
                noBall = 0
                runWickerOverSeter()
                runOneTv.setBackground(drawable2)
                runTwoTv.setBackground(drawable2)
                runThreeTv.setBackground(drawable2)
                runFourTv.setBackground(drawable2)
                runSixTv.setBackground(drawable2)
                wdTv.setBackground(drawable2)
                nbTv.setBackground(drawable2)
                noRunTv.setBackground(drawable2)
                wicketTv.setBackground(drawable2)

                currentRunRate()

                thisOverTv.setText(overHistory)
                overHistory = overHistory + ","

            }
        }

        historyBtn.setOnClickListener() {
            val result = db.getBatingTeamOverHistory()

            if (result != null){
                val sb = StringBuilder()

                while (result.moveToNext()){
                    sb?.append("Over " + result.getDouble(1) + "  -  " + result.getString(2) + "\n")
                }
                showData(sb.toString())

            }

        }
    }

    private fun showData(data: String) {
        dialog.setContentView(R.layout.history_dialog_box)
        dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val historyTv: TextView = dialog.findViewById(R.id.historyShowId)
        historyTv.setText(data)
        dialog.setCancelable(true)
        dialog.show()
    }

    private fun currentRunRate() {
        val db = DatabaseHandler(this)
        val batingTeamRun = db.getBatingTeamRun()
        val overPlayed = db.getBatingTeamOverPlayed()
        val currentRunRate = batingTeamRun.toDouble()/ overPlayed!!
        val finalcurrentRunRate = String.format("%.1f", currentRunRate)
        currentRunRateTv.setText(finalcurrentRunRate)
    }

    private fun inningsChecker() {
        val db = DatabaseHandler(this)
        val team1 = db.getCompletionStatus1()
        val team2 = db.getCompletionStatus2()
        val batingTeamRun = db.getBatingTeamRun()
        val bowlingTeamRun = db.getbowlingTeamRun()
        val forWicket = db.getRunWicketOver()
        val forMatchW = db.getMatchWicket()
        var wicket: Int = 0
        var mWicket: Int = 0

        if (forWicket != null){
            if (forWicket.moveToFirst()){
                do {
                    wicket = forWicket.getInt(4)
                }while (forWicket.moveToNext())
            }
        }

        if (forMatchW != null){
            if (forMatchW.moveToFirst()){
                do {
                    mWicket = forMatchW.getInt(2)
                }while (forMatchW.moveToNext())
            }
        }

        if (bowlingTeamRun.toInt() != 0){
            if ((((team1 == 1) && (team2 == 1)) || batingTeamRun.toInt() > bowlingTeamRun.toInt()) || (wicket == (mWicket-1))) {
                db.updateCompletionStatus(1, batingTeam)
                nextBallBtn.setText("Finish Match")
            }
        }
    }

    private fun getWicket(): Int {
        var wicket: Int = 0
        val db = DatabaseHandler(this)
        var result = db.getRunWicketOver()
        if (result != null) {
            if (result.moveToFirst()) {
                do {
                    wicket = result.getInt(4)
                } while (result.moveToNext())
            }
        }
        return wicket
    }

    private fun getMatchWicket(): Int {
        var matchWicket: Int = 0
        val db = DatabaseHandler(this)
        var cursor = db.getMatchWicket()
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    matchWicket = cursor.getInt(2)
                } while (cursor.moveToNext())
            }
        }
        return matchWicket
    }


    private fun checker(matchOver: Int, over: Double) {
        val db = DatabaseHandler(this)
        if ((matchOver == (over + .5).toInt())) {
            db.updateCompletionStatus(1, batingTeam)
            nextBallBtn.setText("next Innings")
        }
    }

    private fun getOver(): Double {
        var over: Double = 0.0
        val db = DatabaseHandler(this)
        var result = db.getRunWicketOver()
        if (result != null) {
            if (result.moveToFirst()) {
                do {
                    over = result.getDouble(3)
                } while (result.moveToNext())
            }
        }
        return over
    }

    private fun getMatchOver(): Int {
        var matchOver: Int = 0
        val db = DatabaseHandler(this)
        var cursor = db.getMatchOver()
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    matchOver = cursor.getInt(1)
                } while (cursor.moveToNext())
            }
        }
        return matchOver
    }

    private fun runWickerOverSeter() {
        var db = DatabaseHandler(this)
        var result = db.getRunWicketOver()

        if (result != null) {
            if (result.moveToFirst()) {
                do {
                    var run = result.getInt(2)
                    var wicket = result.getInt(4)
                    var over = result.getDouble(3)

                    totalRunTv.setText(run.toString())
                    teamWicketTv.setText(wicket.toString())
                    overPlayedTv.setText(over.toString())

                } while (result.moveToNext())
            }
        }
    }

    private fun batingTeamNameSeter() {
        var db = DatabaseHandler(this)
        var batingTeamName = db.getBatingTeamName()
        batingTeamNameTv.setText(batingTeamName).toString()
    }

    private fun bowlingTeamNameSeter() {
        var db = DatabaseHandler(this)
        var bowlingTeamName = db.getBowlingTeamName()
        bowlingTeamNameTv.setText(bowlingTeamName).toString()
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









