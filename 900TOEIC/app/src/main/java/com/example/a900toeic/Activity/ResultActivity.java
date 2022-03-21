package com.example.a900toeic.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.a900toeic.Adapter.ResultAdapter;
import com.example.a900toeic.LocalData.DataLocalManager;
import com.example.a900toeic.Model.Answer;
import com.example.a900toeic.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ResultActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView btn_home;
    private PieChart resultChart;
    private TickerView tickerView;
    private List<Answer> answerList;
    private RecyclerView rcv_result;
    private ResultAdapter resultAdapter;
    private long correctAnswers = 0, incorrectAnswers = 0, notDoneAnswers = 0, score = 0;
    private final int[] colorClassArray = new int[]{Color.RED, Color.GREEN, Color.GRAY};
    private Map<Long, String> keyMap;
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, RealTestActivity.class);
        intent.putExtra("listAnswer", (Serializable) answerList);
        startActivity(intent);


    }
    @Override
    protected void onDestroy() {
        getIntent().setAction("");
        DataLocalManager.clearAnswers();
        super.onDestroy();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        keyMap = (Map<Long, String>) getIntent().getSerializableExtra("keyMap");
        countAnswers();
        calculateScore();
        loadAnswerList();
        addControls();
        drawChart();
        displayListAnswers();
    }

    private void displayListAnswers() {
        resultAdapter = new ResultAdapter(ResultActivity.this, answerList);
        rcv_result.setLayoutManager(new LinearLayoutManager(this));
        rcv_result.setHasFixedSize(true);
        rcv_result.setOverScrollMode(View.OVER_SCROLL_NEVER);
        rcv_result.setAdapter(resultAdapter);
    }

    private void drawChart() {
        tickerView.setCharacterLists(TickerUtils.provideNumberList());
        tickerView.setPreferredScrollingDirection(TickerView.ScrollingDirection.ANY);
        resultChart.animateX(2000);
        tickerView.setText("0");
        tickerView.setText(String.valueOf(score));
        resultChart.setTouchEnabled(false);
        resultChart.getDescription().setText("");
        resultChart.getLegend().setEnabled(false);
        PieDataSet pieDataSet = new PieDataSet(loadDataValues(), "");
        pieDataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                if(value ==0) return "";
                return String.valueOf((int) value);
            }
        });
        pieDataSet.setValueTextSize(18);
        pieDataSet.setColors(colorClassArray);
        PieData pieData = new PieData(pieDataSet);
        resultChart.setData(pieData);
    }

    private void addControls() {
        btn_home = findViewById(R.id.btn_home);
        resultChart = findViewById(R.id.chart_result);
        tickerView = findViewById(R.id.ticker);
        rcv_result = findViewById(R.id.rcv_result);
        toolbar = findViewById(R.id.toolbar);
        btn_home.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResultActivity.this, MainActivity.class));
                finish();
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, RealTestActivity.class);
                intent.putExtra("listAnswer", (Serializable) answerList);
                startActivity(intent);
            }
        });
    }

    private List<PieEntry> loadDataValues() {
        ArrayList<PieEntry> res = new ArrayList<>();
        res.add(new PieEntry(incorrectAnswers, ""));
        res.add(new PieEntry(correctAnswers, ""));
        res.add(new PieEntry(notDoneAnswers, ""));
        return res;
    }
    private void loadAnswerList()
    {
        answerList = new ArrayList<>();
        for(Long num : keyMap.keySet())
        {
            if (DataLocalManager.getAnswer(num) == null)
            {
                answerList.add(new Answer(num, keyMap.get(num), ""));
            }else {
                answerList.add(new Answer(num, keyMap.get(num), DataLocalManager.getAnswer(num).getKeyClick()));
            }
        }
        Collections.sort(answerList, new Comparator<Answer>() {
            @Override
            public int compare(Answer answer1, Answer answer2) {
                return (int) answer1.getAnswerNum()- (int)answer2.getAnswerNum();
            }
        });
    }
    private void countAnswers() {
        //reset value
        correctAnswers = 0;
        incorrectAnswers = 0;
        notDoneAnswers = 0;
        for (Long num : keyMap.keySet()) {
            if (DataLocalManager.getAnswer(num) == null) notDoneAnswers++;
            else {
                if (keyMap.get(num).equals(DataLocalManager.getAnswer(num).getKeyClick())) {
                    correctAnswers++;
                } else {
                    incorrectAnswers++;
                }
            }
        }
    }

    private void calculateScore() {
        // conversion table at: shorturl.at/copuE
        score = 0;
       if(correctAnswers>=0&&correctAnswers<=6) score = 5;
       else if(correctAnswers>=7&&correctAnswers<=30)
       {
           score = 10 + (5* (correctAnswers-7));
       }else if( correctAnswers >=31&&correctAnswers<=38)
       {
           score = 135 + (5* (correctAnswers-31));
       }else if (correctAnswers >=39&&correctAnswers<=43)
       {
           score = 180 + (5* (correctAnswers-39));
       }else if(correctAnswers == 44 )
       {
           score = 210;
       } else if (correctAnswers>=45&&correctAnswers<=53)
       {
           score = 220 + (5* (correctAnswers-45));
       } else if (correctAnswers>=54&&correctAnswers<=69)
       {
           score = 270 + (5* (correctAnswers-54));
       }else if (correctAnswers>=70&&correctAnswers<=74)
       {
           score = 360 + (5* (correctAnswers-70));
       }else if (correctAnswers>=75&&correctAnswers<=79)
       {
           score = 390 + (5* (correctAnswers-75));
       }else if (correctAnswers>=80 && correctAnswers<=84)
       {
           score = 420 + (5* (correctAnswers-80));
       }else if (correctAnswers>=85&&correctAnswers<=87)
       {
           score = 450 + (5* (correctAnswers-85));
       }else if (correctAnswers>=88&&correctAnswers<=92)
       {
           score = 470 + (5* (correctAnswers-88));
       }else if (correctAnswers>=92&&correctAnswers<=100)
       {
           score = 495;
       }
    }

}