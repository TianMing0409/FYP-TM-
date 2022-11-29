package com.example.moodmonitoringapp.fragments.stats

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moodmonitoringapp.R
import com.example.moodmonitoringapp.data.Goals
import com.example.moodmonitoringapp.databinding.FragmentActiveGoalsBinding
import com.example.moodmonitoringapp.databinding.FragmentStatsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import java.text.FieldPosition
import java.text.Format
import java.text.ParsePosition
import java.util.*

class StatsFragment : Fragment() {

    private lateinit var binding : FragmentStatsBinding
    private lateinit var db : DatabaseReference
    private lateinit var userRecyclerView : RecyclerView
    private lateinit var userArrayList : ArrayList<Goals>
    private lateinit var auth : FirebaseAuth
    private var userUId = "eEnewVtfJXfmjAMvkr5ESfJzjUo2"         // Hardcoded user ID, need to clear it when real work
    var tempUId = ""

    //lateinit var lineGraphView: GraphView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //val view =inflater.inflate(R.layout.fragment_stats,container,false)
        binding = FragmentStatsBinding.inflate(inflater,container,false)

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




        return binding.root
    }

}