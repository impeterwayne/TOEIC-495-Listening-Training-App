package com.example.a900toeic.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.a900toeic.Activity.TrainingActivity;
import com.example.a900toeic.Database.DBQuery;
import com.example.a900toeic.R;

public class ReviewFragment extends Fragment {

    private AppCompatButton btn_review_part1,btn_review_part2, btn_review_part3,btn_review_part4;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadDataForReview();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        addControls(view);
        addEvents();
        return view;
    }

    private void loadDataForReview() {
        DBQuery.loadDataReviewPartOne(DBQuery.user_id);
        DBQuery.loadDataReviewPartTwo(DBQuery.user_id);
        DBQuery.loadDataReviewPartThree(DBQuery.user_id);
        DBQuery.loadDataReviewPartFour(DBQuery.user_id);
    }

    private void addEvents() {
        btn_review_part1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TrainingActivity.class);
                intent.putExtra("part",11);
                startActivity(intent);
            }
        });
        btn_review_part2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TrainingActivity.class);
                intent.putExtra("part",12);
                startActivity(intent);
            }
        });
        btn_review_part3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TrainingActivity.class);
                intent.putExtra("part",13);
                startActivity(intent);
            }
        });
        btn_review_part4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TrainingActivity.class);
                intent.putExtra("part",14);
                startActivity(intent);
            }
        });

    }

    private void addControls(View view) {
        btn_review_part1 = view.findViewById(R.id.btn_review_part1);
        btn_review_part2 = view.findViewById(R.id.btn_review_part2);
        btn_review_part3 = view.findViewById(R.id.btn_review_part3);
        btn_review_part4 = view.findViewById(R.id.btn_review_part4);
    }
}