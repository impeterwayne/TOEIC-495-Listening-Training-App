package com.example.a900toeic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.a900toeic.Activity.ResultActivity;
import com.example.a900toeic.LocalData.DataLocalManager;
import com.example.a900toeic.Model.RealTestPartOneQuestion;
import com.example.a900toeic.R;

import java.util.List;

public class RealTestPartOneAdapter extends RecyclerView.Adapter<RealTestPartOneAdapter.ViewHolder> {
    private Context context;
    private List<RealTestPartOneQuestion> questionList;

    public RealTestPartOneAdapter(Context context, List<RealTestPartOneQuestion> questionList) {
        this.context = context;
        this.questionList = questionList;
    }

    @NonNull
    @Override
    public RealTestPartOneAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_part1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RealTestPartOneAdapter.ViewHolder holder, int position) {
        RealTestPartOneQuestion question = questionList.get(position);
        holder.txt_question_number.setText("Question No. " + question.getNumber());
        Glide.with(context).load(question.getImage_url()).fitCenter().into(holder.img_part1_photo);
        long number = questionList.get(position).getNumber();

        holder.rb_part1_keyA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataLocalManager.addKeyClick(number, "A");
                holder.rb_part1_keyB.setChecked(false);
                holder.rb_part1_keyC.setChecked(false);
                holder.rb_part1_keyD.setChecked(false);
            }
        });
        holder.rb_part1_keyB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataLocalManager.addKeyClick(number, "B");
                holder.rb_part1_keyA.setChecked(false);
                holder.rb_part1_keyC.setChecked(false);
                holder.rb_part1_keyD.setChecked(false);
            }
        });
        holder.rb_part1_keyC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataLocalManager.addKeyClick(number, "C");
                holder.rb_part1_keyA.setChecked(false);
                holder.rb_part1_keyB.setChecked(false);
                holder.rb_part1_keyD.setChecked(false);
            }
        });
        holder.rb_part1_keyD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataLocalManager.addKeyClick(number, "D");
                holder.rb_part1_keyA.setChecked(false);
                holder.rb_part1_keyB.setChecked(false);
                holder.rb_part1_keyC.setChecked(false);
            }
        });
    }


    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_question_number;
        private ImageView img_part1_photo;
        private RadioButton rb_part1_keyA, rb_part1_keyB, rb_part1_keyC, rb_part1_keyD;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_question_number = itemView.findViewById(R.id.txt_question_number);
            img_part1_photo = itemView.findViewById(R.id.img_part1_photo);
            rb_part1_keyA = itemView.findViewById(R.id.rb_part1_keyA);
            rb_part1_keyB = itemView.findViewById(R.id.rb_part1_keyB);
            rb_part1_keyC = itemView.findViewById(R.id.rb_part1_keyC);
            rb_part1_keyD = itemView.findViewById(R.id.rb_part1_keyD);
        }
    }
}
