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


public class PartFourFragment extends Fragment {
    private AppCompatButton btn_keyA, btn_keyB, btn_keyC,btn_keyD,
            btn_key2A,btn_key2B,btn_key2C,btn_key2D,
            btn_key3A,btn_key3B,btn_key3C,btn_key3D;
    private AppCompatButton btn_show_script;
    private TextView txt_question1, txt_script2, txt_script3;
    private String keyClick , key2click, key3click;
    private QuestionPartThreeAndFour data;
    private int countAnsweredQuestions = 0 ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_part_four, container, false);
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
        btn_keyA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keyClick = "A";
                processAnswer(keyClick,btn_keyA,1);
            }
        });
        btn_keyB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keyClick = "B";
                processAnswer(keyClick,btn_keyB,1);
            }
        });
        btn_keyC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keyClick = "C";
                processAnswer(keyClick,btn_keyC,1);
            }
        });
        btn_keyD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keyClick = "D";
                processAnswer(keyClick,btn_keyD,1);
            }
        });
        btn_key2A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                key2click = "A";
                processAnswer(key2click,btn_key2A,2);
            }
        });
        btn_key2B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                key2click = "B";
                processAnswer(key2click,btn_key2B,2);
            }
        });
        btn_key2C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                key2click = "C";
                processAnswer(key2click,btn_key2C,2);
            }
        });
        btn_key2D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                key2click = "D";
                processAnswer(key2click,btn_key2D,2);
            }
        });
        btn_key3A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                key3click = "A";
                processAnswer(key3click,btn_key3A,3);
            }
        });
        btn_key3B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                key3click = "B";
                processAnswer(key3click,btn_key3B,3);
            }
        });
        btn_key3C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                key3click = "C";
                processAnswer(key3click,btn_key3C,3);
            }
        });
        btn_key3D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                key3click = "D";
                processAnswer(key3click,btn_key3D,3);
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

    public void processAnswer(String keyClick, AppCompatButton btn_click, int questionNum)
    {

        if(questionNum==1)
        {
            if(keyClick.equals(data.getKey1()))
                btn_click.setBackgroundResource(R.drawable.bg_right_answer);
            else
                btn_click.setBackgroundResource(R.drawable.bg_wrong_answer);
            btn_keyA.setClickable(false);
            btn_keyB.setClickable(false);
            btn_keyC.setClickable(false);
            btn_keyD.setClickable(false);
            countAnsweredQuestions++;
        }else if(questionNum==2)
        {
            if(key2click.equals(data.getKey2()))
                btn_click.setBackgroundResource(R.drawable.bg_right_answer);
            else
                btn_click.setBackgroundResource(R.drawable.bg_wrong_answer);
            btn_key2A.setClickable(false);
            btn_key2B.setClickable(false);
            btn_key2C.setClickable(false);
            btn_key2D.setClickable(false);
            countAnsweredQuestions++;
        }
        else {
            if(key3click.equals(data.getKey3()))
                btn_click.setBackgroundResource(R.drawable.bg_right_answer);
            else
                btn_click.setBackgroundResource(R.drawable.bg_wrong_answer);
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
        btn_keyA = view.findViewById(R.id.btn_key1A);
        btn_keyB = view.findViewById(R.id.btn_key1B);
        btn_keyC = view.findViewById(R.id.btn_key1C);
        btn_keyD = view.findViewById(R.id.btn_key1D);
        btn_key2A = view.findViewById(R.id.btn_key2A);
        btn_key2B = view.findViewById(R.id.btn_key2B);
        btn_key2C = view.findViewById(R.id.btn_key2C);
        btn_key2D = view.findViewById(R.id.btn_key2D);
        btn_key3A = view.findViewById(R.id.btn_key3A);
        btn_key3B = view.findViewById(R.id.btn_key3B);
        btn_key3C = view.findViewById(R.id.btn_key3C);
        btn_key3D = view.findViewById(R.id.btn_key3D);
        txt_question1 = view.findViewById(R.id.txt_question1);
        txt_script2 = view.findViewById(R.id.txt_question2);
        txt_script3 = view.findViewById(R.id.txt_question3);
        btn_show_script = view.findViewById(R.id.btn_show_script);
        loadDataToView();
    }
    public void loadDataToView()
    {

        txt_question1.setText(data.getQuestion1());
        btn_keyA.setText(data.getScript_key1A());
        btn_keyB.setText(data.getScript_key1B());
        btn_keyC.setText(data.getScript_key1C());
        btn_keyD.setText(data.getScript_key1D());


        txt_script2.setText(data.getQuestion2());
        btn_key2A.setText(data.getScript_key2A());
        btn_key2B.setText(data.getScript_key2B());
        btn_key2C.setText(data.getScript_key2C());
        btn_key2D.setText(data.getScript_key2D());


        txt_script3.setText(data.getQuestion3());
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