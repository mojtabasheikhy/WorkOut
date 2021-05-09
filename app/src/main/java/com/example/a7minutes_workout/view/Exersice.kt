package com.example.a7minutes_workout.view

import android.animation.Animator
import android.app.AlertDialog
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.Lottie
import com.airbnb.lottie.LottieAnimationView
import com.example.a7minutes_workout.Const_Exercise
import com.example.a7minutes_workout.R
import com.example.a7minutes_workout.database.Main_adapter
import com.example.a7minutes_workout.databinding.ActivityExersiceBinding
import com.example.a7minutes_workout.databinding.CustomDioalogQuitBinding
import com.example.a7minutes_workout.model.Exercisemodel
import java.util.*


class exersice : AppCompatActivity(), TextToSpeech.OnInitListener {
    lateinit var exersice_bind: ActivityExersiceBinding
    var reset_timer: CountDownTimer? = null
    var exercise_list: ArrayList<Exercisemodel>? = null
    var reset_timer_exersice_start: CountDownTimer? = null
    var reset_progress = 0
    var reset_progress_exercise_start = 0
    var current_postion = -1
    var media: MediaPlayer? = null
    var text_to_speech: TextToSpeech? = null
    var adapter_custom: Main_adapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exersice_bind = DataBindingUtil.setContentView(this, R.layout.activity_exersice)
        setSupportActionBar(exersice_bind.exersiceToolbar)

        text_to_speech = TextToSpeech(this, this)

        if (exercise_list == null) {
            exercise_list = Const_Exercise.exercise_list()
        }
        //count_down_timer


        resetview_progressbar_count_down_timer()

