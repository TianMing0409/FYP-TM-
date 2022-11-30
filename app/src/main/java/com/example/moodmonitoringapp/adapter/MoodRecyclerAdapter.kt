package com.example.moodmonitoringapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moodmonitoringapp.R
import com.example.moodmonitoringapp.data.Moods

class MoodRecyclerAdapter(private val moods: ArrayList<Moods>) :
    RecyclerView.Adapter<MoodRecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val moodDate : TextView = itemView.findViewById(R.id.dateRecord)
        val mood : TextView = itemView.findViewById(R.id.moodRecord)

        init {
            itemView.setOnClickListener { }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoodRecyclerAdapter.ViewHolder {
        val v  = LayoutInflater.from(parent.context).inflate(R.layout.mood_records_view,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: MoodRecyclerAdapter.ViewHolder, position: Int) {
        val currentItem = moods[position]
        holder.mood.text = currentItem.moodRecord
        holder.moodDate.text = currentItem.dateRecord

    }

    override fun getItemCount(): Int {
        return moods.size
    }

}