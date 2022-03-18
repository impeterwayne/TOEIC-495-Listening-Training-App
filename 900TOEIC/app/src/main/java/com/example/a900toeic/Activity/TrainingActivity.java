package com.example.a900toeic.Activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.example.a900toeic.Adapter.CategoryAdapter;
import com.example.a900toeic.Adapter.ReviewPartFourAdapter;
import com.example.a900toeic.Adapter.ReviewPartOneAdapter;
import com.example.a900toeic.Adapter.ReviewPartThreeAdapter;
import com.example.a900toeic.Adapter.ReviewPartTwoAdapter;
import com.example.a900toeic.Adapter.TrainingPartFourAdapter;
import com.example.a900toeic.Adapter.TrainingPartOneAdapter;
import com.example.a900toeic.Adapter.TrainingPartThreeAdapter;
import com.example.a900toeic.Adapter.TrainingPartTwoAdapter;
import com.example.a900toeic.Database.DBQuery;
import com.example.a900toeic.Model.Question;
import com.example.a900toeic.Model.QuestionPartOne;
import com.example.a900toeic.Model.QuestionPartThreeAndFour;
import com.example.a900toeic.Model.QuestionPartTwo;
import com.example.a900toeic.R;
import com.example.a900toeic.Utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.util.List;

