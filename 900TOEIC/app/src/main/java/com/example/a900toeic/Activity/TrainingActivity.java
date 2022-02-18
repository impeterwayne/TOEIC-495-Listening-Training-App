package com.example.a900toeic.Activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.a900toeic.Adapter.CategoryAdapter;
import com.example.a900toeic.Adapter.ViewPagerPartFourAdapter;
import com.example.a900toeic.Adapter.ViewPagerPartOneAdapter;
import com.example.a900toeic.Adapter.ViewPagerPartThreeAdapter;
import com.example.a900toeic.Adapter.ViewPagerPartTwoAdapter;
import com.example.a900toeic.Database.DBQuery;
import com.example.a900toeic.Model.Question;
import com.example.a900toeic.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrainingActivity extends AppCompatActivity {
    private ViewPager2 mViewPager;
    private Toolbar toolbar;
    private TextView txt_toolbar_part, txt_toolbar_description;
    private MediaPlayer mediaPlayer;
    private ImageView btn_forward, btn_backward, btn_play, btn_bookmark;
    private SeekBar seek_bar;
    private TextView txt_timeline;
    private Runnable runnable;
    private Handler handler;
    private int partId;
    private List<Question> questionList = new ArrayList<>();
    private static String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        addControls();
        loadData();
        playAudioFile(0);
        handleBookmarkButton(0);
        addEvents();
    }
    private void addControls() {

        mViewPager = findViewById(R.id.viewpager_training);
        toolbar = findViewById(R.id.toolbar);
        btn_backward= findViewById(R.id.btn_backward);
        btn_forward = findViewById(R.id.btn_forward);
        btn_play = findViewById(R.id.btn_play);
        btn_bookmark = findViewById(R.id.btn_bookmark);
        seek_bar = findViewById(R.id.seek_bar);
        txt_timeline = findViewById(R.id.txt_timeline);
        txt_toolbar_part = findViewById(R.id.txt_toolbar_part);
        txt_toolbar_description = findViewById(R.id.txt_toolbar_description);
        handler = new Handler();
    }

    private void loadData() {
        partId = getIntent().getIntExtra("part",1);
        questionList.clear();
        switch(partId)
        {
            case 1:
                for(int i = 0; i < DBQuery.questionPartOneList.size(); i++)
                {
                    questionList.add(DBQuery.questionPartOneList.get(i));
                }
                ViewPagerPartOneAdapter mViewPagerPartOneAdapter = new ViewPagerPartOneAdapter(this);
                mViewPager.setAdapter(mViewPagerPartOneAdapter);
                txt_toolbar_part.setText("Part One Training");
                txt_toolbar_description.setText(CategoryAdapter.categories[0].getDescription());
                break;
            case 2:
                for(int i = 0; i < DBQuery.questionPartTwoList.size(); i++)
                {
                    questionList.add(DBQuery.questionPartTwoList.get(i));
                }
                ViewPagerPartTwoAdapter mViewPagerPartTwoAdapter = new ViewPagerPartTwoAdapter(this);
                mViewPager.setAdapter(mViewPagerPartTwoAdapter);
                txt_toolbar_part.setText("Part Two Training");
                txt_toolbar_description.setText(CategoryAdapter.categories[1].getDescription());
                break;
            case 3:
                for(int i = 0; i < DBQuery.questionPartThreeList.size(); i++)
                {
                    questionList.add(DBQuery.questionPartThreeList.get(i));
                }
                ViewPagerPartThreeAdapter mViewPagerPartThreeAdapter = new ViewPagerPartThreeAdapter(this);
                mViewPager.setAdapter(mViewPagerPartThreeAdapter);
                txt_toolbar_part.setText("Part Three Training");
                txt_toolbar_description.setText(CategoryAdapter.categories[2].getDescription());
                break;
            case 4:
                for(int i = 0; i < DBQuery.questionPartFourList.size(); i++)
                {
                    questionList.add(DBQuery.questionPartFourList.get(i));
                }
                ViewPagerPartFourAdapter mViewPagerPartFourAdapter = new ViewPagerPartFourAdapter(this);
                mViewPager.setAdapter(mViewPagerPartFourAdapter);
                txt_toolbar_part.setText("Part Four Training");
                txt_toolbar_description.setText(CategoryAdapter.categories[3].getDescription());
                break;
            case 11:
                break;
        }
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
                playAudioFile(position);
                handleBookmarkButton(position);

            }
        });

    }

    private void handleBookmarkButton(int position) {
            String part = "Part"+ partId;
                DBQuery.db.collection("User").document(user_id).collection(part)
                        .whereEqualTo("id", questionList.get(position).getId()).get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(task.isSuccessful())
                                {
                                    if(task.getResult().size()>0)
                                    {
                                        btn_bookmark.setImageResource(R.drawable.ic_bookmarked);
                                        btn_bookmark.setTag("bookmarked");
                                    }else {
                                        btn_bookmark.setImageResource(R.drawable.ic_bookmark);
                                        btn_bookmark.setTag("bookmark");
                                    }
                                }
                            }
                        });
                btn_bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn_bookmark.getTag().equals("bookmarked"))
                {
                    btn_bookmark.setImageResource(R.drawable.ic_bookmark);
                    btn_bookmark.setTag("bookmark");
                    DBQuery.db.collection("User").document(user_id).collection(part)
                            .document(questionList.get(position).getId()).
                            delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d("Delete successfully", questionList.get(position).getId());
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("Delete data", e.toString());
                        }
                    });
                }else {
                    btn_bookmark.setImageResource(R.drawable.ic_bookmarked);
                    btn_bookmark.setTag("bookmarked");
                    Map<String, String> value = new HashMap<>();
                    value.put("id",questionList.get(position).getId());
                    DBQuery.db.collection("User").document(user_id).collection(part)
                            .document(questionList.get(position).getId()).set(value);
                }
            }
        });
        }

    private void playAudioFile(int position) {
        btn_play.setImageResource(R.drawable.ic_pause);
        if(mediaPlayer!=null)
        {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(questionList.get(position).getAudio_url());
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
        refreshData();
    }

    private void refreshData() {
        DBQuery.loadDataPartOne();
        DBQuery.loadDataPartTwo();
        DBQuery.loadDataPartThree();
        DBQuery.loadDataPartFour();
    }
}