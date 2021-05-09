package com.example.a7minutes_workout.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.RadioGroup
import androidx.databinding.DataBindingUtil
import com.example.a7minutes_workout.R
import com.example.a7minutes_workout.databinding.ActivityBmiBinding
import com.google.android.material.snackbar.Snackbar

import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.Exception

class BMI : AppCompatActivity(), View.OnClickListener, TextWatcher,
    RadioGroup.OnCheckedChangeListener {
    lateinit var bmi: ActivityBmiBinding
    var height: Float? = null
    var weight: Float? = null
    var bmiDescription: String? = null
    var bmiLabel: String? = null
    var flag = false
    var metric="metric"
    var defult=metric
    var us="us"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)
        bmi = DataBindingUtil.setContentView(this, R.layout.activity_bmi)
        setSupportActionBar(bmi.bimToolbar)
        bmi.bmiUsLayout.visibility = View.GONE
        var actionbar = supportActionBar
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.title = "اندازه گیری BMI"
        }
        bmi.bimToolbar.setNavigationIcon(R.drawable.ic_back)
        bmi.bimToolbar.setNavigationOnClickListener {
            onBackPressed()
            finish()
        }
        bmi.bimToolbar.setBackgroundColor(Color.parseColor("#FFFFFFFF"))


        bmi.bmiCalculate.setOnClickListener(this)
        bmi.bmiHeightEdt.addTextChangedListener(this)
        bmi.bmiWeightEdt.addTextChangedListener(this)
        bmi.bmiRadioGroup.setOnCheckedChangeListener(this)

    }

    fun check_data(): Boolean {
        if (TextUtils.isEmpty(bmi.bmiWeightEdt.text.toString().trim() { it <= ' ' })) {
            Snackbar.make(bmi.root, "لطفا وزن خود را وارد نمایید", Snackbar.LENGTH_SHORT).show()
            return false
        }
        if (TextUtils.isEmpty(bmi.bmiHeightEdt.text.toString().trim() { it <= ' ' })) {
            Snackbar.make(bmi.root, "لطفا قد خود را وارد نمایید", Snackbar.LENGTH_SHORT).show()

            return false
        } else return true
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.bmi_calculate -> {
                if (defult==metric) {
                    if (check_data()) {
                        calculate_bmi()
                    }
                }

                else {
                    if (check_data_us_bmi()){

                        bmi_us_calculate()
                    }
                }
            }
        }
    }

    private fun bmi_us_calculate() {
        try {
            var feet=bmi.bmiUsFeet.text.toString()
            var inch =bmi.bmiUsInch.text.toString()
            var weiht_us=bmi.bmiWeightEdt.text.toString().toFloat()
            var height_value=(feet.toFloat()+inch.toFloat())*12
            Log.e("s",height_value.toString())
            var bmi_resualt_us=703*(weiht_us/(height_value*height_value))
            var animation = AnimationUtils.loadAnimation(this, R.anim.item_fadeout_anim)
            bmi.bmiIvMain.animation = animation
            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {
                }

                override fun onAnimationEnd(animation: Animation?) {
                    bmi.bmiIvMain.visibility = View.GONE
                }

                override fun onAnimationRepeat(animation: Animation?) {
                }
            })

            animation.start()
            display_information_bmi(bmi_resualt_us)
        }catch (e:Exception){
             e.printStackTrace()
        }

    }

    private fun calculate_bmi() {
        try {
            height = bmi.bmiHeightEdt.text.toString().trim().toFloat() / 100
            weight = bmi.bmiWeightEdt.text.toString().trim().toFloat()
            var result = weight!! / (height!! * height!!)

            var animation = AnimationUtils.loadAnimation(this, R.anim.fade_out)
            bmi.bmiIvMain.animation = animation
            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {
                }

                override fun onAnimationEnd(animation: Animation?) {
                    bmi.bmiIvMain.visibility = View.GONE
                }

                override fun onAnimationRepeat(animation: Animation?) {
                }
            })

            animation.start()
            display_information_bmi(result)
        } catch (e: Exception) {
            Snackbar.make(bmi.root, "لطفا داده عددی وارد نمایید", Snackbar.LENGTH_SHORT).show()

        }

    }

    private fun display_information_bmi(result: Float) {
        if (result.compareTo(15f) <= 0) {
            bmiLabel = " کمبود شدید وزن !!"
            bmiDescription = "بهتر است به سلامتی خود اهمیت داده و وزن خود را افزایش دهید!!"
            bmi.modeIv.setImageResource(R.drawable.ic_thin)
        } else if (result.compareTo(15f) > 0 && result.compareTo(16f) <= 0
        ) {
            bmiLabel = "کمبود وزن !!t"
            bmiDescription = "بهتر است به سلامتی خود اهمیت داده و وزن خود را افزایش دهید!!"
            bmi.modeIv.setImageResource(R.drawable.ic_thin)
        } else if (result.compareTo(16f) > 0 && result.compareTo(18.5f) <= 0
        ) {
            bmiLabel = "کمی کمبود وزن"
            bmiDescription = "بهتر است به سلامتی خود اهمیت داده و وزن خود را افزایش دهید"
            bmi.modeIv.setImageResource(R.drawable.ic_thin)
        } else if (result.compareTo(18.5f) > 0 && result.compareTo(25f) <= 0
        ) {
            bmiLabel = "وضعیت عادی"
            bmiDescription = "تبریک !! وزن شما ایده عال است"
            bmi.modeIv.setImageResource(R.drawable.ic_thin)
        } else if (java.lang.Float.compare(result, 25f) > 0 && java.lang.Float.compare(
                result,
                30f
            ) <= 0
        ) {
            bmiLabel = "اضافه وزن"
            bmiDescription = "شما اضافه وزن شدید دارید سریع تر وزن خود را کاهش دهید"
            bmi.modeIv.setImageResource(R.drawable.ic_fat)
        } else if (result.compareTo(30f) > 0 && result.compareTo(35f) <= 0
        ) {
            bmiLabel = "چاقی درجه2"
            bmiDescription = "شما اضافه وزن شدید دارید سریع تر وزن خود را کاهش دهید"
            bmi.modeIv.setImageResource(R.drawable.ic_fat)

        } else if (result.compareTo(35f) > 0 && result.compareTo(40f) <= 0
        ) {
            bmiLabel = "چاقی درجه 3"
            bmiDescription = "شما اضافه وزن شدید دارید سریع تر وزن خود را کاهش دهید"
            bmi.modeIv.setImageResource(R.drawable.ic_fat)

        } else {
            bmiLabel = "چاقی شدید"
            bmiDescription = "شما اضافه وزن شدید دارید سریع تر وزن خود را کاهش دهید"
            bmi.modeIv.setImageResource(R.drawable.ic_fat)

        }
        bmi.bmiDescTv.text = bmiDescription
        bmi.textView9.text = bmiLabel
        var round_resualt = BigDecimal(result.toDouble()).setScale(2, RoundingMode.HALF_DOWN).toString()
        bmi.bmiResualtNumber.text = round_resualt
    }


    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(s: Editable?) {
        if (TextUtils.isEmpty(
                bmi.bmiWeightEdt.text.toString().trim() { it <= ' ' }) && TextUtils.isEmpty(
                bmi.bmiHeightEdt.text.toString().trim() { it <= ' ' })
        ) {
            var anim_in = AnimationUtils.loadAnimation(this, R.anim.fade_in)
            bmi.bmiIvMain.animation = anim_in
            anim_in.start()
            anim_in.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {
                    bmi.bmiIvMain.visibility = View.VISIBLE
                }

                override fun onAnimationEnd(animation: Animation?) {}
                override fun onAnimationRepeat(animation: Animation?) {}
            })


        }
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when (checkedId) {
            R.id.bmi_rd_metric -> {
                bmi.bmiHeightEdt.visibility = View.VISIBLE
                bmi.bmiUsLayout.visibility = View.GONE
                bmi.bmiUsFeet.text?.clear()
                bmi.bmiUsInch.text?.clear()
                bmi.textInputLayout2.hint="وزن ( به کیلوگرم )"


                 defult=metric
            }
            R.id.bmi_rd_us -> {
                defult=us
                bmi.bmiHeightEdt.text?.clear()
                bmi.bmiWeightEdt.text.clear()
                bmi.bmiHeightEdt.visibility = View.GONE
                bmi.bmiUsLayout.visibility = View.VISIBLE
                bmi.textInputLayout2.hint="وزن ( به ال بی اس )"
            }
        }
    }

    fun check_data_us_bmi():Boolean{
        when {
            TextUtils.isEmpty(bmi.bmiUsFeet.text.toString().trim() { it <= ' ' }) -> {
                Snackbar.make(bmi.root, "لطفا اطلاعات قدی را وارد نمایید", Snackbar.LENGTH_SHORT).show()
                return false
            }
            TextUtils.isEmpty(bmi.bmiUsInch.text.toString().trim() { it <= ' ' }) -> {
                Snackbar.make(bmi.root, "لطفا اطلاعات قدی را وارد نمایید", Snackbar.LENGTH_SHORT).show()
                return false
            }
            TextUtils.isEmpty(bmi.bmiWeightEdt.text.toString().trim() { it <= ' ' }) -> {
                Snackbar.make(bmi.root, "لطفا اطلاعات قدی را وارد نمایید", Snackbar.LENGTH_SHORT).show()
                return false
            }
            else -> return true
        }
    }
}