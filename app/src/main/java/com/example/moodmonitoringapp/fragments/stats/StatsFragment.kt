package com.example.moodmonitoringapp.fragments.stats

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.view.isGone
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
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.charts.ScatterChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
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

//        binding.lineChart.isGone = true
        binding.barChart.isGone = true

        setPieChart()

        binding.pieChartBtn.setOnClickListener(){
//            binding.textView28.setText("Total Mood Couts")
            binding.textView28.setText("Total Mood Count - Pie Chart")
            setPieChart()
        }

        binding.barChartBtn.setOnClickListener(){
//            binding.textView28.setText("Mood Frequency")
            //setLineChart()
            binding.textView28.setText("Total Mood Count - Bar Chart")
            setBarChart()
        }

        binding.scatterChartBtn.setOnClickListener(){
            binding.textView28.setText("Total Mood Count - Scatter Chart")
            setScatterChart()
        }

        db.child(userUId).child("GoalCompleted").get().addOnSuccessListener {
            val goalCompletedCount = it.value

            binding.inputCompletedGoalCount.setText("" + goalCompletedCount.toString() )
        }


        return binding.root
    }

    private fun setPieChart() {

//        binding.lineChart.isGone = true
        binding.barChart.isGone =  true
        binding.scatterChart.isGone = true
        binding.pieChart.isGone = false


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

            val percentageVs = Math.round((verySad.toString().toDouble()/total)*100)
            binding.percentageVs.setText(percentageVs.toString() + "%")
            val percentageS = Math.round((sad.toString().toDouble()/total)*100)
            binding.percentageS.setText(percentageS.toString() + "%")
            val percentageN = Math.round((normal.toString().toDouble()/total)*100)
            binding.percentageN.setText(percentageN.toString() + "%")
            val percentageG = Math.round((happy.toString().toDouble()/total)*100)
            binding.percentageG.setText(percentageG.toString() + "%")
            val percentageVg = Math.round((veryHappy.toString().toDouble()/total)*100)
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

            piedataset.sliceSpace = 1f

            val data = PieData(xvalues,piedataset)
            binding.pieChart.data = data

            binding.pieChart.holeRadius = 1f
            binding.pieChart.setBackgroundColor(resources.getColor(R.color.verylightgrey))
            piedataset.setDrawValues(false)
            binding.pieChart.setDrawSliceText(false)
            binding.pieChart.isDrawHoleEnabled = false
            binding.pieChart.setDescription("")
            val legendPieChart : Legend = binding.pieChart.legend
            legendPieChart.position = Legend.LegendPosition.ABOVE_CHART_CENTER

//            binding.pieChart.setUsePercentValues(true)

//            binding.pieChart.setDescription("Mood records")

//            binding.pieChart.isGone = false
        }

    }

