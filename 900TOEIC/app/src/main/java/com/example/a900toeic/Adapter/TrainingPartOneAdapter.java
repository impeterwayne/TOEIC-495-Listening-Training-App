package com.example.a900toeic.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.a900toeic.Database.DBQuery;
import com.example.a900toeic.Fragment.PartOneFragment;
import com.example.a900toeic.Model.Question;
import com.example.a900toeic.Model.QuestionPartOne;

import java.util.List;

public class TrainingPartOneAdapter extends FragmentStateAdapter {
    private List<QuestionPartOne> data;
    public TrainingPartOneAdapter(@NonNull FragmentActivity fragmentActivity, List<QuestionPartOne> data) {
        super(fragmentActivity);
        this.data = data;
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        PartOneFragment fragment = new PartOneFragment();
        fragment.setData(data.get(position));
        return fragment;
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

}
