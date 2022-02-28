package com.example.a900toeic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a900toeic.Activity.RealTestActivity;
import com.example.a900toeic.Model.RealTest;
import com.example.a900toeic.R;

import java.util.List;

public class RealTestAdapter extends RecyclerView.Adapter<RealTestAdapter.ViewHolder> {
    private Context context;
    private List<RealTest> testList;

    public RealTestAdapter(Context context, List<RealTest> testList) {
        this.context = context;
        this.testList = testList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_test, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String testName = testList.get(position).getName();
        holder.txt_testName.setText(testName);

    }

    @Override
    public int getItemCount() {
        return testList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView txt_testName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_testName = itemView.findViewById(R.id.txt_testName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //TODO: add a dialog here
            Intent intent = new Intent(context, RealTestActivity.class);
            intent.putExtra("testName", testList.get(getAdapterPosition()).getName());
            context.startActivity(intent);
        }
    }
}
