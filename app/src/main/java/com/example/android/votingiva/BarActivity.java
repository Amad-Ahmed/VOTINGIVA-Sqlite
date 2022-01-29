package com.example.android.votingiva;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

public class BarActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);

//        BarChart barChart = findViewById(R.id.barChart);
//        ArrayList<BarEntry> entry = new ArrayList<>();
//
//        entry.add(new BarEntry(2014, 620));
//        entry.add(new BarEntry(2015, 520));
//        entry.add(new BarEntry(2016, 420));
//        entry.add(new BarEntry(2017, 320));
//
//        BarDataSet barDataSet = new BarDataSet(entry, "Entries");
//
//        barDataSet.setColors(Collections.singletonList(Color.BLUE));
//        barDataSet.setValueTextColor(Color.BLACK);
//        barDataSet.setValueTextSize(16f);
//
////        ArrayList<IBarDataSet> dataSets1 = new ArrayList<>();
////        dataSets1.add( (IBarDataSet) barDataSet);
//
//        BarData barData = new BarData((List<String>) barDataSet);
//
//        barChart.setFitsSystemWindows(true);
//        barChart.setData(barData);
//        barChart.animateY(2000);
//    }

//    public void BarData(List<IBarDataSet> dataSets) {
//        super(dataSets.);
//    }
}}