//    private fun setLineChart(){
//        binding.pieChart.isGone = true
//        binding.lineChart.isGone = false
//
//        db.child(userUId).child("TotalMoods").get().addOnSuccessListener {
//            val verySad = it.child("verySad").value
//            val sad = it.child("sad").value
//            val normal = it.child("normal").value
//            val happy = it.child("happy").value
//            val veryHappy = it.child("veryHappy").value
//
//
//            val xvalue = ArrayList<String>()
//            xvalue.add("Very Happy")
//            xvalue.add("Happy")
//            xvalue.add("Normal")
//            xvalue.add("Sad")
//            xvalue.add("Very Sad")
//
//            val yvalues = ArrayList<Int>()
//            yvalues.add(veryHappy.toString().toInt())
//            yvalues.add(happy.toString().toInt())
//            yvalues.add(normal.toString().toInt())
//            yvalues.add(sad.toString().toInt())
//            yvalues.add(verySad.toString().toInt())
//
//            val linechartentry = ArrayList<Entry>()
//
//            for ((i, item) in yvalues.withIndex()) {
//                linechartentry.add(Entry(item.toFloat(), i))
//            }
//
//            val linedataset = LineDataSet(linechartentry, "Mood Condition")
//            linedataset.color = resources.getColor(R.color.black)
//
//            val data = LineData(xvalue, linedataset)
//            binding.lineChart.data = data
//
//        }
//    }

    private fun setBarChart(){

        binding.pieChart.isGone = true
        binding.scatterChart.isGone = true
        binding.barChart.isGone =  false
//        binding.lineChart.isGone = true


        db.child(userUId).child("TotalMoods").get().addOnSuccessListener {
            val verySad = it.child("verySad").value
            val sad = it.child("sad").value
            val normal = it.child("normal").value
            val happy = it.child("happy").value
            val veryHappy = it.child("veryHappy").value

            val xvalues = ArrayList<String>()
            xvalues.add("Very Happy")
            xvalues.add("Happy")
            xvalues.add("Normal")
            xvalues.add("Sad")
            xvalues.add("Very Sad")

            val barentries = ArrayList<BarEntry>()
            barentries.add(BarEntry(veryHappy.toString().toFloat(),0))
            barentries.add(BarEntry(happy.toString().toFloat(),1))
            barentries.add(BarEntry(normal.toString().toFloat(),2))
            barentries.add(BarEntry(sad.toString().toFloat(),3))
            barentries.add(BarEntry(verySad.toString().toFloat(),4))

            val barDataset = BarDataSet(barentries,"Mood Condition")
            barDataset.setColors(ColorTemplate.JOYFUL_COLORS,255)
            val data = BarData(xvalues,barDataset)

            binding.barChart.setBackgroundColor(resources.getColor(R.color.verylightgrey))
            binding.barChart.xAxis.setDrawGridLines(false)
            barDataset.setDrawValues(false)
            binding.barChart.setDescription("")

            val legendBarChart : Legend = binding.barChart.legend
            legendBarChart.position = Legend.LegendPosition.ABOVE_CHART_CENTER

            val xaxis : XAxis = binding.barChart.xAxis
            xaxis.position = XAxis.XAxisPosition.BOTTOM

            binding.barChart.axisLeft.setAxisMinValue(0f)
            binding.barChart.axisRight.setAxisMinValue(0f)

            binding.barChart.data = data

        }
    }

    private fun setScatterChart(){

        binding.pieChart.isGone = true
        binding.barChart.isGone = true
        binding.scatterChart.isGone = false

        db.child(userUId).child("TotalMoods").get().addOnSuccessListener {
            val verySad = it.child("verySad").value
            val sad = it.child("sad").value
            val normal = it.child("normal").value
            val happy = it.child("happy").value
            val veryHappy = it.child("veryHappy").value

            val scatterEntry = ArrayList<Entry>()

            scatterEntry.add(Entry(veryHappy.toString().toFloat(),0))
            scatterEntry.add(Entry(happy.toString().toFloat(),1))
            scatterEntry.add(Entry(normal.toString().toFloat(),2))
            scatterEntry.add(Entry(sad.toString().toFloat(),3))
            scatterEntry.add(Entry(verySad.toString().toFloat(),4))

            val xvalues = ArrayList<String>()
            xvalues.add("Very Happy")
            xvalues.add("Happy")
            xvalues.add("Normal")
            xvalues.add("Sad")
            xvalues.add("Very Sad")

            val scatterDataSet = ScatterDataSet(scatterEntry,"Moods")
            scatterDataSet.setColors(ColorTemplate.JOYFUL_COLORS,255)
            scatterDataSet.scatterShape = ScatterChart.ScatterShape.CIRCLE

            binding.scatterChart.setBackgroundColor(resources.getColor(R.color.verylightgrey))

            binding.scatterChart.xAxis.setDrawGridLines(false)
            scatterDataSet.setDrawValues(false)
            binding.scatterChart.setDescription("")

            val legendScatterChart : Legend = binding.scatterChart.legend
            legendScatterChart.position = Legend.LegendPosition.ABOVE_CHART_CENTER

            val xaxis : XAxis = binding.scatterChart.xAxis
            xaxis.position = XAxis.XAxisPosition.BOTTOM

            binding.scatterChart.axisLeft.setAxisMinValue(0f)
            binding.scatterChart.axisRight.setAxisMinValue(0f)


            val scatterData = ScatterData(xvalues,scatterDataSet)
            binding.scatterChart.data = scatterData


        }

    }

}