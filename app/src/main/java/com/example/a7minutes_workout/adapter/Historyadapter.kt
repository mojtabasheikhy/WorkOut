package com.example.a7minutes_workout.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minutes_workout.model.Historydataclass
import com.example.a7minutes_workout.R
import com.example.a7minutes_workout.databinding.ItemHistoryBinding

class Historyadapter(var list_history:ArrayList<Historydataclass>):RecyclerView.Adapter<Historyadapter.viewHolder>() {
    inner class viewHolder(var itemHistoryBinding: ItemHistoryBinding):RecyclerView.ViewHolder(itemHistoryBinding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
         var view = DataBindingUtil.inflate<ItemHistoryBinding>(LayoutInflater.from(parent.context),
             R.layout.item_history,parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
       holder.itemHistoryBinding.history=list_history[position]
        if (position % 2 == 0 ){
            holder.itemHistoryBinding.root.setBackgroundColor(Color.parseColor("#BEBEBEBE"))
        }
        else {
            holder.itemHistoryBinding.root.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
        }
    }
    override fun getItemCount(): Int =list_history.size
}