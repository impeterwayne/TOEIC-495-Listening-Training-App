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
import com.example.a900toeic.Model.RealTestPartThreeAndFourQuestion;
import com.example.a900toeic.R;

import java.util.List;

public class RealTestPartThreeAndFourAdapter extends RecyclerView.Adapter<RealTestPartThreeAndFourAdapter.ViewHolder>{
    private Context context;
    private List<RealTestPartThreeAndFourQuestion> questionList;

    public RealTestPartThreeAndFourAdapter(Context context, List<RealTestPartThreeAndFourQuestion> questionList) {
        this.context = context;
        this.questionList = questionList;
    }

    @NonNull
    @Override
    public RealTestPartThreeAndFourAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_part34,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RealTestPartThreeAndFourAdapter.ViewHolder holder, int position) {
        RealTestPartThreeAndFourQuestion question = questionList.get(position);
        holder.txt_question_number1.setText("Question No. " + question.getNumber1());
        holder.txt_question1.setText(question.getQuestion1());
        holder.txt_question_number2.setText("Question No. " + question.getNumber2());
        holder.txt_question2.setText(question.getQuestion2());
        holder.txt_question_number3.setText("Question No. " + question.getNumber3());
        holder.txt_question3.setText(question.getQuestion3());
        holder.rb_part34_key1A.setText(question.getKey1A());
        holder.rb_part34_key1B.setText(question.getKey1B());
        holder.rb_part34_key1C.setText(question.getKey1C());
        holder.rb_part34_key1D.setText(question.getKey1D());
        holder.rb_part34_key2A.setText(question.getKey2A());
        holder.rb_part34_key2B.setText(question.getKey2B());
        holder.rb_part34_key2C.setText(question.getKey2C());
        holder.rb_part34_key2D.setText(question.getKey2D());
        holder.rb_part34_key3A.setText(question.getKey3A());
        holder.rb_part34_key3B.setText(question.getKey3B());
        holder.rb_part34_key3C.setText(question.getKey3C());
        holder.rb_part34_key3D.setText(question.getKey3D());
        long number1 = questionList.get(position).getNumber1();
        long number2 = questionList.get(position).getNumber2();
        long number3 = questionList.get(position).getNumber3();
        Intent intent = new Intent(context, ResultActivity.class);
        holder.rb_part34_key1A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataLocalManager.addKeyClick(number1, "A");
                holder.rb_part34_key1B.setChecked(false);
                holder.rb_part34_key1C.setChecked(false);
                holder.rb_part34_key1D.setChecked(false);
            }
        });
        holder.rb_part34_key1B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataLocalManager.addKeyClick(number1, "B");
                holder.rb_part34_key1A.setChecked(false);
                holder.rb_part34_key1C.setChecked(false);
                holder.rb_part34_key1D.setChecked(false);
            }
        });
        holder.rb_part34_key1C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataLocalManager.addKeyClick(number1, "C");
                holder.rb_part34_key1A.setChecked(false);
                holder.rb_part34_key1B.setChecked(false);
                holder.rb_part34_key1D.setChecked(false);
            }
        });
        holder.rb_part34_key1D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataLocalManager.addKeyClick(number1, "D");
                holder.rb_part34_key1A.setChecked(false);
                holder.rb_part34_key1B.setChecked(false);
                holder.rb_part34_key1C.setChecked(false);
            }
        });
        holder.rb_part34_key2A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataLocalManager.addKeyClick(number2, "A");
                holder.rb_part34_key2B.setChecked(false);
                holder.rb_part34_key2C.setChecked(false);
                holder.rb_part34_key2D.setChecked(false);
            }
        });
        holder.rb_part34_key2B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataLocalManager.addKeyClick(number2, "B");
                holder.rb_part34_key2A.setChecked(false);
                holder.rb_part34_key2C.setChecked(false);
                holder.rb_part34_key2D.setChecked(false);
            }
        });
        holder.rb_part34_key2C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataLocalManager.addKeyClick(number2, "C");
                holder.rb_part34_key2A.setChecked(false);
                holder.rb_part34_key2B.setChecked(false);
                holder.rb_part34_key2D.setChecked(false);
            }
        });
        holder.rb_part34_key2D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataLocalManager.addKeyClick(number2, "D");
                holder.rb_part34_key2A.setChecked(false);
                holder.rb_part34_key2B.setChecked(false);
                holder.rb_part34_key2C.setChecked(false);
            }
        });
        holder.rb_part34_key3A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataLocalManager.addKeyClick(number3, "A");
                holder.rb_part34_key3B.setChecked(false);
                holder.rb_part34_key3C.setChecked(false);
                holder.rb_part34_key3D.setChecked(false);
            }
        });
        holder.rb_part34_key3B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataLocalManager.addKeyClick(number3, "B");
                holder.rb_part34_key3A.setChecked(false);
                holder.rb_part34_key3C.setChecked(false);
                holder.rb_part34_key3D.setChecked(false);
            }
        });
        holder.rb_part34_key3C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataLocalManager.addKeyClick(number3, "C");
                holder.rb_part34_key3A.setChecked(false);
                holder.rb_part34_key3B.setChecked(false);
                holder.rb_part34_key3D.setChecked(false);
            }
        });
        holder.rb_part34_key3D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataLocalManager.addKeyClick(number3, "D");
                holder.rb_part34_key3A.setChecked(false);
                holder.rb_part34_key3B.setChecked(false);
                holder.rb_part34_key3C.setChecked(false);
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_question_number1,txt_question_number2,txt_question_number3;
        private TextView txt_question1,txt_question2,txt_question3;
        private RadioButton rb_part34_key1A,rb_part34_key1B,rb_part34_key1C, rb_part34_key1D;
        private RadioButton rb_part34_key2A,rb_part34_key2B,rb_part34_key2C, rb_part34_key2D;
        private RadioButton rb_part34_key3A,rb_part34_key3B,rb_part34_key3C, rb_part34_key3D;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            addControls(itemView);
        }
        private void addControls(View itemView) {
            txt_question_number1 = itemView.findViewById(R.id.txt_question_number1);
            txt_question_number2 = itemView.findViewById(R.id.txt_question_number2);
            txt_question_number3 = itemView.findViewById(R.id.txt_question_number3);
            txt_question1 = itemView.findViewById(R.id.txt_question1);
            txt_question2 = itemView.findViewById(R.id.txt_question2);
            txt_question3 = itemView.findViewById(R.id.txt_question3);
            rb_part34_key1A = itemView.findViewById(R.id.rb_part34_key1A);
            rb_part34_key1B = itemView.findViewById(R.id.rb_part34_key1B);
            rb_part34_key1C = itemView.findViewById(R.id.rb_part34_key1C);
            rb_part34_key1D = itemView.findViewById(R.id.rb_part34_key1D);
            rb_part34_key2A = itemView.findViewById(R.id.rb_part34_key2A);
            rb_part34_key2B = itemView.findViewById(R.id.rb_part34_key2B);
            rb_part34_key2C = itemView.findViewById(R.id.rb_part34_key2C);
            rb_part34_key2D = itemView.findViewById(R.id.rb_part34_key2D);
            rb_part34_key3A = itemView.findViewById(R.id.rb_part34_key3A);
            rb_part34_key3B = itemView.findViewById(R.id.rb_part34_key3B);
            rb_part34_key3C = itemView.findViewById(R.id.rb_part34_key3C);
            rb_part34_key3D = itemView.findViewById(R.id.rb_part34_key3D);
        }
    }
}
