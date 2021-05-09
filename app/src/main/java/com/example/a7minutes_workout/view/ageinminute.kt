package com.example.a7minutes_workout.view

import android.R.attr.typeface
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.a7minutes_workout.R
import com.example.a7minutes_workout.databinding.ActivityAgeinminuteBinding
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog
import ir.hamsaa.persiandatepicker.api.PersianPickerDate
import ir.hamsaa.persiandatepicker.api.PersianPickerListener
import ir.hamsaa.persiandatepicker.util.PersianCalendarUtils
import org.jetbrains.annotations.NotNull
import java.text.SimpleDateFormat
import java.util.*


class ageinminute : AppCompatActivity(), View.OnClickListener {
    lateinit var ageinminute_bind:ActivityAgeinminuteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ageinminute_bind=DataBindingUtil.setContentView(this, R.layout.activity_ageinminute)
        setSupportActionBar(ageinminute_bind.ageToolbar)
        var actionbar_history = supportActionBar
        if (actionbar_history != null) {
            actionbar_history.setDisplayHomeAsUpEnabled(true)
            actionbar_history.title="سن"

        }
       ageinminute_bind.ageToolbar.setNavigationIcon(R.drawable.ic_back)
        ageinminute_bind.ageToolbar.setNavigationOnClickListener {
            onBackPressed()
            finish()
        }
        seton_click()
    }

    private fun seton_click() {
        ageinminute_bind.ageinDatepicker.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.agein_datepicker -> {
                var picker = PersianDatePickerDialog(this)
                    .setPositiveButtonString("باشه")
                    .setNegativeButton("بیخیال")
                    .setTodayButton("امروز")
                    .setTodayButtonVisible(true)
                    .setMinYear(1300)
                    .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
                    .setInitDate(1370, 12, 16)
                    .setActionTextColor(Color.GRAY)
                    .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
                    .setShowInBottomSheet(true)
                    .setListener(object : PersianPickerListener {
                        override fun onDateSelected(persianPickerDate: PersianPickerDate) {
                            Log.d(
                                "TAG",
                                "onDateSelected: " + persianPickerDate.getTimestamp()
                            ) //675930448000
                            Log.d(
                                "TAG",
                                "onDateSelected: " + persianPickerDate.getGregorianDate()
                            ) //Mon Jun 03 10:57:28 GMT+04:30 1991
                            Log.d(
                                "TAG",
                                "onDateSelected: " + persianPickerDate.getPersianLongDate()
                            ) // دوشنبه  13  خرداد  1370
                            Log.d(
                               " TAG",
                                "onDateSelected: " + persianPickerDate.getPersianMonthName()
                            ) //خرداد
                            Log.d(
                               " TAG",
                                "onDateSelected: " + PersianCalendarUtils.isPersianLeapYear(
                                    persianPickerDate.getPersianYear()
                                )
                            ) //true
                            Toast.makeText(
                               this@ageinminute,
                                persianPickerDate.getPersianYear()
                                    .toString() + "/" + persianPickerDate.getPersianMonth() + "/" + persianPickerDate.getPersianDay(),
                                Toast.LENGTH_SHORT
                            ).show()

                            var selected_data=persianPickerDate.getPersianLongDate()
                            ageinminute_bind.AgeDateSelectedTv.text=selected_data

                            var datePickerDialo = persianPickerDate.getGregorianDate().time/60000
                            var simpleDateFormat=SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)

                            var currentday =System.currentTimeMillis()
                            var sdf_format=simpleDateFormat.format(currentday)
                            var sdf_parse=simpleDateFormat.parse(sdf_format)
                            var sdf_time_cureentday=(sdf_parse?.time?.div(60000))

                            if (sdf_time_cureentday != null) {
                                ageinminute_bind.ageCalculate.text=((sdf_time_cureentday -datePickerDialo)+130560).toString()+ "دقیقه"
                            }



                        }

                        override fun onDismissed() {}
                    })
                picker.show()

            }
        }
    }

}