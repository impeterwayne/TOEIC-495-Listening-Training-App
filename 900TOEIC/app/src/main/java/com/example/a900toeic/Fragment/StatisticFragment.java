package com.example.a900toeic.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;
import com.example.a900toeic.R;
import java.util.ArrayList;
import java.util.List;


public class StatisticFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_statistic, container, false);
        loadTheChart(view);
        return view;
    }

    private void loadTheChart(View view) {
        AnyChartView anyChartView = view.findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(view.findViewById(R.id.progress_bar));

        Cartesian cartesian = AnyChart.line();
        cartesian.background().fill("#EFEFEF");

        cartesian.animation(true);
        cartesian.padding(10d, 20d, 5d, 20d);

        cartesian.crosshair().enabled(true);
        cartesian.crosshair()
                .yLabel(true)
                .yStroke((Stroke) null, null, null, (String) null, (String) null);

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);

        cartesian.title("Training Progress Chart");

        cartesian.yAxis(0).title("Number of practiced questions");
        cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);
        List<DataEntry> seriesData = new ArrayList<>();
        seriesData.add(new CustomDataEntry("20/02",2,4,0,1));
        seriesData.add(new CustomDataEntry("21/02",3,2,1,2));
        seriesData.add(new CustomDataEntry("22/02",5,4,2,1));
        seriesData.add(new CustomDataEntry("23/02",1,8,3,4));
        seriesData.add(new CustomDataEntry("24/02",0,6,4,5));
        seriesData.add(new CustomDataEntry("25/02",5,5,5,7));
        seriesData.add(new CustomDataEntry("26/02",9,1,5,9));
        seriesData.add(new CustomDataEntry("27/02",12,9,8,1));
        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2Mapping = set.mapAs("{ x: 'x', value: 'value2' }");
        Mapping series3Mapping = set.mapAs("{ x: 'x', value: 'value3' }");
        Mapping series4Mapping = set.mapAs("{ x: 'x', value: 'value4' }");
        Line series1 = cartesian.line(series1Mapping);
        series1.name("Part One");
        series1.hovered().markers().enabled(true);
        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        Line series2 = cartesian.line(series2Mapping);
        series2.name("Part Two");
        series2.hovered().markers().enabled(true);
        series2.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series2.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        Line series3 = cartesian.line(series3Mapping);
        series3.name("Part Three");
        series3.hovered().markers().enabled(true);
        series3.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series3.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);
        Line series4 = cartesian.line(series4Mapping);
        series4.name("Part Four");
        series4.hovered().markers().enabled(true);
        series4.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series4.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);
        cartesian.legend().enabled(true);
        cartesian.legend().fontSize(13d);
        cartesian.legend().padding(0d, 0d, 10d, 0d);
        anyChartView.setChart(cartesian);
    }
    private class CustomDataEntry extends ValueDataEntry {

        CustomDataEntry(String x, Number part1, Number part2, Number part3, Number part4) {
            super(x, part1);
            setValue("value2", part2);
            setValue("value3", part3);
            setValue("value4", part4);
        }

    }
}