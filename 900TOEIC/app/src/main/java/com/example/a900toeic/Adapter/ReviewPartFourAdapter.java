package com.example.a900toeic.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.a900toeic.Database.DBQuery;
import com.example.a900toeic.Fragment.EmptyReviewFragment;
import com.example.a900toeic.Fragment.PartFourFragment;
import com.example.a900toeic.Model.Question;
import com.example.a900toeic.Model.QuestionPartThreeAndFour;

import java.util.List;

public class ReviewPartFourAdapter extends FragmentStateAdapter {
    private List<QuestionPartThreeAndFour> data;
    public ReviewPartFourAdapter(@NonNull FragmentActivity fragmentActivity,List<QuestionPartThreeAndFour> data) {
        super(fragmentActivity);
        this.data = data;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        PartFourFragment fragment = new PartFourFragment();
        fragment.setData( data.get(position));
        return fragment;
    }

    @Override
    public int getItemCount() {
//        if(DBQuery.questionPartFourReviewList.size()==0) return 1;
        return data.size();
    }
}
