package com.example.a900toeic.LocalData;

import com.anychart.chart.common.dataentry.ValueDataEntry;

public class StatisticDataEntry extends ValueDataEntry {
    public StatisticDataEntry(String x, Number part1, Number part2, Number part3, Number part4) {
        super(x, part1);
        setValue("value2", part2);
        setValue("value3", part3);
        setValue("value4", part4);
    }
}
