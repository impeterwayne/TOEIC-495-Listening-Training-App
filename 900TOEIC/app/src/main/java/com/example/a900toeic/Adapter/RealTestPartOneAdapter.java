package com.example.a900toeic.Adapter;

import static com.example.a900toeic.Utils.Utils.RESULT_VIEW_TYPE;
import static com.example.a900toeic.Utils.Utils.TEST_VIEW_TYPE;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a900toeic.LocalData.DataLocalManager;
import com.example.a900toeic.Model.Answer;
import com.example.a900toeic.Model.QuestionPartOne;
import com.example.a900toeic.R;
import com.example.a900toeic.Utils.Utils;

import java.io.IOException;
import java.util.List;

public class RealTestPartOneAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<QuestionPartOne> questionList;
    private List<Answer> answerList;
    private MediaPlayer currentMediaPlayer;
    private Runnable runnable;
    private Handler handler;

    public RealTestPartOneAdapter(Context context, List<QuestionPartOne> questionList , List<Answer> answerList) {
        this.context = context;
        this.questionList = questionList;
        this.answerList = answerList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == RESULT_VIEW_TYPE) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_part1_result, parent, false);
            return new ResultViewHolder(view);
        } else if (viewType == TEST_VIEW_TYPE) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_part1_test, parent, false);
            return new TestViewHolder(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        if(answerList==null) return TEST_VIEW_TYPE;
        return RESULT_VIEW_TYPE;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        QuestionPartOne question = questionList.get(position);
        if (holder.getItemViewType() == RESULT_VIEW_TYPE) {
            ResultViewHolder resultViewHolder = (ResultViewHolder) holder;
            resultViewHolder.txt_question_number.setText("Question No. " + question.getNumber());
            Glide.with(context).load(question.getImage_url()).fitCenter().into(resultViewHolder.img_part1_photo);
            resultViewHolder.txt_scriptA.setText(question.getScript_keyA());
            resultViewHolder.txt_scriptB.setText(question.getScript_keyB());
            resultViewHolder.txt_scriptC.setText(question.getScript_keyC());
            resultViewHolder.txt_scriptD.setText(question.getScript_keyD());

            long number = questionList.get(position).getNumber();
            Answer answer = answerList.get((int) number - 1);
            if (!answer.getKeyClick().equals(answer.getCorrectKey())) {

                switch (answer.getKeyClick()) {
                    case "A":
                        resultViewHolder.rb_part1_keyA.setChecked(true);
                        resultViewHolder.rb_part1_keyA.setButtonTintList(Utils.COLOR_WRONG_KEY_STATE_LIST);
                        resultViewHolder.txt_scriptA.setTextColor(Color.RED);
                        break;
                    case "B":
                        resultViewHolder.rb_part1_keyB.setChecked(true);
                        resultViewHolder.rb_part1_keyB.setButtonTintList(Utils.COLOR_WRONG_KEY_STATE_LIST);
                        resultViewHolder.txt_scriptB.setTextColor(Color.RED);
                        break;
                    case "C":
                        resultViewHolder.rb_part1_keyC.setChecked(true);
                        resultViewHolder.rb_part1_keyC.setButtonTintList(Utils.COLOR_WRONG_KEY_STATE_LIST);
                        resultViewHolder.txt_scriptC.setTextColor(Color.RED);
                        break;
                    case "D":
                        resultViewHolder.rb_part1_keyD.setChecked(true);
                        resultViewHolder.rb_part1_keyD.setButtonTintList(Utils.COLOR_WRONG_KEY_STATE_LIST);
                        resultViewHolder.txt_scriptD.setTextColor(Color.RED);
                        break;

                }
            }else {
                switch (answer.getKeyClick())
                {
                    case "A":
                        resultViewHolder.rb_part1_keyA.setChecked(true);
                        break;
                    case "B":
                        resultViewHolder.rb_part1_keyB.setChecked(true);
                        break;
                    case "C":
                        resultViewHolder.rb_part1_keyC.setChecked(true);
                        break;
                    case "D":
                        resultViewHolder.rb_part1_keyD.setChecked(true);
                        break;
                }
            }
            switch (answer.getCorrectKey()) {
                case "A":
                    resultViewHolder.rb_part1_keyA.setButtonTintList(Utils.COLOR_RIGHT_KEY_STATE_LIST);
                    resultViewHolder.txt_scriptA.setTextColor(Color.GREEN);
                    break;
                case "B":
                    resultViewHolder.rb_part1_keyB.setButtonTintList(Utils.COLOR_RIGHT_KEY_STATE_LIST);
                    resultViewHolder.txt_scriptB.setTextColor(Color.GREEN);
                    break;
                case "C":
                    resultViewHolder.rb_part1_keyC.setButtonTintList(Utils.COLOR_RIGHT_KEY_STATE_LIST);
                    resultViewHolder.txt_scriptC.setTextColor(Color.GREEN);
                    break;
                case "D":
                    resultViewHolder.rb_part1_keyD.setButtonTintList(Utils.COLOR_RIGHT_KEY_STATE_LIST);
                    resultViewHolder.txt_scriptD.setTextColor(Color.GREEN);
                    break;
            }
            resultViewHolder.rb_part1_keyA.setClickable(false);
            resultViewHolder.rb_part1_keyB.setClickable(false);
            resultViewHolder.rb_part1_keyC.setClickable(false);
            resultViewHolder.rb_part1_keyD.setClickable(false);

            resultViewHolder.btn_forward.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currentMediaPlayer.seekTo(currentMediaPlayer.getCurrentPosition() + 3000);
                }
            });
            resultViewHolder.btn_backward.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currentMediaPlayer.seekTo(currentMediaPlayer.getCurrentPosition() - 3000);
                }
            });
            resultViewHolder.btn_play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (resultViewHolder.mediaPlayer == currentMediaPlayer && currentMediaPlayer != null) {
                        if (currentMediaPlayer.isPlaying()) {
                            resultViewHolder.btn_play.setImageResource(R.drawable.ic_play_black);
                            currentMediaPlayer.pause();
                        } else {
                            resultViewHolder.btn_play.setImageResource(R.drawable.ic_pause_black);
                            currentMediaPlayer.start();
                        }
                    } else {
                        resultViewHolder.playAudioFile(position);
                    }

                }
            });
        } else if (holder.getItemViewType() == TEST_VIEW_TYPE) {
            TestViewHolder testViewHolder = (TestViewHolder) holder;
            testViewHolder.txt_question_number.setText("Question No. " + question.getNumber());
            Glide.with(context).load(question.getImage_url()).fitCenter().into(testViewHolder.img_part1_photo);
            long number = questionList.get(position).getNumber();
            Answer answer = new Answer(number, question.getKey(), "");
            testViewHolder.rb_part1_keyA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    answer.setKeyClick("A");
                    DataLocalManager.addAnswer(answer);
                    testViewHolder.rb_part1_keyB.setChecked(false);
                    testViewHolder.rb_part1_keyC.setChecked(false);
                    testViewHolder.rb_part1_keyD.setChecked(false);
                }
            });
            testViewHolder.rb_part1_keyB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    answer.setKeyClick("B");
                    DataLocalManager.addAnswer(answer);
                    testViewHolder.rb_part1_keyA.setChecked(false);
                    testViewHolder.rb_part1_keyC.setChecked(false);
                    testViewHolder.rb_part1_keyD.setChecked(false);
                }
            });
            testViewHolder.rb_part1_keyC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    answer.setKeyClick("C");
                    DataLocalManager.addAnswer(answer);
                    testViewHolder.rb_part1_keyA.setChecked(false);
                    testViewHolder.rb_part1_keyB.setChecked(false);
                    testViewHolder.rb_part1_keyD.setChecked(false);
                }
            });
            testViewHolder.rb_part1_keyD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    answer.setKeyClick("D");
                    DataLocalManager.addAnswer(answer);
                    testViewHolder.rb_part1_keyA.setChecked(false);
                    testViewHolder.rb_part1_keyB.setChecked(false);
                    testViewHolder.rb_part1_keyC.setChecked(false);
                }
            });

        }
    }
    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_question_number;
        private ImageView img_part1_photo;
        private RadioButton rb_part1_keyA, rb_part1_keyB, rb_part1_keyC, rb_part1_keyD;
        private TextView txt_scriptA, txt_scriptB, txt_scriptC, txt_scriptD, txt_timeline;
        private ImageView btn_backward, btn_forward, btn_play, btn_bookmark;
        private MediaPlayer mediaPlayer;

        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_question_number = itemView.findViewById(R.id.txt_question_number);
            img_part1_photo = itemView.findViewById(R.id.img_part1_photo);
            rb_part1_keyA = itemView.findViewById(R.id.rb_part1_keyA);
            rb_part1_keyB = itemView.findViewById(R.id.rb_part1_keyB);
            rb_part1_keyC = itemView.findViewById(R.id.rb_part1_keyC);
            rb_part1_keyD = itemView.findViewById(R.id.rb_part1_keyD);

            txt_scriptA = itemView.findViewById(R.id.txt_scriptA);
            txt_scriptB = itemView.findViewById(R.id.txt_scriptB);
            txt_scriptC = itemView.findViewById(R.id.txt_scriptC);
            txt_scriptD = itemView.findViewById(R.id.txt_scriptD);
            txt_timeline = itemView.findViewById(R.id.txt_timeline);
            btn_backward = itemView.findViewById(R.id.btn_backward);
            btn_forward = itemView.findViewById(R.id.btn_forward);
            btn_play = itemView.findViewById(R.id.btn_play);
            btn_bookmark = itemView.findViewById(R.id.btn_bookmark);
        }

        private void playAudioFile(int position) {
            btn_play.setImageResource(R.drawable.ic_pause_black);
            if (currentMediaPlayer != null) {
                currentMediaPlayer.stop();
                currentMediaPlayer.release();
            }
            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(questionList.get(position).getAudio_url());
                currentMediaPlayer = mediaPlayer;
            } catch (IOException e) {
                e.printStackTrace();
            }
            currentMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    txt_timeline.setText(Utils.getTimeString(mediaPlayer.getDuration()));
                    mediaPlayer.start();
                }
            });
            currentMediaPlayer.prepareAsync();
        }

    }

    public class TestViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_question_number;
        private ImageView img_part1_photo;
        private RadioButton rb_part1_keyA, rb_part1_keyB, rb_part1_keyC, rb_part1_keyD;

        public TestViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_question_number = itemView.findViewById(R.id.txt_question_number);
            img_part1_photo = itemView.findViewById(R.id.img_part1_photo);
            rb_part1_keyA = itemView.findViewById(R.id.rb_part1_keyA);
            rb_part1_keyB = itemView.findViewById(R.id.rb_part1_keyB);
            rb_part1_keyC = itemView.findViewById(R.id.rb_part1_keyC);
            rb_part1_keyD = itemView.findViewById(R.id.rb_part1_keyD);
        }

    }
}
