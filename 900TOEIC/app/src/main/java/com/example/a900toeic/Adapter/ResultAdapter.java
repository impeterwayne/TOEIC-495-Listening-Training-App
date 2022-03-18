package com.example.a900toeic.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.a900toeic.Model.Answer;
import com.example.a900toeic.R;
import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {
    private final Context context;
    private final List<Answer> answerList;

    public ResultAdapter(Context context, List<Answer> answerList) {
        this.context = context;
        this.answerList = answerList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_answer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Answer answer = answerList.get(position);
        holder.txt_answer_num.setText("Question No. "+ answer.getAnswerNum());
        if(!answer.getKeyClick().equals(answer.getCorrectKey()))
        {
            switch(answer.getKeyClick())
            {
                case "A":
                    holder.txt_answer_keyA.setBackgroundResource(R.drawable.bg_circle_red);
                    return;
                case "B":
                    holder.txt_answer_keyB.setBackgroundResource(R.drawable.bg_circle_red);
                    break;
                case "C":
                    holder.txt_answer_keyC.setBackgroundResource(R.drawable.bg_circle_red);
                    break;
                case "D":
                    holder.txt_answer_keyD.setBackgroundResource(R.drawable.bg_circle_red);
                    break;
                default:
                    holder.txt_answer_keyA.setBackgroundResource(R.drawable.bg_cirle_black);
                    holder.txt_answer_keyB.setBackgroundResource(R.drawable.bg_cirle_black);
                    holder.txt_answer_keyC.setBackgroundResource(R.drawable.bg_cirle_black);
                    holder.txt_answer_keyD.setBackgroundResource(R.drawable.bg_cirle_black);
                    switch (answer.getCorrectKey())
                    {
                        case "A":
                            holder.txt_answer_keyA.setBackgroundResource(R.drawable.bg_circle_green_fade);
                            break;
                        case "B":
                            holder.txt_answer_keyB.setBackgroundResource(R.drawable.bg_circle_green_fade);
                            break;
                        case "C":
                            holder.txt_answer_keyC.setBackgroundResource(R.drawable.bg_circle_green_fade);
                            break;
                        case "D":
                            holder.txt_answer_keyD.setBackgroundResource(R.drawable.bg_circle_green_fade);
                            break;
                    }
                    return;
            }
        }
        switch (answer.getCorrectKey())
        {
            case "A":
                holder.txt_answer_keyA.setBackgroundResource(R.drawable.bg_circle_green);
                break;
            case "B":
                holder.txt_answer_keyB.setBackgroundResource(R.drawable.bg_circle_green);
                break;
            case "C":
                holder.txt_answer_keyC.setBackgroundResource(R.drawable.bg_circle_green);
                break;
            case "D":
                holder.txt_answer_keyD.setBackgroundResource(R.drawable.bg_circle_green);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return answerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_answer_num;
        private TextView txt_answer_keyA,txt_answer_keyB,txt_answer_keyC,txt_answer_keyD;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_answer_num = itemView.findViewById(R.id.txt_answer_num);
            txt_answer_keyA = itemView.findViewById(R.id.txt_answer_keyA);
            txt_answer_keyB = itemView.findViewById(R.id.txt_answer_keyB);
            txt_answer_keyC = itemView.findViewById(R.id.txt_answer_keyC);
            txt_answer_keyD = itemView.findViewById(R.id.txt_answer_keyD);
        }
    }
}
