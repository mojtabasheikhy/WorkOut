package com.example.a7minutes_workout.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minutes_workout.R
import com.example.a7minutes_workout.adapter.Historyadapter
import com.example.a7minutes_workout.database.MySqliteOpenHelper
import com.example.a7minutes_workout.databinding.ActivityHistoryBinding

class History : AppCompatActivity() {
    lateinit var history_binding:ActivityHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        history_binding=DataBindingUtil.setContentView(this, R.layout.activity_history)

        setSupportActionBar(history_binding.historyToolbar)
        var actionbar_history = supportActionBar
        if (actionbar_history != null) {
            actionbar_history.setDisplayHomeAsUpEnabled(true)
            actionbar_history.title="تاریخچه"

        }
        history_binding.historyToolbar.setNavigationIcon(R.drawable.ic_back)
        history_binding.historyToolbar.setNavigationOnClickListener {
            onBackPressed()
            finish()
        }

        get_all_data()
    }


    fun get_all_data() {
        var handler_read_database = MySqliteOpenHelper(this, null)
        var list_date_compelete_day = handler_read_database.read_all_date_completed_history()
        if (list_date_compelete_day.size > 0) {
            history_binding.historyNodataTv.visibility = View.GONE
            history_binding.historyRecyclerView.visibility=View.VISIBLE
            history_binding.historyTitleRecycler.visibility=View.VISIBLE
            history_binding.historyNodataAnim.visibility=View.GONE

            var HistoryAdpter = Historyadapter(list_date_compelete_day)
            history_binding.historyRecyclerView.apply {
                layoutManager=LinearLayoutManager(this@History,RecyclerView.VERTICAL,false)
                adapter=HistoryAdpter
            }
        } else{
            history_binding.historyNodataAnim.visibility=View.VISIBLE
            history_binding.historyRecyclerView.visibility=View.GONE
            history_binding.historyNodataTv.visibility=View.VISIBLE
            history_binding.historyTitleRecycler.visibility=View.GONE
        }
    }
}