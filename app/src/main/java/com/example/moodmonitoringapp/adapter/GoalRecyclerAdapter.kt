package com.example.moodmonitoringapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.moodmonitoringapp.R
import com.example.moodmonitoringapp.fragments.goals.GoalsDetailsFragment

class GoalRecyclerAdapter (private var titles: List<String>, private var scheduleDate: List<String>, private var images:List<Int>) :
RecyclerView.Adapter<GoalRecyclerAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val itemTitle: TextView = itemView.findViewById(R.id.goal_title)
        val itemDate : TextView = itemView.findViewById(R.id.shcedule_date)
        val itemPicture : ImageView = itemView.findViewById(R.id.goal_image)

        init {
            itemView.setOnClickListener{ v:View ->
//                val position: Int = adapterPosition
//                Toast.makeText(itemView.context, "You clicked on item# ${position+1}", Toast.LENGTH_SHORT).show()
                val activity=v!!.context as AppCompatActivity
                val fragmentDetails = GoalsDetailsFragment()
                activity.supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragmentDetails).addToBackStack(null).commit()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v  = LayoutInflater.from(parent.context).inflate(R.layout.goals_view,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = titles[position]
        holder.itemDate.text = scheduleDate[position]
        holder.itemPicture.setImageResource(images[position])



    }

    override fun getItemCount(): Int {
        return titles.size
    }
}