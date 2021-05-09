package com.example.a7minutes_workout.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.a7minutes_workout.R
import com.example.a7minutes_workout.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), View.OnClickListener  {
    lateinit var main_binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main_binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        main_binding.mainStart.setOnClickListener(this)
        main_binding.mainBmi.setOnClickListener(this)
        main_binding.mainCalender.setOnClickListener(this)
        main_binding.mainAgeInMinute.setOnClickListener(this)
        anim()


    }

    private fun anim() {
        var anim=AnimationUtils.loadAnimation(this, R.anim.item_fadeout_anim)
        main_binding.mainStart.animation=anim
        main_binding.mainCalender.animation=anim
        main_binding.mainBmi.animation=anim
        main_binding.textView8.animation=anim
        main_binding.textView10.animation=anim
        main_binding.mainAgeInMinute.animation=anim
        main_binding.textView11.animation=anim
        anim.start()

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.main_start -> {
                val intent = Intent(this, exersice::class.java)
                startActivity(intent)
            }
            R.id.main_bmi -> {
                var intent_bmi = Intent(this, BMI::class.java)
                startActivity(intent_bmi)
            }
            R.id.main_calender -> {
                var intent = Intent(this, History::class.java)
                startActivity(intent)
            }
            R.id.main_age_in_minute ->{
                var intent=Intent(this,ageinminute::class.java)
                startActivity(intent)
            }
        }
    }


}