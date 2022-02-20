package com.example.a900toeic.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.a900toeic.Database.DBQuery;
import com.example.a900toeic.Fragment.EmptyReviewFragment;
import com.example.a900toeic.Fragment.PartFourFragment;

public class ReviewPartFourAdapter extends FragmentStateAdapter {
    public ReviewPartFourAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
//        if(DBQuery.questionPartFourReviewList.size() == 0) {
//            EmptyReviewFragment fragment = new EmptyReviewFragment();
//            return fragment;
//        }
        PartFourFragment fragment = new PartFourFragment();
        fragment.setData(DBQuery.questionPartFourReviewList.get(position));
        return fragment;
    }

    @Override
    public int getItemCount() {
//        if(DBQuery.questionPartFourReviewList.size()==0) return 1;
        return DBQuery.questionPartFourReviewList.size();
    }
}
