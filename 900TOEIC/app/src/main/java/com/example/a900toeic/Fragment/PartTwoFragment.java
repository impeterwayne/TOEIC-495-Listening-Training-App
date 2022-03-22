package com.example.a900toeic.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.a900toeic.Database.DBQuery;
import com.example.a900toeic.LocalData.DataLocalManager;
import com.example.a900toeic.Model.QuestionPartTwo;
import com.example.a900toeic.R;


public class PartTwoFragment extends Fragment {
    private AppCompatButton btn_keyA, btn_keyB, btn_keyC;
    private TextView txt_script_part_two, txt_script_keyA, txt_script_keyB,txt_script_keyC;
    private QuestionPartTwo data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_part_two, container, false);
        addControls(view);
        addEvents(view);
        return view;
    }

    private void addEvents(View view) {
        btn_keyA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processAnswer(btn_keyA);
                DataLocalManager.addDoneQuestion(data.getId());
            }
        });
        btn_keyB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processAnswer(btn_keyB);
                DataLocalManager.addDoneQuestion(data.getId());
            }
        });
        btn_keyC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processAnswer(btn_keyC);
                DataLocalManager.addDoneQuestion(data.getId());
            }
        });
    }

    private void addControls(View view) {
        btn_keyA = view.findViewById(R.id.btn_key1A);
        btn_keyA.setTag("A");
        btn_keyB = view.findViewById(R.id.btn_key1B);
        btn_keyB.setTag("B");
        btn_keyC = view.findViewById(R.id.btn_key1C);
        btn_keyC.setTag("C");
        txt_script_part_two = view.findViewById(R.id.txt_script_part_two);
        txt_script_keyA = view.findViewById(R.id.txt_script_keyA);
        txt_script_keyB = view.findViewById(R.id.txt_script_keyB);
        txt_script_keyC = view.findViewById(R.id.txt_script_keyC);
        loadDataToView();
    }
    public void processAnswer(AppCompatButton btnKeyClick)
    {
        DBQuery.updateStatisticValues(2);
        if(btnKeyClick.getTag().equals(data.getKey()))
        {
            btnKeyClick.setBackgroundResource(R.drawable.bg_right_answer);
        }else
        {
            btnKeyClick.setBackgroundResource(R.drawable.bg_wrong_answer);
            if(btn_keyA.getTag().equals(data.getKey())) btn_keyA.setBackgroundResource(R.drawable.bg_right_answer);
            if(btn_keyB.getTag().equals(data.getKey())) btn_keyB.setBackgroundResource(R.drawable.bg_right_answer);
            if(btn_keyC.getTag().equals(data.getKey())) btn_keyC.setBackgroundResource(R.drawable.bg_right_answer);
        }
        txt_script_part_two.setText(data.getScript());
        txt_script_keyA.setVisibility(View.VISIBLE);
        txt_script_keyB.setVisibility(View.VISIBLE);
        txt_script_keyC.setVisibility(View.VISIBLE);
        btn_keyA.setClickable(false);
        btn_keyB.setClickable(false);
        btn_keyC.setClickable(false);
    }
    public void loadDataToView()
    {
        txt_script_keyA.setText(data.getScript_keyA());
        txt_script_keyB.setText(data.getScript_keyB());
        txt_script_keyC.setText(data.getScript_keyC());
    }
    public void setData(QuestionPartTwo data) {
        this.data = data;
    }
}