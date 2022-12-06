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
        db = FirebaseDatabase.getInstance().getReference("Stats")

        setPieChart()


        return binding.root
    }

    private fun setPieChart() {

        db.child(userUId).child("TotalMoods").get().addOnSuccessListener {
            val verySad = it.child("verySad").value
            val sad = it.child("sad").value
            val normal = it.child("normal").value
            val happy = it.child("happy").value
            val veryHappy = it.child("veryHappy").value

            val total = verySad.toString().toInt() + sad.toString().toInt() + normal.toString().toInt() +
                    happy.toString().toInt() + veryHappy.toString().toInt()

            binding.countVs.text = verySad.toString()
            binding.countS.text = sad.toString()
            binding.countN.text = normal.toString()
            binding.countG.text = happy.toString()
            binding.countVg.text = veryHappy.toString()
            binding.inputTotal.text = total.toString()

            val percentageVs = Math.round((verySad.toString().toDouble()/total)*100)/100.0
            binding.percentageVs.setText(percentageVs.toString() + "%")
            val percentageS = Math.round((sad.toString().toDouble()/total)*100)/100.0
            binding.percentageS.setText(percentageS.toString() + "%")
            val percentageN = Math.round((normal.toString().toDouble()/total)*100)/100.0
            binding.percentageN.setText(percentageN.toString() + "%")
            val percentageG = Math.round((happy.toString().toDouble()/total)*100)/100.0
            binding.percentageG.setText(percentageG.toString() + "%")
            val percentageVg = Math.round((veryHappy.toString().toDouble()/total)*100)/100.0
            binding.percentageVg.setText(percentageVg.toString() + "%")

            //X values
            val xvalues = ArrayList<String>()
            xvalues.add("Very Good")
            xvalues.add("Good")
            xvalues.add("Normal")
            xvalues.add("Sad")
            xvalues.add("Very Sad")

            //Y values
            val yvalues = ArrayList<Int>()
            yvalues.add(veryHappy.toString().toInt())
            yvalues.add(happy.toString().toInt())
            yvalues.add(normal.toString().toInt())
            yvalues.add(sad.toString().toInt())
            yvalues.add(verySad.toString().toInt())



            val piechartentry = ArrayList<Entry>()

            for((i, item) in yvalues.withIndex()){
                piechartentry.add(Entry(item.toFloat(),i))
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


}