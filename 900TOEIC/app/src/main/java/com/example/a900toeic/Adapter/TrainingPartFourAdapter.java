package com.example.a900toeic.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.a900toeic.Database.DBQuery;
import com.example.a900toeic.Fragment.PartFourFragment;
import com.example.a900toeic.Fragment.PartThreeFragment;

public class TrainingPartFourAdapter extends FragmentStateAdapter {
    public TrainingPartFourAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        PartFourFragment fragment = new PartFourFragment();
        fragment.setData(DBQuery.questionPartFourList.get(position));
        return fragment;
    }

    @Override
    public int getItemCount() {
        return DBQuery.questionPartFourList.size();
    }
}
