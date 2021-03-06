package com.example.a900toeic.Activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.example.a900toeic.Adapter.ViewPagerPartOneAdapter;
import com.example.a900toeic.Database.QueryDB;
import com.example.a900toeic.R;

import java.io.IOException;

public class PartOneActivity extends AppCompatActivity {
    private ViewPager2 mViewPager;
    private Toolbar toolbar;
    private ViewPagerPartOneAdapter mViewPagerAdapter;
    private MediaPlayer mediaPlayer;
    private ImageView btn_forward, btn_backward, btn_play;
    private SeekBar seek_bar;
    private TextView txt_timeline;
    private Runnable runnable;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_one);
        addControls();
        playFirstQuestion();
        addEvents();
    }
    private void addControls() {

        mViewPager = findViewById(R.id.viewpager_part_one);
        mViewPagerAdapter = new ViewPagerPartOneAdapter(this);
        mViewPager.setAdapter(mViewPagerAdapter);
        toolbar = findViewById(R.id.toolbar);
        btn_backward= findViewById(R.id.btn_backward);
        btn_forward = findViewById(R.id.btn_forward);
        btn_play = findViewById(R.id.btn_play);
        seek_bar = findViewById(R.id.seek_bar);
        txt_timeline = findViewById(R.id.txt_timeline);
        handler = new Handler();
    }

    private void addEvents() {

        btn_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn_backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying())
                {
                    btn_play.setImageResource(R.drawable.ic_play);
                    mediaPlayer.pause();
                }else
                {
                    btn_play.setImageResource(R.drawable.ic_pause);
                    mediaPlayer.start();
                }
            }
        });
        seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b)
                {
                    mediaPlayer.seekTo(i);
                    seekBar.setProgress(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                mediaPlayer.release();
                seek_bar.setEnabled(false);
                finish();
            }
        });
        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                btn_play.setImageResource(R.drawable.ic_pause);
                if(mediaPlayer!=null)
                {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                }
                mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(QueryDB.questionPartOneList.get(position).getAudio_url());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        seek_bar.setMax(mediaPlayer.getDuration());
                        txt_timeline.setText(getTimeString(mediaPlayer.getDuration()));
                        mediaPlayer.start();
                        updateSeekBar();
                    }
                });
                mediaPlayer.prepareAsync();
            }
        });
    }
    private void playFirstQuestion() {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(QueryDB.questionPartOneList.get(0).getAudio_url());
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                txt_timeline.setText(getTimeString(mediaPlayer.getDuration()));
                seek_bar.setMax(mediaPlayer.getDuration());
                mediaPlayer.start();
                updateSeekBar();
            }
        });
        mediaPlayer.prepareAsync();
    }

    public void updateSeekBar()
    {
        try {
            int currPos = mediaPlayer.getCurrentPosition();

            seek_bar.setProgress(currPos);
            runnable = new Runnable() {
                @Override
                public void run() {
                    updateSeekBar();
                }
            };
            handler.postDelayed(runnable,500);
        }catch(Exception e){

        }


    }
    private String getTimeString(long millis) {
        StringBuffer buf = new StringBuffer();
        int minutes = (int) ((millis % (1000 * 60 * 60)) / (1000 * 60));
        int seconds = (int) (((millis % (1000 * 60 * 60)) % (1000 * 60)) / 1000);
        buf
                .append(String.format("%02d", minutes))
                .append(":")
                .append(String.format("%02d", seconds));
        return buf.toString();
    }

    @Override
    protected void onStop() {
        super.onStop();
        QueryDB.loadDataPartOne();
    }
}