        ///actionbar setuap
        var actionbar = supportActionBar
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.title = "شروع تمرین"
        }
        exersice_bind.exersiceToolbar.setNavigationIcon(R.drawable.ic_back)

        exersice_bind.exersiceToolbar.setNavigationOnClickListener {
            val dialogBinding: CustomDioalogQuitBinding? =
                DataBindingUtil.inflate(
                    LayoutInflater.from(this),
                    R.layout.custom_dioalog_quit,
                    null,
                    false
                )

            val customDialog = AlertDialog.Builder(this).create()

            customDialog.apply {
                setView(dialogBinding?.root)
                setCancelable(true)
            }.show()
            dialogBinding?.alertDialogYes?.setOnClickListener {
                customDialog?.dismiss()
                finish()
            }
            dialogBinding?.alertDialogNo?.setOnClickListener {
                customDialog?.dismiss()

            }

        }

        //adapter
        setup_adapter()

    }

    private fun setup_adapter() {
        adapter_custom = exercise_list?.let { Main_adapter(it, this) }
        exersice_bind.exerciseRecyclerView.apply {
            adapter = adapter_custom
            layoutManager = LinearLayoutManager(this@exersice, RecyclerView.HORIZONTAL, false)
        }
    }

    private fun set_reset_progresbar() {
        exersice_bind.exersiceCountDownTimerProgressbar.progress = reset_progress
        reset_timer = object : CountDownTimer(11000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                reset_progress++
                exersice_bind.exersiceCountDownTimerProgressbar.progress = 11 - reset_progress
                exersice_bind.exersiceCountDownTimerTv.text = (11 - reset_progress).toString()
            }

            override fun onFinish() {
                exersice_bind.exerciseIv.playAnimation()

                current_postion++
                adapter_custom!!.notifyDataSetChanged()
                resetview_progressbar_count_down_timer_exercise()
            }

        }.start()
    }


    fun resetview_progressbar_count_down_timer() {
        /**song*/
        try {
            //var song=uri.parse("android:recouce//")
            media = MediaPlayer.create(this, R.raw.press_start)
            media!!.isLooping
            media!!.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        if (reset_timer != null) {
            reset_timer!!.cancel()
            reset_progress = 0
        }
        exercise_list!![current_postion + 1].isSelected = true
        Speakout(exercise_list!![current_postion + 1].name.toString())
        exersice_bind.exerciseGetreadyTv.visibility = View.VISIBLE
        exersice_bind.exerciseNameTvBig.visibility = View.VISIBLE
        exersice_bind.frameLayout.visibility = View.VISIBLE
        exersice_bind.exerciseNameTvBig.visibility = View.VISIBLE
        exersice_bind.frameLayoutExersiceStart.visibility = View.GONE
        exersice_bind.exerciseIv.visibility = View.GONE
        exersice_bind.exerciseAlert.visibility=View.GONE

        exersice_bind.exersiceNameTvSmall.visibility = View.GONE

        exersice_bind.exerciseNameTvBig.text = exercise_list!![current_postion + 1].name.toString()
    

        set_reset_progresbar()
    }

    fun resetview_progressbar_count_down_timer_exercise() {
        if (reset_timer_exersice_start != null) {
            reset_timer_exersice_start!!.cancel()
            reset_progress_exercise_start = 0
        }
        /**text to Speech*/
        Speakout(exercise_list!![current_postion].name.toString())

        exersice_bind.exersiceNameTvSmall.visibility = View.VISIBLE
        exersice_bind.exerciseIv.visibility = View.VISIBLE
        exersice_bind.frameLayoutExersiceStart.visibility = View.VISIBLE
        exersice_bind.exersiceNameTvSmall.visibility = View.VISIBLE
        exersice_bind.exerciseGetreadyTv.visibility = View.GONE
        exersice_bind.exerciseNameTvBig.visibility = View.GONE
        exersice_bind.frameLayout.visibility = View.GONE
        exersice_bind.exerciseAlert.visibility=View.VISIBLE

        /**bind the name of exercise and picture*/
        exersice_bind.exersiceNameTvSmall.text = exercise_list!![current_postion].name
        exersice_bind.exerciseIv.setAnimation(exercise_list!![current_postion].pic_url)
        exersice_bind.exerciseIv.setOnClickListener {
            exersice_bind.exerciseIv.playAnimation()
        }
        exersice_bind.exerciseIv.playAnimation()


        exercise_start()


    }


    fun exercise_start() {
        reset_timer_exersice_start = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                reset_progress_exercise_start++


                exersice_bind.exersiceCountDownTimerProgressbarExercisestart.progress =
                    30 - reset_progress_exercise_start
                exersice_bind.exersiceCountDownTimerTvExerciseStart.text =
                    (30- reset_progress_exercise_start).toString()
            }

            override fun onFinish() {
                if (current_postion < exercise_list!!.size - 1) {
                    exercise_list!![current_postion].isSelected = false
                    exercise_list!![current_postion].iscomplete = true

                    Log.e("is", exercise_list!![current_postion].iscomplete.toString())
                    Log.e("is", exercise_list!![current_postion].isSelected.toString())

                    adapter_custom!!.notifyDataSetChanged()
                    resetview_progressbar_count_down_timer()
                } else {
                    var intent = Intent(this@exersice, finish_exercise::class.java)
                    startActivity(intent)
                    Toast.makeText(
                        this@exersice, "آفرین برای امروز کافیه" +
                                "", Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }
            }

        }.start()

    }

    override fun onDestroy() {
        if (reset_timer != null) {
            reset_timer!!.cancel()
            reset_progress = 0
        }
        if (reset_timer_exersice_start != null) {
            reset_timer_exersice_start!!.cancel()
            reset_progress_exercise_start = 0
        }
        if (media != null) {
            media!!.stop()
        }

        super.onDestroy()


    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            var resualt = text_to_speech?.setLanguage(Locale.US)
            if (resualt == TextToSpeech.LANG_MISSING_DATA || resualt == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(this, " زبان انگلیسی انتخاب کنید", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "لطفا نرم افزار text to speech را انتخاب کنید", Toast.LENGTH_SHORT)
                .show()
        }
    }

    fun Speakout(text: String) {
        text_to_speech?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }





}