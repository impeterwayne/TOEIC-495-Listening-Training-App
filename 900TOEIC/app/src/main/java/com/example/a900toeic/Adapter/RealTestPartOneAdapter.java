package com.example.a900toeic.Adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a900toeic.LocalData.DataLocalManager;
import com.example.a900toeic.Model.Answer;
import com.example.a900toeic.Model.Question;
import com.example.a900toeic.Model.QuestionPartOne;
import com.example.a900toeic.R;
import com.example.a900toeic.Utils.Utils;

import java.io.IOException;
import java.util.List;

public class RealTestPartOneAdapter extends RecyclerView.Adapter<RealTestPartOneAdapter.ViewHolder> {
    private Context context;
    private List<QuestionPartOne> questionList;
    private MediaPlayer mediaPlayer;
    private Runnable runnable;
    private Handler handler;

    public RealTestPartOneAdapter(Context context, List<QuestionPartOne> questionList) {
        this.context = context;
        this.questionList = questionList;
    }

    @NonNull
    @Override
    public RealTestPartOneAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_part1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RealTestPartOneAdapter.ViewHolder holder, int position) {
        QuestionPartOne question = questionList.get(position);
        holder.txt_question_number.setText("Question No. " + question.getNumber());
        Glide.with(context).load(question.getImage_url()).fitCenter().into(holder.img_part1_photo);
        long number = questionList.get(position).getNumber();
        Answer answer = new Answer(number, question.getKey(), "");
        holder.rb_part1_keyA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataLocalManager.addAnswer(answer);
                holder.rb_part1_keyB.setChecked(false);
                holder.rb_part1_keyC.setChecked(false);
                holder.rb_part1_keyD.setChecked(false);
            }
        });
        holder.rb_part1_keyB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer.setKeyClick("B");
                DataLocalManager.addAnswer(answer);
                holder.rb_part1_keyA.setChecked(false);
                holder.rb_part1_keyC.setChecked(false);
                holder.rb_part1_keyD.setChecked(false);
            }
        });
        holder.rb_part1_keyC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer.setKeyClick("C");
                DataLocalManager.addAnswer(answer);
                holder.rb_part1_keyA.setChecked(false);
                holder.rb_part1_keyB.setChecked(false);
                holder.rb_part1_keyD.setChecked(false);
            }
        });
        holder.rb_part1_keyD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer.setKeyClick("D");
                DataLocalManager.addAnswer(answer);
                holder.rb_part1_keyA.setChecked(false);
                holder.rb_part1_keyB.setChecked(false);
                holder.rb_part1_keyC.setChecked(false);
            }
        });
        holder.txt_scriptA.setText(question.getScript_keyA());
        holder.txt_scriptB.setText(question.getScript_keyB());
        holder.txt_scriptC.setText(question.getScript_keyC());
        holder.txt_scriptD.setText(question.getScript_keyD());

        holder.seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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
        holder.btn_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 3000);
            }
        });
        holder.btn_backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 3000);
            }
        });
        holder.btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()) {
                    holder.btn_play.setImageResource(R.drawable.ic_play_white);
                    mediaPlayer.pause();
                } else {
                    holder.btn_play.setImageResource(R.drawable.ic_pause);
                    mediaPlayer.start();
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_question_number;
        private ImageView img_part1_photo;
        private RadioButton rb_part1_keyA, rb_part1_keyB, rb_part1_keyC, rb_part1_keyD;
        private LinearLayout resultView;
        private TextView txt_scriptA, txt_scriptB, txt_scriptC, txt_scriptD, txt_timeline;
        private ImageView btn_backward, btn_forward, btn_play, btn_bookmark;
        private SeekBar seek_bar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_question_number = itemView.findViewById(R.id.txt_question_number);
            img_part1_photo = itemView.findViewById(R.id.img_part1_photo);
            rb_part1_keyA = itemView.findViewById(R.id.rb_part1_keyA);
            rb_part1_keyB = itemView.findViewById(R.id.rb_part1_keyB);
            rb_part1_keyC = itemView.findViewById(R.id.rb_part1_keyC);
            rb_part1_keyD = itemView.findViewById(R.id.rb_part1_keyD);

            resultView = itemView.findViewById(R.id.resultView);
            txt_scriptA = itemView.findViewById(R.id.txt_scriptA);
            txt_scriptB = itemView.findViewById(R.id.txt_scriptB);
            txt_scriptC = itemView.findViewById(R.id.txt_scriptC);
            txt_scriptD = itemView.findViewById(R.id.txt_scriptD);
            txt_timeline = itemView.findViewById(R.id.txt_timeline);
            btn_backward = itemView.findViewById(R.id.btn_backward);
            btn_forward = itemView.findViewById(R.id.btn_forward);
            btn_play = itemView.findViewById(R.id.btn_play);
            btn_bookmark = itemView.findViewById(R.id.btn_bookmark);
            seek_bar = itemView.findViewById(R.id.seek_bar);
        }

        private void playAudioFile(int position) {
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
                            if (mediaPlayer.isPlaying()) {
                                btn_play.setImageResource(R.drawable.ic_pause);
                            } else {
                                btn_play.setImageResource(R.drawable.ic_play_white);
                            }
                        } catch (IllegalStateException ex) {

                        }
                        updateSeekBar();
                    }
                };
                handler.postDelayed(runnable, 500);
            } catch (Exception e) {

            }
        }
    }
}
