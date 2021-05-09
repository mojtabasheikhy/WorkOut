package com.example.a7minutes_workout.bindingadper

import android.widget.TextView
import androidx.databinding.BindingAdapter

class binding_adapter {
    companion object{
        @BindingAdapter("setnumber")
        @JvmStatic
        fun setnumberofexerciese(textView: TextView,number:Int){
            textView.text=number.toString()
        }
        @BindingAdapter("settextid")
        @JvmStatic
        fun sethistory(view: TextView,int: Int){
            view.text=(int + 1).toString()

        }
    }
}