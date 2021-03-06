package com.example.a900toeic.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.example.a900toeic.Fragment.PartTwoFragment;
import com.example.a900toeic.Model.Question;
import com.example.a900toeic.Model.QuestionPartTwo;
import java.util.List;

public class TrainingPartTwoAdapter extends FragmentStateAdapter {
    private List<QuestionPartTwo> data;
    public TrainingPartTwoAdapter(@NonNull FragmentActivity fragmentActivity, List<QuestionPartTwo> data) {
        super(fragmentActivity);
        this.data = data;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        PartTwoFragment fragment = new PartTwoFragment();
        fragment.setData( data.get(position));
        return fragment;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
