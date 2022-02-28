package com.example.a900toeic.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.a900toeic.Database.DBQuery;
import com.example.a900toeic.LocalData.DataLocalManager;
import com.example.a900toeic.R;

import java.util.HashMap;
import java.util.Map;

public class ResultActivity extends AppCompatActivity {
    private TextView txt_result;
    private Map<Long, String> keyMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        txt_result = findViewById(R.id.txt_result);
        keyMap = (Map<Long, String>) getIntent().getSerializableExtra("keyMap");
        txt_result.setText(calculateScore()+"/"+keyMap.size()+"");
    }

    private long calculateScore() {
        long res= 0;
        for(Long num : keyMap.keySet())
        {
            if(keyMap.get(num).equals(DataLocalManager.getKeyClick(num)))
            {
                res++;
            }
        }
        return res;
    }

}