public class TrainingActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private ViewPager2 mViewPager;
    private Toolbar toolbar;
    private TextView txt_toolbar_part, txt_toolbar_description;
    private MediaPlayer mediaPlayer;
    private ImageView btn_forward, btn_backward, btn_play, btn_bookmark;
    private LinearLayout bottomAudioBar;
    private SeekBar seek_bar;
    private TextView txt_timeline;
    private Runnable runnable;
    private Handler handler;
    private int partId;
    private static String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        addControls();
        loadData();

    }

    private void addControls() {
        progressBar = findViewById(R.id.progressBar);
        mViewPager = findViewById(R.id.viewpager_training);
        toolbar = findViewById(R.id.toolbar);
        btn_backward = findViewById(R.id.btn_backward);
        btn_forward = findViewById(R.id.btn_forward);
        btn_play = findViewById(R.id.btn_play);
        btn_bookmark = findViewById(R.id.btn_bookmark);
        seek_bar = findViewById(R.id.seek_bar);
        txt_timeline = findViewById(R.id.txt_timeline);
        txt_toolbar_part = findViewById(R.id.txt_toolbar_part);
        txt_toolbar_description = findViewById(R.id.txt_toolbar_description);
        bottomAudioBar = findViewById(R.id.bottom_audio_bar);
        handler = new Handler();
    }

    private void loadData() {
        partId = getIntent().getIntExtra("part", 1);
        switch (partId) {
            case 1:
                    DBQuery.loadTestNameList(new DBQuery.iTestNameCallback() {
                        @Override
                        public void onCallBack(List<String> res) {
                            DBQuery.loadDataPartOne(res, new DBQuery.iTrainingCallback<QuestionPartOne>() {
                                @Override
                                public void onCallBack(List<QuestionPartOne> res) {

                                    progressBar.setVisibility(View.GONE);
                                    TrainingPartOneAdapter mTrainingPartOneAdapter = new TrainingPartOneAdapter(TrainingActivity.this, res);
                                    mViewPager.setAdapter(mTrainingPartOneAdapter);
                                    txt_toolbar_part.setText("Part One Training");
                                    txt_toolbar_description.setText(CategoryAdapter.categories[0].getDescription());
                                    if (res.size() > 0) {
                                        playAudioFile(res,0);
                                        handleBookmarkButton(res,0);
                                    }
                                    addEvents(res);
                                }
                            });
                        }
                    });
                break;
            case 2:
                DBQuery.loadTestNameList(new DBQuery.iTestNameCallback() {
                    @Override
                    public void onCallBack(List<String> res) {
                        DBQuery.loadDataPartTwo(res, new DBQuery.iTrainingCallback<QuestionPartTwo>() {
                            @Override
                            public void onCallBack(List<QuestionPartTwo> res) {
                                progressBar.setVisibility(View.GONE);
                                TrainingPartTwoAdapter mTrainingPartTwoAdapter = new TrainingPartTwoAdapter(TrainingActivity.this, res);
                                mViewPager.setAdapter(mTrainingPartTwoAdapter);
                                txt_toolbar_part.setText("Part Two Training");
                                txt_toolbar_description.setText(CategoryAdapter.categories[1].getDescription());
                                if (res.size() > 0) {
                                    playAudioFile(res,0);
                                    handleBookmarkButton(res,0);
                                }
                                addEvents(res);
                            }
                        });
                    }
                });
                break;
            case 3:
                DBQuery.loadTestNameList(new DBQuery.iTestNameCallback() {
                    @Override
                    public void onCallBack(List<String> res) {
                        DBQuery.loadDataPartThree(res, new DBQuery.iTrainingCallback<QuestionPartThreeAndFour>() {
                            @Override
                            public void onCallBack(List<QuestionPartThreeAndFour> res) {

                                progressBar.setVisibility(View.GONE);
                                TrainingPartThreeAdapter mTrainingPartThreeAdapter = new TrainingPartThreeAdapter(TrainingActivity.this, res);
                                mViewPager.setAdapter(mTrainingPartThreeAdapter);
                                txt_toolbar_part.setText("Part Three Training");
                                txt_toolbar_description.setText(CategoryAdapter.categories[2].getDescription());
                                if (res.size() > 0) {
                                    playAudioFile(res,0);
                                    handleBookmarkButton(res,0);
                                }
                                addEvents(res);
                            }
                        });
                    }
                });
                break;
            case 4:
                DBQuery.loadTestNameList(new DBQuery.iTestNameCallback() {
                    @Override
                    public void onCallBack(List<String> res) {
                        DBQuery.loadDataPartFour(res, new DBQuery.iTrainingCallback<QuestionPartThreeAndFour>() {
                            @Override
                            public void onCallBack(List<QuestionPartThreeAndFour> res) {
                                progressBar.setVisibility(View.GONE);
                                TrainingPartFourAdapter mTrainingPartFourAdapter = new TrainingPartFourAdapter(TrainingActivity.this, res);
                                mViewPager.setAdapter(mTrainingPartFourAdapter);
                                txt_toolbar_part.setText("Part Four Training");
                                txt_toolbar_description.setText(CategoryAdapter.categories[3].getDescription());
                                if (res.size() > 0) {
                                    playAudioFile(res,0);
                                    handleBookmarkButton(res,0);
                                }
                                addEvents(res);
                            }
                        });
                    }
                });
            case 11:
                DBQuery.loadDataReviewPartOne(user_id, new DBQuery.iTrainingCallback<QuestionPartOne>() {
                    @Override
                    public void onCallBack(List<QuestionPartOne> res) {

                        progressBar.setVisibility(View.GONE);
                        ReviewPartOneAdapter mReviewPartOneAdapter = new ReviewPartOneAdapter(TrainingActivity.this,res);
                        mViewPager.setAdapter(mReviewPartOneAdapter);
                        txt_toolbar_part.setText("Part One Review");
                        txt_toolbar_description.setText(CategoryAdapter.categories[0].getDescription());
                        if (res.size() > 0) {
                            playAudioFile(res,0);
                            handleBookmarkButton(res,0);
                        }
                        addEvents(res);
                    }
                });

                break;
            case 12:
                DBQuery.loadDataReviewPartTwo(user_id, new DBQuery.iTrainingCallback<QuestionPartTwo>() {
                    @Override
                    public void onCallBack(List<QuestionPartTwo> res) {

                        progressBar.setVisibility(View.GONE);
                        ReviewPartTwoAdapter mReviewPartTwoAdapter = new ReviewPartTwoAdapter(TrainingActivity.this,res);
                        mViewPager.setAdapter(mReviewPartTwoAdapter);
                        txt_toolbar_part.setText("Part Two Review");
                        txt_toolbar_description.setText(CategoryAdapter.categories[1].getDescription());
                        if (res.size() > 0) {
                            playAudioFile(res,0);
                            handleBookmarkButton(res,0);
                        }
                        addEvents(res);
                    }
                });
                break;
            case 13:
                DBQuery.loadDataReviewPartThree(user_id, new DBQuery.iTrainingCallback<QuestionPartThreeAndFour>() {
                    @Override
                    public void onCallBack(List<QuestionPartThreeAndFour> res) {

                        progressBar.setVisibility(View.GONE);
                        ReviewPartThreeAdapter mReviewPartThreeAdapter = new ReviewPartThreeAdapter(TrainingActivity.this,res);
                        mViewPager.setAdapter(mReviewPartThreeAdapter);
                        txt_toolbar_part.setText("Part Three Review");
                        txt_toolbar_description.setText(CategoryAdapter.categories[2].getDescription());
                        if (res.size() > 0) {
                            playAudioFile(res,0);
                            handleBookmarkButton(res,0);
                        }
                        addEvents(res);
                    }
                });
                break;
            case 14:
                DBQuery.loadDataReviewPartThree(user_id, new DBQuery.iTrainingCallback<QuestionPartThreeAndFour>() {
                    @Override
                    public void onCallBack(List<QuestionPartThreeAndFour> res) {

                        progressBar.setVisibility(View.GONE);
                        ReviewPartFourAdapter mReviewPartFourAdapter = new ReviewPartFourAdapter(TrainingActivity.this,res);
                        mViewPager.setAdapter(mReviewPartFourAdapter);
                        txt_toolbar_part.setText("Part Three Review");
                        txt_toolbar_description.setText(CategoryAdapter.categories[3].getDescription());
                        if (res.size() > 0) {
                            playAudioFile(res,0);
                            handleBookmarkButton(res,0);
                        }
                        addEvents(res);
                    }
                });
                break;
        }
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(TrainingActivity.this, MainActivity.class));
        finish();
    }
    private <T extends Question>void addEvents(List<T> questionList) {

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer!=null)
                {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    seek_bar.setEnabled(false);
                }
                startActivity(new Intent(TrainingActivity.this, MainActivity.class));
                finish();
            }
        });
        Log.d("QuestionListSize", questionList.size()+"");
        if (questionList.size() > 0) {
            btn_forward.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()+3000);
                }
            });
            btn_backward.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()-3000);
                }
            });
            btn_play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mediaPlayer.isPlaying()) {
                        btn_play.setImageResource(R.drawable.ic_play_white);
                        mediaPlayer.pause();
                    } else {
                        btn_play.setImageResource(R.drawable.ic_pause);
                        mediaPlayer.start();
                    }
                }
            });

            seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    if (b) {
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
            mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    playAudioFile(questionList,position);
                    handleBookmarkButton(questionList,position);

                }
            });
        }else {
            bottomAudioBar.setVisibility(View.GONE);
        }

    }
    private <T extends Question> String loadPartNumberForBookmarking(List<T> questionList, int position)
    {
        if(partId>=1&&partId<=4)
        {
            String partNumber = "Part" + partId;
            DBQuery.db.collection("User").document(user_id).collection(partNumber)
                    .whereEqualTo("id", questionList.get(position).getId()).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                if (task.getResult().size() > 0) {

                                } else {
                                    btn_bookmark.setImageResource(R.drawable.ic_bookmark_white);
                                    btn_bookmark.setTag("bookmark");
                                }
                            }
                        }
                    });
            return partNumber;
        }else {
            btn_bookmark.setImageResource(R.drawable.ic_bookmarked_white);
            btn_bookmark.setTag("bookmarked");
            return "Part" + (partId-10);
        }
    }
    private <T extends Question>void handleBookmarkButton(List<T> questionList, int position) {


        String finalPartNumber = loadPartNumberForBookmarking(questionList,position);
        btn_bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_bookmark.getTag().equals("bookmarked")) {
                    btn_bookmark.setImageResource(R.drawable.ic_bookmark_white);
                    btn_bookmark.setTag("bookmark");
                    DBQuery.db.collection("User").document(user_id).collection(finalPartNumber)
                            .document(questionList.get(position).getId()).
                            delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(TrainingActivity.this, "Removed successfully",Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("Delete data", e.toString());
                        }
                    });
                } else {
                    btn_bookmark.setImageResource(R.drawable.ic_bookmarked_white);
                    btn_bookmark.setTag("bookmarked");
                    DBQuery.db.collection("User").document(user_id).collection(finalPartNumber)
                            .document(questionList.get(position).getId()).set(questionList.get(position));
                }
            }
        });
    }

    private <T extends Question> void playAudioFile(List<T> questionList,int position) {
        btn_play.setImageResource(R.drawable.ic_pause);
        if (mediaPlayer != null) {
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
                txt_timeline.setText(Utils.getTimeString(mediaPlayer.getDuration()));
                mediaPlayer.start();
                updateSeekBar();
            }
        });
        mediaPlayer.prepareAsync();
    }

    public void updateSeekBar() {
        try {
            int currPos = mediaPlayer.getCurrentPosition();
            seek_bar.setProgress(currPos);
            runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        if(mediaPlayer.isPlaying())
                        {
                            btn_play.setImageResource(R.drawable.ic_pause);
                        }else {
                            btn_play.setImageResource(R.drawable.ic_play_white);
                        }
                    }catch (IllegalStateException ex)
                    {

                    }

                    updateSeekBar();
                }
            };
            handler.postDelayed(runnable, 500);
        } catch (Exception e) {

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mediaPlayer!=null)
        {
            mediaPlayer.release();
        }
    }
}