package com.example.a900toeic.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.example.a900toeic.Fragment.PartOneFragment;

public class ViewPagerPartOneAdapter extends FragmentStateAdapter {
    public ViewPagerPartOneAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new PartOneFragment();
    }
    @Override
    public int getItemCount() {
        return 2;
    }
}
