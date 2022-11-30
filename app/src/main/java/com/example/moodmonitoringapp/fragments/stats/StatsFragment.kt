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

    //lateinit var lineGraphView: GraphView
//    lateinit var barList : ArrayList<BarEntry>
//
//    lateinit var barDataSet : BarDataSet
//    lateinit var barData : BarData

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

        userRecyclerView = binding.moodRecyclerView
        userRecyclerView.layoutManager = LinearLayoutManager(context)

        userArrayList = arrayListOf<Moods>()

        getPostsData()

//        val domainLabels = arrayOf<Number>(1,2,3,6,7,8,9,10,13,14)
//        val series1Number = arrayOf<Number>(1,4,8,12,16,32,26,29,10,13)
//
//        val series1 : XYSeries = SimpleXYSeries(Arrays.asList(* series1Number),SimpleXYSeries.ArrayFormat.Y_VALS_ONLY
//            ,"Seris1")
//
//        val series1Format = LineAndPointFormatter(Color.BLUE ,Color.BLACK,null,null)
//
//        binding.lineGraph.addSeries(series1,series1Format)
//
//        binding.lineGraph.graph.getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).format = object : Format(){
//            override fun format(obj: Any?, toAppendTo: StringBuffer?, pos: FieldPosition?): StringBuffer {
//                val i = Math.round((obj as Number).toFloat())
//                return toAppendTo!!.append(domainLabels[i])
//            }
//
//            override fun parseObject(source: String?, pos: ParsePosition?): Any? {
//                return null
//            }
//
//        }

//        barList = ArrayList()
//        barList.add(BarEntry(1f,500f))
//        barList.add(BarEntry(2f,100f))
//        barList.add(BarEntry(3f,300f))
//        barList.add(BarEntry(4f,500f))
//        barList.add(BarEntry(5f,800f))
//        barList.add(BarEntry(6f,200f))
//        barList.add(BarEntry(7f,900f))
//        barDataSet = BarDataSet(barList,"Mood")
//        barData = BarData(barDataSet)
//        binding.moodBarChart.data = barData
//        barDataSet.setColors(ColorTemplate.JOYFUL_COLORS,250)
//        barDataSet.valueTextColor = Color.BLACK
//        barDataSet.valueTextSize = 15f

        setBarChartValues()



        return binding.root
    }

    private fun getPostsData(){

        val getData = db.child(userUId)

        getData.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.exists()){
                    for(moodSnapshot in snapshot.children){
                        val moods = moodSnapshot.getValue(Moods::class.java)
                        userArrayList.add(moods!!)
                    }
                    userRecyclerView.adapter = MoodRecyclerAdapter(userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    fun setBarChartValues(){

        //x axis values
        val xValues = ArrayList<String>()
        xValues.add("Monday")
        xValues.add("Tuesday")
        xValues.add("Wednesday")
        xValues.add("Thursday")
        xValues.add("Friday")
        xValues.add("Saturday")
        xValues.add("Sunday")

        //y axis values or bar data

        //bar entries
        val barentries = ArrayList<BarEntry>()

        barentries.add(BarEntry(1.0f,0))
        barentries.add(BarEntry(2.0f,1))
        barentries.add(BarEntry(3.0f,2))
        barentries.add(BarEntry(4.0f,3))
        barentries.add(BarEntry(5.0f,4))
        barentries.add(BarEntry(6.0f,5))
        barentries.add(BarEntry(7.0f,6))

        //bardata set
        val bardataset = BarDataSet(barentries,"Mood")

        //make a bar data
        val data  = BarData(xValues,bardataset)

        binding.moodBarChart.data = data
        binding.moodBarChart.setBackgroundColor(resources.getColor(R.color.white))





    }


}