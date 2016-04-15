package edu.westga.workoutpal.View;


import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import edu.westga.workoutpal.Model.MyDBHandler;
import edu.westga.workoutpal.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatsFragment extends Fragment {


    public StatsFragment() {
        // Required empty public constructor
    }

    private PieChart mChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_stats, container, false);
        mChart = (PieChart) v.findViewById(R.id.pieChart);
        mChart.setDescription("");

        mChart.setCenterText(generateCenterText());
        mChart.setCenterTextSize(10f);
        mChart.setHoleColor(Color.GRAY);

        // radius of the center hole in percent of maximum radius
        mChart.setHoleRadius(45f);
        mChart.setTransparentCircleRadius(50f);

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);

        mChart.setData(generatePieData());

        return v;
    }

    private PieData generatePieData() {

        ArrayList<Entry> entries = new ArrayList<Entry>();
        ArrayList<String> xVals = new ArrayList<String>();
        ArrayList<String> statData = MainActivity.handler.getStats();

        int armsCount = Collections.frequency(statData, MainActivity.ARMS);
        int legsCount = Collections.frequency(statData, MainActivity.LEGS);
        int absCount = Collections.frequency(statData, MainActivity.ABS);
        int chestCount = Collections.frequency(statData, MainActivity.CHEST);

        if(armsCount > 0) {
            xVals.add("Arms");
            entries.add(new Entry(armsCount, 0));
        }
        if(legsCount > 0) {
            xVals.add("Legs");
            entries.add(new Entry(legsCount, 0));
        }

        if (absCount > 0) {
            xVals.add("Abs");
            entries.add(new Entry(absCount, 0));
        }
        if (chestCount > 0) {
            xVals.add("Chest");
            entries.add(new Entry(chestCount, 0));
        }

        PieDataSet ds1 = new PieDataSet(entries, "");
        ds1.setColors(ColorTemplate.VORDIPLOM_COLORS);
        ds1.setSliceSpace(2f);
        ds1.setValueTextColor(Color.BLACK);
        ds1.setValueTextSize(12f);

        PieData d = new PieData(xVals, ds1);

        return d;
    }

    private SpannableString generateCenterText() {
        SpannableString s = new SpannableString("Monthly Workout Counts");
        s.setSpan(new RelativeSizeSpan(1.6f), 0, 8, 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.VORDIPLOM_COLORS[0]), 0, 8, 0);
        s.setSpan(new RelativeSizeSpan(1.6f), 8, 15, 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.VORDIPLOM_COLORS[1]), 8, 15, 0);
        s.setSpan(new RelativeSizeSpan(1.6f), 15, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), 15, s.length(), 0);
        return s;
    }
}
