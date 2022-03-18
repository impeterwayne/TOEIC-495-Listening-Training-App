package com.example.a900toeic.Fragment;

import static com.example.a900toeic.Database.DBQuery.dataStatisticArr;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.a900toeic.Database.DBQuery;
import com.example.a900toeic.Model.DataStatistic;
import com.example.a900toeic.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class StatisticFragment extends Fragment {
    private LineChart mChart;
    private String[] dateArray;
    private int[] colorClassArray = {Color.GREEN,Color.CYAN,Color.YELLOW,Color.BLUE};
    private String[] legendName ={"Part One","Part Two","Part Three","Part Four"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_statistic, container, false);
        addControls(view);
        return view;
    }

    private void addControls(View view) {

        mChart = view.findViewById(R.id.mChart);
        DBQuery.loadDataStatistic(new DBQuery.iDataStatisticCallback() {
            @Override
            public void onCallBack(ArrayList<DataStatistic> res) {
                dataStatisticArr = res;
                dateArray = new String[dataStatisticArr.size()];
                Log.d("DataDateSize", dateArray.length+"");
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM");
                for(int i = 0; i< dataStatisticArr.size(); i++)
                {
                    dateArray[i] = sdf.format(dataStatisticArr.get(i).getDate());
                    Log.d("Date" + i, dateArray[i]);
                }
                setUpTheChart();
            }
        });

    }
    private void setUpTheChart() {
        mChart.setNoDataText("No data");
        mChart.getDescription().setText("");
        YAxis yAxisRight = mChart.getAxisRight();
        YAxis yAxisLeft = mChart.getAxisLeft();
        XAxis xAxis = mChart.getXAxis();
        // remove axis
        yAxisRight.setEnabled(false);
        yAxisRight.setDrawGridLines(false);
        yAxisLeft.setDrawGridLines(false);
        xAxis.setDrawGridLines(false);
        //custom axis
        yAxisLeft.setAxisMinimum(0);
        yAxisLeft.setAxisLineWidth(5);
        yAxisLeft.setAxisLineColor(Color.BLACK);
        xAxis.setAxisLineWidth(5);
        xAxis.setAxisLineColor(Color.BLACK);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(dateArray));
        // Legend
        Legend legend = mChart.getLegend();
        legend.setEnabled(true);
        legend.setTextSize(18);
        legend.setFormSize(15);
        legend.setXEntrySpace(10);
        legend.setFormToTextSpace(1.5f);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setForm(Legend.LegendForm.CIRCLE);
        // Custom legend
        LegendEntry[] legendEntries = new LegendEntry[4];
        for(int i= 0  ; i<legendEntries.length;i++)
        {
            LegendEntry entry = new LegendEntry();
            entry.formColor = colorClassArray[i];
            entry.label = legendName[i];
            legendEntries[i] = entry;
        }
        legend.setCustom(legendEntries);
        // data line
            //part1
        LineDataSet lineDataSet1 = new LineDataSet(loadDataEntryPart1(), "Part One");
        lineDataSet1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet1.setCubicIntensity(0.0f);
        lineDataSet1.setLineWidth(4);
        lineDataSet1.setColor(Color.GREEN);
        lineDataSet1.setValueTextSize(10);
        lineDataSet1.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf((int) value);
            }
        });
            //part2
        LineDataSet lineDataSet2 = new LineDataSet(loadDataEntryPart2(), "Part Two");
        lineDataSet2.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet2.setColor(Color.CYAN);
        lineDataSet2.setCubicIntensity(0.0f);
        lineDataSet2.setLineWidth(4);
        lineDataSet2.setValueTextSize(10);
        lineDataSet2.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf((int) value);
            }
        });
        //part3
        LineDataSet lineDataSet3 = new LineDataSet(loadDataEntryPart3(), "Part Three");
        lineDataSet3.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet3.setColor(Color.YELLOW);
        lineDataSet3.setCubicIntensity(0.0f);
        lineDataSet3.setLineWidth(4);
        lineDataSet3.setValueTextSize(10);
        lineDataSet3.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf((int) value);
            }
        });
        LineDataSet lineDataSet4 = new LineDataSet(loadDataEntryPart4(), "Part Four");
        lineDataSet4.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet4.setColor(Color.BLUE);
        lineDataSet4.setCubicIntensity(0.0f);
        lineDataSet4.setLineWidth(4);
        lineDataSet4.setValueTextSize(10);
        lineDataSet4.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf((int) value);
            }
        });
        //set of line for chart
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet1);
        dataSets.add(lineDataSet2);
        dataSets.add(lineDataSet3);
        dataSets.add(lineDataSet4);

        LineData data = new LineData(dataSets);
        mChart.setData(data);
        mChart.invalidate();
    }
    private ArrayList<Entry> loadDataEntryPart1()
    {
        ArrayList<Entry> data = new ArrayList<Entry>();
        for(int i = 0; i< DBQuery.dataStatisticArr.size(); i++)
        {
            data.add(new Entry(i,DBQuery.dataStatisticArr.get(i).getNum_part1()));
        }
        return data;
    }
    private ArrayList<Entry> loadDataEntryPart2()
    {
        ArrayList<Entry> data = new ArrayList<Entry>();
        for(int i = 0; i< DBQuery.dataStatisticArr.size(); i++)
        {
            data.add(new Entry(i,DBQuery.dataStatisticArr.get(i).getNum_part2()));
        }
        return data;
    }
    private ArrayList<Entry> loadDataEntryPart3()
    {
        ArrayList<Entry> data = new ArrayList<Entry>();
        for(int i = 0; i< DBQuery.dataStatisticArr.size(); i++)
        {
            data.add(new Entry(i,DBQuery.dataStatisticArr.get(i).getNum_part3()));
        }
        return data;
    }
    private ArrayList<Entry> loadDataEntryPart4()
    {
        ArrayList<Entry> data = new ArrayList<Entry>();
        for(int i = 0; i< DBQuery.dataStatisticArr.size(); i++)
        {
            data.add(new Entry(i,DBQuery.dataStatisticArr.get(i).getNum_part4()));
        }
        return data;
    }
}