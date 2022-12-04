package com.example.moodmonitoringapp.fragments.stats

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moodmonitoringapp.R
import com.example.moodmonitoringapp.adapter.MoodRecyclerAdapter
import com.example.moodmonitoringapp.adapter.PostRecyclerAdapter
import com.example.moodmonitoringapp.data.Goals
import com.example.moodmonitoringapp.data.Moods
import com.example.moodmonitoringapp.data.Posts
import com.example.moodmonitoringapp.databinding.FragmentActiveGoalsBinding
import com.example.moodmonitoringapp.databinding.FragmentStatsBinding
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.text.FieldPosition
import java.text.Format
import java.text.ParsePosition
import java.util.*
import kotlin.collections.ArrayList

class StatsFragment : Fragment() {

    private lateinit var binding : FragmentStatsBinding
    private lateinit var db : DatabaseReference
    private lateinit var userRecyclerView : RecyclerView
    private lateinit var userArrayList : ArrayList<Moods>
    private lateinit var auth : FirebaseAuth
    private var userUId = "eEnewVtfJXfmjAMvkr5ESfJzjUo2"         // Hardcoded user ID, need to clear it when real work
    var tempUId = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //val view =inflater.inflate(R.layout.fragment_stats,container,false)
        binding = FragmentStatsBinding.inflate(inflater,container,false)

        auth = FirebaseAuth.getInstance()
        tempUId = auth.uid.toString()
        //userUId = tempUId              //Need to uncomment this in real work, because this is to get that signed in user id
        db = FirebaseDatabase.getInstance().getReference("Moods")

        setPieChart()


        return binding.root
    }

    private fun setPieChart() {

        //X values
        val xvalues = ArrayList<String>()
        xvalues.add("Very Good")
        xvalues.add("Good")
        xvalues.add("Normal")
        xvalues.add("Sad")
        xvalues.add("Very Sad")

        //Y values
        val yvalues = ArrayList<Float>()
        yvalues.add(22.45f)
        yvalues.add(45.0f)
        yvalues.add(100.90f)
        yvalues.add(37.15f)
        yvalues.add(78.25f)

        val piechartentry = ArrayList<Entry>()

        for((i, item) in yvalues.withIndex()){
            piechartentry.add(Entry(item,i))
        }


        //Colors
        val colors = ArrayList<Int>()
        colors.add(Color.CYAN)
        colors.add(Color.GRAY)
        colors.add(Color.YELLOW)
        colors.add(Color.BLUE)
        colors.add(Color.MAGENTA)


        //fill the chart
        val piedataset = PieDataSet(piechartentry,"Mood")

//        piedataset.color = resources.getColor(R.color.teal_200)
        piedataset.colors = colors

        piedataset.sliceSpace = 2f

        val data = PieData(xvalues,piedataset)
        binding.pieChart.data = data

        binding.pieChart.holeRadius = 3f
        binding.pieChart.setBackgroundColor(resources.getColor(R.color.white))

        binding.pieChart.setDescription("Mood records")

    }


}