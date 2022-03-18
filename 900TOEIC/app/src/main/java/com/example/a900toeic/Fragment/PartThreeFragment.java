package com.example.a900toeic.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.a900toeic.Database.DBQuery;
import com.example.a900toeic.LocalData.DataLocalManager;
import com.example.a900toeic.Model.QuestionPartThreeAndFour;
import com.example.a900toeic.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;


public class PartThreeFragment extends Fragment {
    private AppCompatButton btn_key1A, btn_key1B, btn_key1C, btn_key1D,
                            btn_key2A,btn_key2B,btn_key2C,btn_key2D,
                            btn_key3A,btn_key3B,btn_key3C,btn_key3D;
    private AppCompatButton btn_show_script;
    private TextView txt_question1, txt_question2, txt_question3;
    private QuestionPartThreeAndFour data;
    private int countAnsweredQuestions = 0 ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_part_three, container, false);
        addControls(view);
        addEvents();
        return view;
    }

    private void addEvents() {
        btn_show_script.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(countAnsweredQuestions<3)
                {
                    Toast.makeText(getContext(), "Please answer all questions before showing script!",Toast.LENGTH_SHORT).show();
                }else {
                        clickOpenBottomSheetDialog();
                }
            }
        });
        btn_key1A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                processAnswer(btn_key1A,1);
            }
        });
        btn_key1B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processAnswer(btn_key1B,1);
            }
        });
        btn_key1C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processAnswer(btn_key1C,1);
            }
        });
        btn_key1D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                processAnswer(btn_key1D,1);
            }
        });
        btn_key2A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                processAnswer(btn_key2A,2);
            }
        });
        btn_key2B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                processAnswer(btn_key2B,2);
            }
        });
        btn_key2C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                processAnswer(btn_key2C,2);
            }
        });
        btn_key2D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                processAnswer(btn_key2D,2);
            }
        });
        btn_key3A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                processAnswer(btn_key3A,3);
            }
        });
        btn_key3B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                processAnswer(btn_key3B,3);
            }
        });
        btn_key3C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                processAnswer(btn_key3C,3);
            }
        });
        btn_key3D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                processAnswer(btn_key3D,3);
            }
        });
    }

    private void clickOpenBottomSheetDialog() {
        View viewDialog = getLayoutInflater().inflate(R.layout.bottomsheet_script,null);
        BottomSheetDialog dialog = new BottomSheetDialog(getContext());
        dialog.setContentView(viewDialog);
        TextView txt_script_dialog = dialog.findViewById(R.id.txt_script_dialog);
        String txt = data.getScript().replace("\\n","\n");
        txt_script_dialog.setText(txt);
        dialog.show();
    }

    public void processAnswer(AppCompatButton btnKeyClick, int questionNum)
    {
        if(questionNum==1)
        {
            if(btnKeyClick.getTag().equals(data.getKey1()))
                btnKeyClick.setBackgroundResource(R.drawable.bg_right_answer);
            else
            {
                btnKeyClick.setBackgroundResource(R.drawable.bg_wrong_answer);
                if(btn_key1A.getTag().equals(data.getKey1())) btn_key1A.setBackgroundResource(R.drawable.bg_right_answer);
                if(btn_key1B.getTag().equals(data.getKey1())) btn_key1B.setBackgroundResource(R.drawable.bg_right_answer);
                if(btn_key1C.getTag().equals(data.getKey1())) btn_key1C.setBackgroundResource(R.drawable.bg_right_answer);
                if(btn_key1D.getTag().equals(data.getKey1())) btn_key1D.setBackgroundResource(R.drawable.bg_right_answer);
            }

            btn_key1A.setClickable(false);
            btn_key1B.setClickable(false);
            btn_key1C.setClickable(false);
            btn_key1D.setClickable(false);
            countAnsweredQuestions++;
        }else if(questionNum==2)
        {
            if(btnKeyClick.getTag().equals(data.getKey2()))
                btnKeyClick.setBackgroundResource(R.drawable.bg_right_answer);
            else
            {
                btnKeyClick.setBackgroundResource(R.drawable.bg_wrong_answer);
                if(btn_key2A.getTag().equals(data.getKey1())) btn_key2A.setBackgroundResource(R.drawable.bg_right_answer);
                if(btn_key2B.getTag().equals(data.getKey1())) btn_key2B.setBackgroundResource(R.drawable.bg_right_answer);
                if(btn_key2C.getTag().equals(data.getKey1())) btn_key2C.setBackgroundResource(R.drawable.bg_right_answer);
                if(btn_key2D.getTag().equals(data.getKey1())) btn_key2D.setBackgroundResource(R.drawable.bg_right_answer);
            }

            btn_key2A.setClickable(false);
            btn_key2B.setClickable(false);
            btn_key2C.setClickable(false);
            btn_key2D.setClickable(false);
            countAnsweredQuestions++;
        }
        else {
            if(btnKeyClick.getTag().equals(data.getKey3()))
                btnKeyClick.setBackgroundResource(R.drawable.bg_right_answer);
            else
            {
                btnKeyClick.setBackgroundResource(R.drawable.bg_wrong_answer);
                if(btn_key3A.getTag().equals(data.getKey1())) btn_key3A.setBackgroundResource(R.drawable.bg_right_answer);
                if(btn_key3B.getTag().equals(data.getKey1())) btn_key3B.setBackgroundResource(R.drawable.bg_right_answer);
                if(btn_key3C.getTag().equals(data.getKey1())) btn_key3C.setBackgroundResource(R.drawable.bg_right_answer);
                if(btn_key3D.getTag().equals(data.getKey1())) btn_key3D.setBackgroundResource(R.drawable.bg_right_answer);
            }

            btn_key3A.setClickable(false);
            btn_key3B.setClickable(false);
            btn_key3C.setClickable(false);
            btn_key3D.setClickable(false);
            countAnsweredQuestions++;
        }
        if(countAnsweredQuestions==3)
        {
           DataLocalManager.addDoneQuestion(data.getId());
           DBQuery.updateStatisticValues(4);
        }
    }
    private void addControls(View view) {
        btn_key1A = view.findViewById(R.id.btn_key1A);
        btn_key1A.setTag("A");
        btn_key1B = view.findViewById(R.id.btn_key1B);
        btn_key1B.setTag("B");
        btn_key1C = view.findViewById(R.id.btn_key1C);
        btn_key1C.setTag("C");
        btn_key1D = view.findViewById(R.id.btn_key1D);
        btn_key1D.setTag("D");
        btn_key2A = view.findViewById(R.id.btn_key2A);
        btn_key2A.setTag("A");
        btn_key2B = view.findViewById(R.id.btn_key2B);
        btn_key2B.setTag("B");
        btn_key2C = view.findViewById(R.id.btn_key2C);
        btn_key2C.setTag("C");
        btn_key2D = view.findViewById(R.id.btn_key2D);
        btn_key2D.setTag("D");
        btn_key3A = view.findViewById(R.id.btn_key3A);
        btn_key3A.setTag("A");
        btn_key3B = view.findViewById(R.id.btn_key3B);
        btn_key3B.setTag("B");
        btn_key3C = view.findViewById(R.id.btn_key3C);
        btn_key3C.setTag("C");
        btn_key3D = view.findViewById(R.id.btn_key3D);
        btn_key3D.setTag("D");
        txt_question1 = view.findViewById(R.id.txt_question1);
        txt_question2 = view.findViewById(R.id.txt_question2);
        txt_question3 = view.findViewById(R.id.txt_question3);
        btn_show_script = view.findViewById(R.id.btn_show_script);
        loadDataToView();
    }
    public void loadDataToView()
    {
        txt_question1.setText(data.getQuestion1());
        btn_key1A.setText(data.getScript_key1A());
        btn_key1B.setText(data.getScript_key1B());
        btn_key1C.setText(data.getScript_key1C());
        btn_key1D.setText(data.getScript_key1D());
        txt_question2.setText(data.getQuestion2());
        btn_key2A.setText(data.getScript_key2A());
        btn_key2B.setText(data.getScript_key2B());
        btn_key2C.setText(data.getScript_key2C());
        btn_key2D.setText(data.getScript_key2D());
        txt_question3.setText(data.getQuestion3());
        btn_key3A.setText(data.getScript_key3A());
        btn_key3B.setText(data.getScript_key3B());
        btn_key3C.setText(data.getScript_key3C());
        btn_key3D.setText(data.getScript_key3D());
    }
    public void setData(QuestionPartThreeAndFour data)
    {
        this.data = data;
    }
}