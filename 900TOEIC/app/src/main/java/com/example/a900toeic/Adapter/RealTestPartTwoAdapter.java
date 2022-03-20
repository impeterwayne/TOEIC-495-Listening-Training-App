package com.example.a900toeic.Adapter;

import static com.example.a900toeic.Utils.Utils.RESULT_VIEW_TYPE;
import static com.example.a900toeic.Utils.Utils.TEST_VIEW_TYPE;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a900toeic.LocalData.DataLocalManager;
import com.example.a900toeic.Model.Answer;
import com.example.a900toeic.Model.QuestionPartTwo;
import com.example.a900toeic.R;
import com.example.a900toeic.Utils.Utils;

import java.io.IOException;
import java.util.List;

public class RealTestPartTwoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<QuestionPartTwo> questionList;
    private List<Answer> answerList;
    private MediaPlayer currentMediaPlayer;
    public RealTestPartTwoAdapter(Context context, List<QuestionPartTwo> questionList,List<Answer> answerList) {
        this.context = context;
        this.questionList = questionList;
        this.answerList = answerList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == TEST_VIEW_TYPE)
        {
            View view = LayoutInflater.from(context).inflate(R.layout.item_part2_test, parent, false);
            return new TestViewHolder(view);
        }else if(viewType == RESULT_VIEW_TYPE)
        {
            View view = LayoutInflater.from(context).inflate(R.layout.item_part2_result, parent, false);
            return new ResultViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        QuestionPartTwo question = questionList.get(position);
        if(holder.getItemViewType() == TEST_VIEW_TYPE)
        {
            TestViewHolder testViewHolder = (TestViewHolder) holder;
            testViewHolder.txt_question_number.setText("Question No. " + question.getNumber());
            long number = questionList.get(position).getNumber();
            Answer answer = new Answer(number, question.getKey(),"");
            testViewHolder.rb_part2_keyA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    answer.setKeyClick("A");
                    DataLocalManager.addAnswer(answer);
                    testViewHolder.rb_part2_keyB.setChecked(false);
                    testViewHolder.rb_part2_keyC.setChecked(false);
                }
            });
            testViewHolder.rb_part2_keyB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    answer.setKeyClick("B");
                    DataLocalManager.addAnswer(answer);
                    testViewHolder.rb_part2_keyA.setChecked(false);
                    testViewHolder.rb_part2_keyC.setChecked(false);
                }
            });
            testViewHolder.rb_part2_keyC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    answer.setKeyClick("C");
                    DataLocalManager.addAnswer(answer);
                    testViewHolder.rb_part2_keyA.setChecked(false);
                    testViewHolder.rb_part2_keyB.setChecked(false);
                }
            });

        }else if (holder.getItemViewType() == RESULT_VIEW_TYPE)
        {
            ResultViewHolder resultViewHolder = (ResultViewHolder) holder;
            resultViewHolder.txt_question_number.setText("Question No. " + question.getNumber());
            resultViewHolder.txt_script.setText(question.getScript());
            resultViewHolder.txt_scriptA.setText(question.getScript_keyA());
            resultViewHolder.txt_scriptB.setText(question.getScript_keyB());
            resultViewHolder.txt_scriptC.setText(question.getScript_keyC());
            long number = questionList.get(position).getNumber();
            Answer answer = answerList.get((int) (number - 1));
            Log.d("answer num"+answer.getAnswerNum(), position+"");
            if (!answer.getKeyClick().equals(answer.getCorrectKey())) {
                switch (answer.getKeyClick()) {
                    case "A":
                        resultViewHolder.rb_part2_keyA.setChecked(true);
                        resultViewHolder.rb_part2_keyA.setButtonTintList(Utils.COLOR_WRONG_KEY_STATE_LIST);
                        resultViewHolder.txt_scriptA.setTextColor(Color.RED);
                        break;
                    case "B":
                        resultViewHolder.rb_part2_keyB.setChecked(true);
                        resultViewHolder.rb_part2_keyB.setButtonTintList(Utils.COLOR_WRONG_KEY_STATE_LIST);
                        resultViewHolder.txt_scriptB.setTextColor(Color.RED);
                        break;
                    case "C":
                        resultViewHolder.rb_part2_keyC.setChecked(true);
                        resultViewHolder.rb_part2_keyC.setButtonTintList(Utils.COLOR_WRONG_KEY_STATE_LIST);
                        resultViewHolder.txt_scriptC.setTextColor(Color.RED);
                        break;
                }
            }
            switch (answer.getCorrectKey()) {
                case "A":
                    resultViewHolder.rb_part2_keyA.setButtonTintList(Utils.COLOR_RIGHT_KEY_STATE_LIST);
                    resultViewHolder.txt_scriptA.setTextColor(Color.GREEN);
                    break;
                case "B":
                    resultViewHolder.rb_part2_keyB.setButtonTintList(Utils.COLOR_RIGHT_KEY_STATE_LIST);
                    resultViewHolder.txt_scriptB.setTextColor(Color.GREEN);
                    break;
                case "C":
                    resultViewHolder.rb_part2_keyC.setButtonTintList(Utils.COLOR_RIGHT_KEY_STATE_LIST);
                    resultViewHolder.txt_scriptC.setTextColor(Color.GREEN);
                    break;

            }
            resultViewHolder.rb_part2_keyA.setClickable(false);
            resultViewHolder.rb_part2_keyB.setClickable(false);
            resultViewHolder.rb_part2_keyC.setClickable(false);

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
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(answerList==null) return TEST_VIEW_TYPE;
        return RESULT_VIEW_TYPE;
    }


    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_question_number;
        private RadioButton rb_part2_keyA,rb_part2_keyB,rb_part2_keyC;

        private TextView txt_script, txt_scriptA, txt_scriptB, txt_scriptC, txt_timeline;
        private ImageView btn_backward, btn_forward, btn_play, btn_bookmark;
        private MediaPlayer mediaPlayer;
        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_question_number = itemView.findViewById(R.id.txt_question_number);
            rb_part2_keyA = itemView.findViewById(R.id.rb_part2_keyA);
            rb_part2_keyB = itemView.findViewById(R.id.rb_part2_keyB);
            rb_part2_keyC = itemView.findViewById(R.id.rb_part2_keyC);

            txt_script = itemView.findViewById(R.id.txt_script);
            txt_scriptA = itemView.findViewById(R.id.txt_scriptA);
            txt_scriptB = itemView.findViewById(R.id.txt_scriptB);
            txt_scriptC = itemView.findViewById(R.id.txt_scriptC);
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
        private RadioButton rb_part2_keyA,rb_part2_keyB,rb_part2_keyC;
        public TestViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_question_number = itemView.findViewById(R.id.txt_question_number);
            rb_part2_keyA = itemView.findViewById(R.id.rb_part2_keyA);
            rb_part2_keyB = itemView.findViewById(R.id.rb_part2_keyB);
            rb_part2_keyC = itemView.findViewById(R.id.rb_part2_keyC);
        }
    }
}
