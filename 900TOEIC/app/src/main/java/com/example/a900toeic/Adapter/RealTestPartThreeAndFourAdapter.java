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
import com.example.a900toeic.Model.QuestionPartThreeAndFour;
import com.example.a900toeic.R;
import com.example.a900toeic.Utils.Utils;

import java.io.IOException;
import java.util.List;

public class RealTestPartThreeAndFourAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<QuestionPartThreeAndFour> questionList;
    private MediaPlayer currentMediaPlayer;
    private List<Answer> answerList;
    public RealTestPartThreeAndFourAdapter(Context context, List<QuestionPartThreeAndFour> questionList,List<Answer> answerList ) {
        this.context = context;
        this.questionList = questionList;
        this.answerList = answerList;
    }

    @Override
    public int getItemViewType(int position) {
        if(answerList==null) return TEST_VIEW_TYPE;
        return RESULT_VIEW_TYPE;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == TEST_VIEW_TYPE)
        {
            View view = LayoutInflater.from(context).inflate(R.layout.item_part34_test,parent, false);
            return new TestViewHolder(view);
        }else if(viewType == RESULT_VIEW_TYPE)
        {
            View view = LayoutInflater.from(context).inflate(R.layout.item_part34_result,parent, false);
            return new ResultViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        QuestionPartThreeAndFour question = questionList.get(position);
        if(holder.getItemViewType()==RESULT_VIEW_TYPE)
        {
            ResultViewHolder resultViewHolder = (ResultViewHolder) holder;
            resultViewHolder.txt_question_number1.setText("Question No. " + question.getNumber1());
            resultViewHolder.txt_question1.setText(question.getQuestion1());
            resultViewHolder.txt_question_number2.setText("Question No. " + question.getNumber2());
            resultViewHolder.txt_question2.setText(question.getQuestion2());
            resultViewHolder.txt_question_number3.setText("Question No. " + question.getNumber3());
            resultViewHolder.txt_question3.setText(question.getQuestion3());
            resultViewHolder.rb_part34_key1A.setText(question.getScript_key1A());
            resultViewHolder.rb_part34_key1B.setText(question.getScript_key1B());
            resultViewHolder.rb_part34_key1C.setText(question.getScript_key1C());
            resultViewHolder.rb_part34_key1D.setText(question.getScript_key1D());
            resultViewHolder.rb_part34_key2A.setText(question.getScript_key2A());
            resultViewHolder.rb_part34_key2B.setText(question.getScript_key2B());
            resultViewHolder.rb_part34_key2C.setText(question.getScript_key2C());
            resultViewHolder.rb_part34_key2D.setText(question.getScript_key2D());
            resultViewHolder.rb_part34_key3A.setText(question.getScript_key3A());
            resultViewHolder.rb_part34_key3B.setText(question.getScript_key3B());
            resultViewHolder.rb_part34_key3C.setText(question.getScript_key3C());
            resultViewHolder.rb_part34_key3D.setText(question.getScript_key3D());
            String txt = question.getScript().replace("\\n","\n");
            resultViewHolder.txt_script.setText(txt);
            long number1 = questionList.get(position).getNumber1();
            long number2 = questionList.get(position).getNumber2();
            long number3 = questionList.get(position).getNumber3();
            Answer answer1 = answerList.get((int)(number1-1));
            Answer answer2 = answerList.get((int)(number2-1));
            Answer answer3 = answerList.get((int)(number3-1));

            if (!answer1.getKeyClick().equals(answer1.getCorrectKey())) {
                switch (answer1.getKeyClick()) {
                    case "A":
                        resultViewHolder.rb_part34_key1A.setChecked(true);
                        resultViewHolder.rb_part34_key1A.setButtonTintList(Utils.COLOR_WRONG_KEY_STATE_LIST);
                        resultViewHolder.rb_part34_key1A.setTextColor(Color.RED);
                        break;
                    case "B":
                        resultViewHolder.rb_part34_key1B.setChecked(true);
                        resultViewHolder.rb_part34_key1B.setButtonTintList(Utils.COLOR_WRONG_KEY_STATE_LIST);
                        resultViewHolder.rb_part34_key1B.setTextColor(Color.RED);
                        break;
                    case "C":
                        resultViewHolder.rb_part34_key1C.setChecked(true);
                        resultViewHolder.rb_part34_key1C.setButtonTintList(Utils.COLOR_WRONG_KEY_STATE_LIST);
                        resultViewHolder.rb_part34_key1C.setTextColor(Color.RED);
                        break;
                    case "D":
                        resultViewHolder.rb_part34_key1D.setChecked(true);
                        resultViewHolder.rb_part34_key1D.setButtonTintList(Utils.COLOR_WRONG_KEY_STATE_LIST);
                        resultViewHolder.rb_part34_key1D.setTextColor(Color.RED);
                        break;
                }
            }
            switch (answer1.getCorrectKey()) {
                case "A":
                    resultViewHolder.rb_part34_key1A.setButtonTintList(Utils.COLOR_RIGHT_KEY_STATE_LIST);
                    resultViewHolder.rb_part34_key1A.setTextColor(Color.GREEN);
                    break;
                case "B":
                    resultViewHolder.rb_part34_key1B.setButtonTintList(Utils.COLOR_RIGHT_KEY_STATE_LIST);
                    resultViewHolder.rb_part34_key1B.setTextColor(Color.GREEN);
                    break;
                case "C":
                    resultViewHolder.rb_part34_key1C.setButtonTintList(Utils.COLOR_RIGHT_KEY_STATE_LIST);
                    resultViewHolder.rb_part34_key1C.setTextColor(Color.GREEN);
                    break;
                case "D":
                    resultViewHolder.rb_part34_key1D.setButtonTintList(Utils.COLOR_RIGHT_KEY_STATE_LIST);
                    resultViewHolder.rb_part34_key1D.setTextColor(Color.GREEN);
                    break;
            }

            if (!answer2.getKeyClick().equals(answer2.getCorrectKey())) {
                switch (answer2.getKeyClick()) {
                    case "A":
                        resultViewHolder.rb_part34_key2A.setChecked(true);
                        resultViewHolder.rb_part34_key2A.setButtonTintList(Utils.COLOR_WRONG_KEY_STATE_LIST);
                        resultViewHolder.rb_part34_key2A.setTextColor(Color.RED);
                        break;
                    case "B":
                        resultViewHolder.rb_part34_key2B.setChecked(true);
                        resultViewHolder.rb_part34_key2B.setButtonTintList(Utils.COLOR_WRONG_KEY_STATE_LIST);
                        resultViewHolder.rb_part34_key2B.setTextColor(Color.RED);
                        break;
                    case "C":
                        resultViewHolder.rb_part34_key2C.setChecked(true);
                        resultViewHolder.rb_part34_key2C.setButtonTintList(Utils.COLOR_WRONG_KEY_STATE_LIST);
                        resultViewHolder.rb_part34_key2C.setTextColor(Color.RED);
                        break;
                    case "D":
                        resultViewHolder.rb_part34_key2D.setChecked(true);
                        resultViewHolder.rb_part34_key2D.setButtonTintList(Utils.COLOR_WRONG_KEY_STATE_LIST);
                        resultViewHolder.rb_part34_key2D.setTextColor(Color.RED);
                        break;
                }
            }
            switch (answer2.getCorrectKey()) {
                case "A":
                    resultViewHolder.rb_part34_key2A.setButtonTintList(Utils.COLOR_RIGHT_KEY_STATE_LIST);
                    resultViewHolder.rb_part34_key2A.setTextColor(Color.GREEN);
                    break;
                case "B":
                    resultViewHolder.rb_part34_key2B.setButtonTintList(Utils.COLOR_RIGHT_KEY_STATE_LIST);
                    resultViewHolder.rb_part34_key2B.setTextColor(Color.GREEN);
                    break;
                case "C":
                    resultViewHolder.rb_part34_key2C.setButtonTintList(Utils.COLOR_RIGHT_KEY_STATE_LIST);
                    resultViewHolder.rb_part34_key2C.setTextColor(Color.GREEN);
                    break;
                case "D":
                    resultViewHolder.rb_part34_key2D.setButtonTintList(Utils.COLOR_RIGHT_KEY_STATE_LIST);
                    resultViewHolder.rb_part34_key2D.setTextColor(Color.GREEN);
                    break;
            }

            if (!answer3.getKeyClick().equals(answer3.getCorrectKey())) {
                switch (answer3.getKeyClick()) {
                    case "A":
                        resultViewHolder.rb_part34_key3A.setChecked(true);
                        resultViewHolder.rb_part34_key3A.setButtonTintList(Utils.COLOR_WRONG_KEY_STATE_LIST);
                        resultViewHolder.rb_part34_key3A.setTextColor(Color.RED);
                        break;
                    case "B":
                        resultViewHolder.rb_part34_key3B.setChecked(true);
                        resultViewHolder.rb_part34_key3B.setButtonTintList(Utils.COLOR_WRONG_KEY_STATE_LIST);
                        resultViewHolder.rb_part34_key3B.setTextColor(Color.RED);
                        break;
                    case "C":
                        resultViewHolder.rb_part34_key3C.setChecked(true);
                        resultViewHolder.rb_part34_key3C.setButtonTintList(Utils.COLOR_WRONG_KEY_STATE_LIST);
                        resultViewHolder.rb_part34_key3C.setTextColor(Color.RED);
                        break;
                    case "D":
                        resultViewHolder.rb_part34_key3D.setChecked(true);
                        resultViewHolder.rb_part34_key3D.setButtonTintList(Utils.COLOR_WRONG_KEY_STATE_LIST);
                        resultViewHolder.rb_part34_key3D.setTextColor(Color.RED);
                        break;
                }
            }
            switch (answer3.getCorrectKey()) {
                case "A":
                    resultViewHolder.rb_part34_key3A.setButtonTintList(Utils.COLOR_RIGHT_KEY_STATE_LIST);
                    resultViewHolder.rb_part34_key3A.setTextColor(Color.GREEN);
                    break;
                case "B":
                    resultViewHolder.rb_part34_key3B.setButtonTintList(Utils.COLOR_RIGHT_KEY_STATE_LIST);
                    resultViewHolder.rb_part34_key3B.setTextColor(Color.GREEN);
                    break;
                case "C":
                    resultViewHolder.rb_part34_key3C.setButtonTintList(Utils.COLOR_RIGHT_KEY_STATE_LIST);
                    resultViewHolder.rb_part34_key3C.setTextColor(Color.GREEN);
                    break;
                case "D":
                    resultViewHolder.rb_part34_key3D.setButtonTintList(Utils.COLOR_RIGHT_KEY_STATE_LIST);
                    resultViewHolder.rb_part34_key3D.setTextColor(Color.GREEN);
                    break;
            }
            resultViewHolder.rb_part34_key1A.setClickable(false);
            resultViewHolder.rb_part34_key1B.setClickable(false);
            resultViewHolder.rb_part34_key1C.setClickable(false);
            resultViewHolder.rb_part34_key1D.setClickable(false);
            resultViewHolder.rb_part34_key2A.setClickable(false);
            resultViewHolder.rb_part34_key2B.setClickable(false);
            resultViewHolder.rb_part34_key2C.setClickable(false);
            resultViewHolder.rb_part34_key2D.setClickable(false);
            resultViewHolder.rb_part34_key3A.setClickable(false);
            resultViewHolder.rb_part34_key3B.setClickable(false);
            resultViewHolder.rb_part34_key3C.setClickable(false);
            resultViewHolder.rb_part34_key3D.setClickable(false);
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
        }else if(holder.getItemViewType() == TEST_VIEW_TYPE)
        {
            TestViewHolder testViewHolder = (TestViewHolder) holder;
            testViewHolder.txt_question_number1.setText("Question No. " + question.getNumber1());
            testViewHolder.txt_question1.setText(question.getQuestion1());
            testViewHolder.txt_question_number2.setText("Question No. " + question.getNumber2());
            testViewHolder.txt_question2.setText(question.getQuestion2());
            testViewHolder.txt_question_number3.setText("Question No. " + question.getNumber3());
            testViewHolder.txt_question3.setText(question.getQuestion3());
            testViewHolder.rb_part34_key1A.setText(question.getScript_key1A());
            testViewHolder.rb_part34_key1B.setText(question.getScript_key1B());
            testViewHolder.rb_part34_key1C.setText(question.getScript_key1C());
            testViewHolder.rb_part34_key1D.setText(question.getScript_key1D());
            testViewHolder.rb_part34_key2A.setText(question.getScript_key2A());
            testViewHolder.rb_part34_key2B.setText(question.getScript_key2B());
            testViewHolder.rb_part34_key2C.setText(question.getScript_key2C());
            testViewHolder.rb_part34_key2D.setText(question.getScript_key2D());
            testViewHolder.rb_part34_key3A.setText(question.getScript_key3A());
            testViewHolder.rb_part34_key3B.setText(question.getScript_key3B());
            testViewHolder.rb_part34_key3C.setText(question.getScript_key3C());
            testViewHolder.rb_part34_key3D.setText(question.getScript_key3D());
            long number1 = questionList.get(position).getNumber1();
            long number2 = questionList.get(position).getNumber2();
            long number3 = questionList.get(position).getNumber3();
            Answer answer1 = new Answer(number1, question.getKey1(), "");
            Answer answer2 = new Answer(number2, question.getKey2(), "");
            Answer answer3 = new Answer(number3, question.getKey3(), "");

            testViewHolder.rb_part34_key1A.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    answer1.setKeyClick("A");
                    DataLocalManager.addAnswer(answer1);
                    testViewHolder.rb_part34_key1B.setChecked(false);
                    testViewHolder.rb_part34_key1C.setChecked(false);
                    testViewHolder.rb_part34_key1D.setChecked(false);
                }
            });
            testViewHolder.rb_part34_key1B.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    answer1.setKeyClick("B");
                    DataLocalManager.addAnswer(answer1);
                    testViewHolder.rb_part34_key1A.setChecked(false);
                    testViewHolder.rb_part34_key1C.setChecked(false);
                    testViewHolder.rb_part34_key1D.setChecked(false);
                }
            });
            testViewHolder.rb_part34_key1C.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    answer1.setKeyClick("C");
                    DataLocalManager.addAnswer(answer1);
                    testViewHolder.rb_part34_key1A.setChecked(false);
                    testViewHolder.rb_part34_key1B.setChecked(false);
                    testViewHolder.rb_part34_key1D.setChecked(false);
                }
            });
            testViewHolder.rb_part34_key1D.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    answer1.setKeyClick("D");
                    DataLocalManager.addAnswer(answer1);
                    testViewHolder.rb_part34_key1A.setChecked(false);
                    testViewHolder.rb_part34_key1B.setChecked(false);
                    testViewHolder.rb_part34_key1C.setChecked(false);
                }
            });
            testViewHolder.rb_part34_key2A.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    answer2.setKeyClick("A");
                    DataLocalManager.addAnswer(answer2);
                    testViewHolder.rb_part34_key2B.setChecked(false);
                    testViewHolder.rb_part34_key2C.setChecked(false);
                    testViewHolder.rb_part34_key2D.setChecked(false);
                }
            });
            testViewHolder.rb_part34_key2B.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    answer2.setKeyClick("B");
                    DataLocalManager.addAnswer(answer2);
                    testViewHolder.rb_part34_key2A.setChecked(false);
                    testViewHolder.rb_part34_key2C.setChecked(false);
                    testViewHolder.rb_part34_key2D.setChecked(false);
                }
            });
           testViewHolder.rb_part34_key2C.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    answer2.setKeyClick("C");
                    DataLocalManager.addAnswer(answer2);
                    testViewHolder.rb_part34_key2A.setChecked(false);
                    testViewHolder.rb_part34_key2B.setChecked(false);
                    testViewHolder.rb_part34_key2D.setChecked(false);
                }
            });
            testViewHolder.rb_part34_key2D.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    answer2.setKeyClick("D");
                    DataLocalManager.addAnswer(answer2);
                    testViewHolder.rb_part34_key2A.setChecked(false);
                    testViewHolder.rb_part34_key2B.setChecked(false);
                    testViewHolder.rb_part34_key2C.setChecked(false);
                }
            });
            testViewHolder.rb_part34_key3A.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    answer3.setKeyClick("A");
                    DataLocalManager.addAnswer(answer3);
                    testViewHolder.rb_part34_key3B.setChecked(false);
                    testViewHolder.rb_part34_key3C.setChecked(false);
                    testViewHolder.rb_part34_key3D.setChecked(false);
                }
            });
            testViewHolder.rb_part34_key3B.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    answer3.setKeyClick("B");
                    DataLocalManager.addAnswer(answer3);
                    testViewHolder.rb_part34_key3A.setChecked(false);
                    testViewHolder.rb_part34_key3C.setChecked(false);
                    testViewHolder.rb_part34_key3D.setChecked(false);
                }
            });
           testViewHolder.rb_part34_key3C.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    answer3.setKeyClick("C");
                    DataLocalManager.addAnswer(answer3);
                    testViewHolder.rb_part34_key3A.setChecked(false);
                    testViewHolder.rb_part34_key3B.setChecked(false);
                    testViewHolder.rb_part34_key3D.setChecked(false);
                }
            });
            testViewHolder.rb_part34_key3D.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    answer3.setKeyClick("D");
                    DataLocalManager.addAnswer(answer3);
                    testViewHolder.rb_part34_key3A.setChecked(false);
                    testViewHolder.rb_part34_key3B.setChecked(false);
                    testViewHolder.rb_part34_key3C.setChecked(false);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_question_number1,txt_question_number2,txt_question_number3;
        private TextView txt_question1,txt_question2,txt_question3;
        private RadioButton rb_part34_key1A,rb_part34_key1B,rb_part34_key1C, rb_part34_key1D;
        private RadioButton rb_part34_key2A,rb_part34_key2B,rb_part34_key2C, rb_part34_key2D;
        private RadioButton rb_part34_key3A,rb_part34_key3B,rb_part34_key3C, rb_part34_key3D;

        private TextView txt_script, txt_timeline;
        private ImageView btn_backward, btn_forward, btn_play, btn_bookmark;
        private MediaPlayer mediaPlayer;

        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);
            addControls(itemView);
        }
        private void addControls(View itemView) {
            txt_question_number1 = itemView.findViewById(R.id.txt_question_number1);
            txt_question_number2 = itemView.findViewById(R.id.txt_question_number2);
            txt_question_number3 = itemView.findViewById(R.id.txt_question_number3);
            txt_question1 = itemView.findViewById(R.id.txt_question1);
            txt_question2 = itemView.findViewById(R.id.txt_question2);
            txt_question3 = itemView.findViewById(R.id.txt_question3);
            rb_part34_key1A = itemView.findViewById(R.id.rb_part34_key1A);
            rb_part34_key1B = itemView.findViewById(R.id.rb_part34_key1B);
            rb_part34_key1C = itemView.findViewById(R.id.rb_part34_key1C);
            rb_part34_key1D = itemView.findViewById(R.id.rb_part34_key1D);
            rb_part34_key2A = itemView.findViewById(R.id.rb_part34_key2A);
            rb_part34_key2B = itemView.findViewById(R.id.rb_part34_key2B);
            rb_part34_key2C = itemView.findViewById(R.id.rb_part34_key2C);
            rb_part34_key2D = itemView.findViewById(R.id.rb_part34_key2D);
            rb_part34_key3A = itemView.findViewById(R.id.rb_part34_key3A);
            rb_part34_key3B = itemView.findViewById(R.id.rb_part34_key3B);
            rb_part34_key3C = itemView.findViewById(R.id.rb_part34_key3C);
            rb_part34_key3D = itemView.findViewById(R.id.rb_part34_key3D);

            txt_timeline = itemView.findViewById(R.id.txt_timeline);
            txt_script = itemView.findViewById(R.id.txt_script);
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
        private TextView txt_question_number1,txt_question_number2,txt_question_number3;
        private TextView txt_question1,txt_question2,txt_question3;
        private RadioButton rb_part34_key1A,rb_part34_key1B,rb_part34_key1C, rb_part34_key1D;
        private RadioButton rb_part34_key2A,rb_part34_key2B,rb_part34_key2C, rb_part34_key2D;
        private RadioButton rb_part34_key3A,rb_part34_key3B,rb_part34_key3C, rb_part34_key3D;
        public TestViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_question_number1 = itemView.findViewById(R.id.txt_question_number1);
            txt_question_number2 = itemView.findViewById(R.id.txt_question_number2);
            txt_question_number3 = itemView.findViewById(R.id.txt_question_number3);
            txt_question1 = itemView.findViewById(R.id.txt_question1);
            txt_question2 = itemView.findViewById(R.id.txt_question2);
            txt_question3 = itemView.findViewById(R.id.txt_question3);
            rb_part34_key1A = itemView.findViewById(R.id.rb_part34_key1A);
            rb_part34_key1B = itemView.findViewById(R.id.rb_part34_key1B);
            rb_part34_key1C = itemView.findViewById(R.id.rb_part34_key1C);
            rb_part34_key1D = itemView.findViewById(R.id.rb_part34_key1D);
            rb_part34_key2A = itemView.findViewById(R.id.rb_part34_key2A);
            rb_part34_key2B = itemView.findViewById(R.id.rb_part34_key2B);
            rb_part34_key2C = itemView.findViewById(R.id.rb_part34_key2C);
            rb_part34_key2D = itemView.findViewById(R.id.rb_part34_key2D);
            rb_part34_key3A = itemView.findViewById(R.id.rb_part34_key3A);
            rb_part34_key3B = itemView.findViewById(R.id.rb_part34_key3B);
            rb_part34_key3C = itemView.findViewById(R.id.rb_part34_key3C);
            rb_part34_key3D = itemView.findViewById(R.id.rb_part34_key3D);
        }
    }
}
