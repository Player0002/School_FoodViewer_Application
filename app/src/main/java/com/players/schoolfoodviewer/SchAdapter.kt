package com.players.schoolfoodviewer

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.players.schoolfoodviewer.data.search.SearchSch2

class SchAdapter (val context : Context, val data : Array<SearchSch2>) : RecyclerView.Adapter<SchAdapter.Holder>() {
    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(str: SearchSch2, context: Context) {
            itemView.findViewById<TextView>(R.id.sch_name).text  = (str.name)
            itemView.findViewById<TextView>(R.id.sch_add).text = (str.address)
            itemView.findViewById<ImageView>(R.id.imageView).setImageResource(R.mipmap.ic_launcher)
            itemView.setOnClickListener{
                val str : String = itemView.findViewById<TextView>(R.id.sch_name).text.toString()
                val str2 : String = itemView.findViewById<TextView>(R.id.sch_add).text.toString()
                if(str == "학교명을 찾을 수 없습니다.") Toast.makeText(itemView.context, "학교명을 확인해주세요.", Toast.LENGTH_LONG).show()
                else{
                    if(str2.isEmpty()) Toast.makeText(itemView.context, "학교 주소를 찾을수가 없습니다.", Toast.LENGTH_LONG).show()
                    else {
                        App.prefs.text = str
                        App.prefs.address = str2
                        val intent = Intent(itemView.context, FoodViewer::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        itemView.context.startActivity(intent)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.school_layout, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(data[position], context)
    }
}