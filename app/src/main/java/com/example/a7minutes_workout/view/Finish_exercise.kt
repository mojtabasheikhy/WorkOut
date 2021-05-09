package com.example.a7minutes_workout.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.a7minutes_workout.R
import com.example.a7minutes_workout.database.MySqliteOpenHelper
import com.example.a7minutes_workout.databinding.ActivityFinishExerciseBinding
import saman.zamani.persiandate.PersianDate
import saman.zamani.persiandate.PersianDateFormat
import java.text.SimpleDateFormat
import java.util.*

class finish_exercise : AppCompatActivity(), View.OnClickListener {

    lateinit var finishExerciseBinding: ActivityFinishExerciseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        finishExerciseBinding=DataBindingUtil.setContentView(this,
            R.layout.activity_finish_exercise
        )
        setSupportActionBar(finishExerciseBinding.toolbarFinishExercise)
        var actionbar = supportActionBar
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.title = "اتمام تمرین"
        }
        finishExerciseBinding.toolbarFinishExercise.setNavigationIcon(R.drawable.ic_back)
        finishExerciseBinding.toolbarFinishExercise.setNavigationOnClickListener {
            onBackPressed()
        }
        finishExerciseBinding.button.setOnClickListener(this)
        finishExerciseBinding.finishStartAgain.setOnClickListener(this)
        save_data_to_database()

    }
    fun save_data_to_database(){
        /**english date*/
        var calender= Calendar.getInstance()
        var time=calender.time
        var simpele_data= SimpleDateFormat("DD MMM yyyy HH:mm:ss", Locale.getDefault())
        var date_custom_format = simpele_data.format(time)


        /**persian date*/
        val pdate = PersianDate()
        val pdformater = PersianDateFormat("Y/m/d  l  H:i:s" )
        val persian_date=pdformater.format(pdate)
        val handler = MySqliteOpenHelper(this,null)
        handler.addDATE(persian_date)


    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.button -> {
              startActivity(Intent(this,MainActivity::class.java))
                finish()
            }
            R.id.finish_start_again -> {
                startActivity(Intent(this,exersice::class.java))
                finish()
            }
        }
    }


}