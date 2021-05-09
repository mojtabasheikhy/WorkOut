package com.example.a7minutes_workout.database

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minutes_workout.model.Exercisemodel
import com.example.a7minutes_workout.R
import com.example.a7minutes_workout.databinding.ItemMainRecyclerviewBinding

class Main_adapter(var list_exercise:ArrayList<Exercisemodel>, var context: Context):RecyclerView.Adapter<Main_adapter.view_holder>() {
    inner class view_holder(var item_view_bind:ItemMainRecyclerviewBinding):RecyclerView.ViewHolder(item_view_bind.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view_holder {
        var view = DataBindingUtil.inflate<ItemMainRecyclerviewBinding>(LayoutInflater.from(parent.context),
            R.layout.item_main_recyclerview,parent,false)
        return view_holder(view)
      }

    override fun onBindViewHolder(holder: view_holder, position: Int) {
           holder.item_view_bind.exercise = list_exercise[position]

           if (list_exercise[position].isSelected == true ){
               holder.item_view_bind.itemTvRecycler.setTextColor(ContextCompat.getColor(context,
                   R.color.black
               ))
               holder.item_view_bind.itemTvRecycler.background=ContextCompat.getDrawable(context,
                   R.drawable.item_background_selected_exercise
               )
           }
          else if(list_exercise[position].iscomplete == true ){
            holder.item_view_bind.itemTvRecycler.setTextColor(ContextCompat.getColor(context,
                R.color.white
            ))
            holder.item_view_bind.itemTvRecycler.background=ContextCompat.getDrawable(context,
                R.drawable.item_background_complete_exercise
            )
          }
        else{
               holder.item_view_bind.itemTvRecycler.setTextColor(ContextCompat.getColor(context,
                   R.color.black
               ))
               holder.item_view_bind.itemTvRecycler.background=ContextCompat.getDrawable(context,
                   R.drawable.item_background
               )
        }
        }

    override fun getItemCount(): Int =list_exercise.size
}