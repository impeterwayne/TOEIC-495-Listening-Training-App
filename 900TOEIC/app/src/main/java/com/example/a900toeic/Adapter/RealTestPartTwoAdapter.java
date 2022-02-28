package com.example.a900toeic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a900toeic.Activity.ResultActivity;
import com.example.a900toeic.LocalData.DataLocalManager;
import com.example.a900toeic.Model.RealTestPartTwoQuestion;
import com.example.a900toeic.R;

import java.util.List;

public class RealTestPartTwoAdapter extends RecyclerView.Adapter<RealTestPartTwoAdapter.ViewHolder> {
    private Context context;
    private List<RealTestPartTwoQuestion> questionList;

    public RealTestPartTwoAdapter(Context context, List<RealTestPartTwoQuestion> questionList) {
        this.context = context;
        this.questionList = questionList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_part2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RealTestPartTwoQuestion question = questionList.get(position);
        holder.txt_question_number.setText("Question No. " + question.getNumber());
        long number = questionList.get(position).getNumber();
        Intent intent = new Intent(context, ResultActivity.class);
        holder.rb_part2_keyA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataLocalManager.addKeyClick(number, "A");
                holder.rb_part2_keyB.setChecked(false);
                holder.rb_part2_keyC.setChecked(false);
            }
        });
        holder.rb_part2_keyB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataLocalManager.addKeyClick(number, "B");
                holder.rb_part2_keyA.setChecked(false);
                holder.rb_part2_keyC.setChecked(false);
            }
        });
        holder.rb_part2_keyC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataLocalManager.addKeyClick(number, "C");
                holder.rb_part2_keyA.setChecked(false);
                holder.rb_part2_keyB.setChecked(false);
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_question_number;
        private RadioButton rb_part2_keyA,rb_part2_keyB,rb_part2_keyC;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_question_number = itemView.findViewById(R.id.txt_question_number);
            rb_part2_keyA = itemView.findViewById(R.id.rb_part2_keyA);
            rb_part2_keyB = itemView.findViewById(R.id.rb_part2_keyB);
            rb_part2_keyC = itemView.findViewById(R.id.rb_part2_keyC);
        }
    }
}