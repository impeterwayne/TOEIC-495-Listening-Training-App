package com.example.a900toeic.Fragment;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a900toeic.Database.DBQuery;
import com.example.a900toeic.LocalData.DataLocalManager;
import com.example.a900toeic.Model.QuestionPartOne;
import com.example.a900toeic.R;


public class PartOneFragment extends Fragment {
    private ImageView img_part_one;
    private AppCompatButton btn_keyA, btn_keyB, btn_keyC,btn_keyD;
    private TextView txt_script_keyA,txt_script_keyB,txt_script_keyC,txt_script_keyD;
    private QuestionPartOne data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_part_one, container, false);
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
        btn_keyD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processAnswer(btn_keyD);
                DataLocalManager.addDoneQuestion(data.getId());
            }
        });
    }
    private void addControls(View view) {
        img_part_one = view.findViewById(R.id.img_part_one);
        btn_keyA = view.findViewById(R.id.btn_key1A);
        btn_keyA.setTag("A");
        btn_keyB = view.findViewById(R.id.btn_key1B);
        btn_keyB.setTag("B");
        btn_keyC = view.findViewById(R.id.btn_key1C);
        btn_keyC.setTag("C");
        btn_keyD = view.findViewById(R.id.btn_key1D);
        btn_keyD.setTag("D");
        txt_script_keyA = view.findViewById(R.id.txt_script_keyA);
        txt_script_keyB = view.findViewById(R.id.txt_script_keyB);
        txt_script_keyC = view.findViewById(R.id.txt_script_keyC);
        txt_script_keyD = view.findViewById(R.id.txt_script_keyD);
        passDataToView();

    }

    private void passDataToView() {
        Glide.with(PartOneFragment.this).load(data.getImage_url()).fitCenter().into(img_part_one);
        txt_script_keyA.setText(data.getScript_keyA());
        txt_script_keyB.setText(data.getScript_keyB());
        txt_script_keyC.setText(data.getScript_keyC());
        txt_script_keyD.setText(data.getScript_keyD());
    }

    public void setData(QuestionPartOne data)
    {
        this.data = data;
    }
    public void processAnswer(AppCompatButton btnKeyClick)
    {
        DBQuery.updateStatisticValues(1);
        if(btnKeyClick.getTag().equals(data.getKey()))
        {
            btnKeyClick.setBackgroundResource(R.drawable.bg_right_answer);
        }else
        {
            btnKeyClick.setBackgroundResource(R.drawable.bg_wrong_answer);
            if(btn_keyA.getTag().equals(data.getKey())) btn_keyA.setBackgroundResource(R.drawable.bg_right_answer);
            if(btn_keyB.getTag().equals(data.getKey())) btn_keyB.setBackgroundResource(R.drawable.bg_right_answer);
            if(btn_keyC.getTag().equals(data.getKey())) btn_keyC.setBackgroundResource(R.drawable.bg_right_answer);
            if(btn_keyD.getTag().equals(data.getKey())) btn_keyD.setBackgroundResource(R.drawable.bg_right_answer);
        }
        txt_script_keyA.setVisibility(View.VISIBLE);
        txt_script_keyB.setVisibility(View.VISIBLE);
        txt_script_keyC.setVisibility(View.VISIBLE);
        txt_script_keyD.setVisibility(View.VISIBLE);
        btn_keyA.setClickable(false);
        btn_keyB.setClickable(false);
        btn_keyC.setClickable(false);
        btn_keyD.setClickable(false);
    }
}