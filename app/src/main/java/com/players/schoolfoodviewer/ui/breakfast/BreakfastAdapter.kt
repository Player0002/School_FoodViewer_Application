package com.players.schoolfoodviewer.ui.breakfast

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.players.schoolfoodviewer.R

class BreakfastAdapter(val context : Context, val dataList : Array<String>) : RecyclerView.Adapter<BreakfastAdapter.Holder>(){
    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(str : String, context : Context){
            itemView.findViewById<TextView>(R.id.textView).setText(str)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.food_layout, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(dataList[position], context)
    }

}