package com.example.a900toeic.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.example.a900toeic.Adapter.ViewPagerPartOneAdapter;
import com.example.a900toeic.R;

public class PartOneActivity extends AppCompatActivity {
    private ViewPager2 mViewPager;
    private Toolbar toolbar;
    private ViewPagerPartOneAdapter mViewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_one);
        addControls();
        addEvents();
    }

    private void addControls() {
        mViewPager = findViewById(R.id.viewpager_part_one);
        mViewPagerAdapter = new ViewPagerPartOneAdapter(this);
        mViewPager.setAdapter(mViewPagerAdapter);
        toolbar = findViewById(R.id.toolbar);
    }

    private void addEvents() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PartOneActivity.this,MainActivity.class));
                finish();
            }
        });
    }

}