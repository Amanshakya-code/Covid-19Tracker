package com.example.covid_19tracker

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import com.example.covid_19tracker.notification.Constant.Companion.chnageList
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.activity_bar_graph.*

class barGraphActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bar_graph)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        val status = intent.getStringExtra("condition")
        if(status == "confirm"){
            titletxt.text = "Confirm cases Graph"
            division.text = "For getting approx. figure multiply each value with 10000"
            graphScrollView.setBackgroundColor(Color.parseColor("#ffebee"))
            val confirmArrayList = ArrayList<BarEntry>()
            confirmArrayList.add(BarEntry(1f, chnageList.get(0).confirmed!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(2f, chnageList.get(1).confirmed!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(3f, chnageList.get(2).confirmed!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(4f, chnageList.get(3).confirmed!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(5f, chnageList.get(4).confirmed!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(6f, chnageList.get(5).confirmed!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(7f, chnageList.get(7).confirmed!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(8f, chnageList.get(8).confirmed!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(9f, chnageList.get(9).confirmed!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(10f, chnageList.get(10).confirmed!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(11f, chnageList.get(11).confirmed!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(12f, chnageList.get(12).confirmed!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(13f, chnageList.get(13).confirmed!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(14f, chnageList.get(14).confirmed!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(15f, chnageList.get(15).confirmed!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(16f, chnageList.get(16).confirmed!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(17f, chnageList.get(19).confirmed!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(18f, chnageList.get(20).confirmed!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(19f, chnageList.get(25).confirmed!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(20f, chnageList.get(27).confirmed!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(21f, chnageList.get(28).confirmed!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(22f, chnageList.get(29).confirmed!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(23f, chnageList.get(34).confirmed!!.toFloat()/10000))

            val xAxisLabel = listOf<String>(
                chnageList.get(34).statecode.toString(),
                chnageList.get(29).statecode.toString(),
                chnageList.get(28).statecode.toString(),
                chnageList.get(27).statecode.toString(),
                chnageList.get(25).statecode.toString(),
                chnageList.get(20).statecode.toString(),
                chnageList.get(19).statecode.toString(),
                chnageList.get(16).statecode.toString(),
                chnageList.get(15).statecode.toString(),
                chnageList.get(14).statecode.toString(),
                chnageList.get(13).statecode.toString(),
                chnageList.get(12).statecode.toString(),
                chnageList.get(11).statecode.toString(),
                chnageList.get(10).statecode.toString(),
                chnageList.get(9).statecode.toString(),
                chnageList.get(8).statecode.toString(),
                chnageList.get(7).statecode.toString(),
                chnageList.get(5).statecode.toString(),
                chnageList.get(4).statecode.toString(),
                chnageList.get(3).statecode.toString(),
                chnageList.get(2).statecode.toString(),
                chnageList.get(1).statecode.toString(),
                chnageList.get(0).statecode.toString())
            val dataset = BarDataSet(confirmArrayList,"ConfirmReport")
            dataset.setColor(Color.parseColor("#D32F2F"))
            dataset.setValueTextColor(Color.parseColor("#D32F2F"))
            dataset.valueTextSize = 20f
            val bardata = BarData(dataset)
            barchart.setFitBars(true)
            barchart.xAxis.valueFormatter = IndexAxisValueFormatter(xAxisLabel)
            barchart.data = bardata
            barchart.description.text = "Bar report Demo"
            barchart.animateY(2000)


        }
        if(status == "recovered"){
            titletxt.text = "Recovered Cases Graph"
            division.text = "For getting approx. figure multiply each value with 10000"
            graphScrollView.setBackgroundColor(Color.parseColor("#f0f4c3"))
            val confirmArrayList = ArrayList<BarEntry>()
            confirmArrayList.add(BarEntry(1f, chnageList.get(0).recovered!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(2f, chnageList.get(1).recovered!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(3f, chnageList.get(2).recovered!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(4f, chnageList.get(3).recovered!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(5f, chnageList.get(4).recovered!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(6f, chnageList.get(5).recovered!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(7f, chnageList.get(7).recovered!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(8f, chnageList.get(8).recovered!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(9f, chnageList.get(9).recovered!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(10f, chnageList.get(10).recovered!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(11f, chnageList.get(11).recovered!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(12f, chnageList.get(12).recovered!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(13f, chnageList.get(13).recovered!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(14f, chnageList.get(14).recovered!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(15f, chnageList.get(15).recovered!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(16f, chnageList.get(16).recovered!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(17f, chnageList.get(19).recovered!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(18f, chnageList.get(20).recovered!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(19f, chnageList.get(25).recovered!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(20f, chnageList.get(27).recovered!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(21f, chnageList.get(28).recovered!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(22f, chnageList.get(29).recovered!!.toFloat()/10000))
            confirmArrayList.add(BarEntry(23f, chnageList.get(34).recovered!!.toFloat()/10000))

            val xAxisLabel = listOf<String>(
                chnageList.get(34).statecode.toString(),
                chnageList.get(29).statecode.toString(),
                chnageList.get(28).statecode.toString(),
                chnageList.get(27).statecode.toString(),
                chnageList.get(25).statecode.toString(),
                chnageList.get(20).statecode.toString(),
                chnageList.get(19).statecode.toString(),
                chnageList.get(16).statecode.toString(),
                chnageList.get(15).statecode.toString(),
                chnageList.get(14).statecode.toString(),
                chnageList.get(13).statecode.toString(),
                chnageList.get(12).statecode.toString(),
                chnageList.get(11).statecode.toString(),
                chnageList.get(10).statecode.toString(),
                chnageList.get(9).statecode.toString(),
                chnageList.get(8).statecode.toString(),
                chnageList.get(7).statecode.toString(),
                chnageList.get(5).statecode.toString(),
                chnageList.get(4).statecode.toString(),
                chnageList.get(3).statecode.toString(),
                chnageList.get(2).statecode.toString(),
                chnageList.get(1).statecode.toString(),
                chnageList.get(0).statecode.toString())
            val dataset = BarDataSet(confirmArrayList,"RecoveredReport")
            dataset.setColor(Color.parseColor("#388E3C"))
            dataset.setValueTextColor(Color.parseColor("#388E3C"))
            dataset.valueTextSize = 20f
            val bardata = BarData(dataset)
            barchart.setFitBars(true)
            barchart.xAxis.valueFormatter = IndexAxisValueFormatter(xAxisLabel)
            barchart.data = bardata
            barchart.description.text = "Bar report Demo"
            barchart.animateY(2000)
        }
        if(status == "active"){
            titletxt.text = "Active cases Graph"
            division.text = "For getting approx. figure multiply each value with 1000"
            graphScrollView.setBackgroundColor(Color.parseColor("#e0f7fa"))
            val confirmArrayList = ArrayList<BarEntry>()
            confirmArrayList.add(BarEntry(2f, chnageList.get(1).active!!.toFloat()/1000))
            confirmArrayList.add(BarEntry(1f, chnageList.get(0).active!!.toFloat()/1000))
            confirmArrayList.add(BarEntry(3f, chnageList.get(2).active!!.toFloat()/1000))
            confirmArrayList.add(BarEntry(4f, chnageList.get(3).active!!.toFloat()/1000))
            confirmArrayList.add(BarEntry(5f, chnageList.get(4).active!!.toFloat()/1000))
            confirmArrayList.add(BarEntry(6f, chnageList.get(5).active!!.toFloat()/1000))
            confirmArrayList.add(BarEntry(7f, chnageList.get(7).active!!.toFloat()/1000))
            confirmArrayList.add(BarEntry(8f, chnageList.get(8).active!!.toFloat()/1000))
            confirmArrayList.add(BarEntry(9f, chnageList.get(9).active!!.toFloat()/1000))
            confirmArrayList.add(BarEntry(10f, chnageList.get(10).active!!.toFloat()/1000))
            confirmArrayList.add(BarEntry(11f, chnageList.get(11).active!!.toFloat()/1000))
            confirmArrayList.add(BarEntry(12f, chnageList.get(12).active!!.toFloat()/1000))
            confirmArrayList.add(BarEntry(13f, chnageList.get(13).active!!.toFloat()/1000))
            confirmArrayList.add(BarEntry(14f, chnageList.get(14).active!!.toFloat()/1000))
            confirmArrayList.add(BarEntry(15f, chnageList.get(15).active!!.toFloat()/1000))
            confirmArrayList.add(BarEntry(16f, chnageList.get(16).active!!.toFloat()/1000))
            confirmArrayList.add(BarEntry(17f, chnageList.get(19).active!!.toFloat()/1000))
            confirmArrayList.add(BarEntry(18f, chnageList.get(20).active!!.toFloat()/1000))
            confirmArrayList.add(BarEntry(19f, chnageList.get(25).active!!.toFloat()/1000))
            confirmArrayList.add(BarEntry(20f, chnageList.get(27).active!!.toFloat()/1000))
            confirmArrayList.add(BarEntry(21f, chnageList.get(28).active!!.toFloat()/1000))
            confirmArrayList.add(BarEntry(22f, chnageList.get(29).active!!.toFloat()/1000))
            confirmArrayList.add(BarEntry(23f, chnageList.get(34).active!!.toFloat()/1000))

            val xAxisLabel = listOf<String>(
                chnageList.get(34).statecode.toString(),
                chnageList.get(29).statecode.toString(),
                chnageList.get(28).statecode.toString(),
                chnageList.get(27).statecode.toString(),
                chnageList.get(25).statecode.toString(),
                chnageList.get(20).statecode.toString(),
                chnageList.get(19).statecode.toString(),
                chnageList.get(16).statecode.toString(),
                chnageList.get(15).statecode.toString(),
                chnageList.get(14).statecode.toString(),
                chnageList.get(13).statecode.toString(),
                chnageList.get(12).statecode.toString(),
                chnageList.get(11).statecode.toString(),
                chnageList.get(10).statecode.toString(),
                chnageList.get(9).statecode.toString(),
                chnageList.get(8).statecode.toString(),
                chnageList.get(7).statecode.toString(),
                chnageList.get(5).statecode.toString(),
                chnageList.get(4).statecode.toString(),
                chnageList.get(3).statecode.toString(),
                chnageList.get(2).statecode.toString(),
                chnageList.get(1).statecode.toString(),
                chnageList.get(0).statecode.toString())
            val dataset = BarDataSet(confirmArrayList,"Active Report")
            dataset.setColors(Color.parseColor("#1976D2"))
            dataset.setValueTextColor(Color.parseColor("#1976D2"))
            dataset.valueTextSize = 20f
            val bardata = BarData(dataset)
            barchart.setFitBars(true)
            barchart.xAxis.valueFormatter = IndexAxisValueFormatter(xAxisLabel)
            barchart.data = bardata
            barchart.description.text = "Bar report Demo"
            barchart.animateY(2000)
        }
        if(status == "death") {
            titletxt.text = "Death cases Graph"
            division.text = "For getting approx. figure multiply each value with 100"
            graphScrollView.setBackgroundColor(Color.parseColor("#f3e5f5"))
            val confirmArrayList = ArrayList<BarEntry>()
            confirmArrayList.add(BarEntry(2f, chnageList.get(1).deaths!!.toFloat() / 100))
            confirmArrayList.add(BarEntry(1f, chnageList.get(0).deaths!!.toFloat() / 100))
            confirmArrayList.add(BarEntry(3f, chnageList.get(2).deaths!!.toFloat() / 100))
            confirmArrayList.add(BarEntry(4f, chnageList.get(3).deaths!!.toFloat() / 100))
            confirmArrayList.add(BarEntry(5f, chnageList.get(4).deaths!!.toFloat() / 100))
            confirmArrayList.add(BarEntry(6f, chnageList.get(5).deaths!!.toFloat() / 100))
            confirmArrayList.add(BarEntry(7f, chnageList.get(7).deaths!!.toFloat() / 100))
            confirmArrayList.add(BarEntry(8f, chnageList.get(8).deaths!!.toFloat() / 100))
            confirmArrayList.add(BarEntry(9f, chnageList.get(9).deaths!!.toFloat() / 100))
            confirmArrayList.add(BarEntry(10f, chnageList.get(10).deaths!!.toFloat() / 100))
            confirmArrayList.add(BarEntry(11f, chnageList.get(11).deaths!!.toFloat() / 100))
            confirmArrayList.add(BarEntry(12f, chnageList.get(12).deaths!!.toFloat() / 100))
            confirmArrayList.add(BarEntry(13f, chnageList.get(13).deaths!!.toFloat() / 100))
            confirmArrayList.add(BarEntry(14f, chnageList.get(14).deaths!!.toFloat() / 100))
            confirmArrayList.add(BarEntry(15f, chnageList.get(15).deaths!!.toFloat() / 100))
            confirmArrayList.add(BarEntry(16f, chnageList.get(16).deaths!!.toFloat() / 100))
            confirmArrayList.add(BarEntry(17f, chnageList.get(19).deaths!!.toFloat() / 100))
            confirmArrayList.add(BarEntry(18f, chnageList.get(20).deaths!!.toFloat() / 100))
            confirmArrayList.add(BarEntry(19f, chnageList.get(25).deaths!!.toFloat() / 100))
            confirmArrayList.add(BarEntry(20f, chnageList.get(27).deaths!!.toFloat() / 100))
            confirmArrayList.add(BarEntry(21f, chnageList.get(28).deaths!!.toFloat() / 100))
            confirmArrayList.add(BarEntry(22f, chnageList.get(29).deaths!!.toFloat() / 100))
            confirmArrayList.add(BarEntry(23f, chnageList.get(34).deaths!!.toFloat() / 100))

            val xAxisLabel = listOf<String>(
                chnageList.get(34).statecode.toString(),
                chnageList.get(29).statecode.toString(),
                chnageList.get(28).statecode.toString(),
                chnageList.get(27).statecode.toString(),
                chnageList.get(25).statecode.toString(),
                chnageList.get(20).statecode.toString(),
                chnageList.get(19).statecode.toString(),
                chnageList.get(16).statecode.toString(),
                chnageList.get(15).statecode.toString(),
                chnageList.get(14).statecode.toString(),
                chnageList.get(13).statecode.toString(),
                chnageList.get(12).statecode.toString(),
                chnageList.get(11).statecode.toString(),
                chnageList.get(10).statecode.toString(),
                chnageList.get(9).statecode.toString(),
                chnageList.get(8).statecode.toString(),
                chnageList.get(7).statecode.toString(),
                chnageList.get(5).statecode.toString(),
                chnageList.get(4).statecode.toString(),
                chnageList.get(3).statecode.toString(),
                chnageList.get(2).statecode.toString(),
                chnageList.get(1).statecode.toString(),
                chnageList.get(0).statecode.toString())
            val dataset = BarDataSet(confirmArrayList, "Active Report")
            dataset.setColor(Color.parseColor("#FF6200EE"))
            dataset.setValueTextColor(Color.parseColor("#FF6200EE"))
            dataset.valueTextSize = 20f
            val bardata = BarData(dataset)
            barchart.setFitBars(true)
            barchart.xAxis.valueFormatter = IndexAxisValueFormatter(xAxisLabel)
            barchart.data = bardata
            barchart.description.text = "Bar report Demo"
            barchart.animateY(2000)
        }
    }
}
