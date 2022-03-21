package com.example.a900toeic.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a900toeic.Adapter.RealTestPartOneAdapter;
import com.example.a900toeic.Adapter.RealTestPartThreeAndFourAdapter;
import com.example.a900toeic.Adapter.RealTestPartTwoAdapter;
import com.example.a900toeic.Database.DBQuery;
import com.example.a900toeic.LocalData.DataLocalManager;
import com.example.a900toeic.Model.Answer;
import com.example.a900toeic.Model.QuestionPartOne;
import com.example.a900toeic.Model.QuestionPartThreeAndFour;
import com.example.a900toeic.Model.QuestionPartTwo;
import com.example.a900toeic.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RealTestActivity extends AppCompatActivity {
    private RecyclerView rcv_test_part1;
    private RecyclerView rcv_test_part2;
    private RecyclerView rcv_test_part3;
    private RecyclerView rcv_test_part4;
    private RealTestPartOneAdapter partOneAdapter;
    private RealTestPartTwoAdapter partTwoAdapter;
    private RealTestPartThreeAndFourAdapter partThreeAdapter;
    private RealTestPartThreeAndFourAdapter partFourAdapter;
    private List<QuestionPartOne> partOneQuestionList;
    private List<QuestionPartTwo> partTwoQuestionList;
    private List<QuestionPartThreeAndFour> partThreeQuestionList;
    private List<QuestionPartThreeAndFour> partFourQuestionList;
    private Map<Long, String> keyMap;
    private LinearLayout bottom_audio_bar;
    private TextView btn_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_test);
        addControls();
        List<Answer> answerList = (List<Answer>) getIntent().getSerializableExtra("listAnswer");
        if(answerList==null)
        {
            loadQuestionForTest(new iMyCallback() {
                @Override
                public void onCallBack(Map<Long, String> map) {
                    Log.d("keymapcallbacksize", map.size()+"");
                    Intent intent = new Intent(RealTestActivity.this, ResultActivity.class);
                    intent.putExtra("keyMap", (Serializable) map);
                    btn_submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(intent);
                        }
                    });
                }
            });
        }else {
            loadQuestionsForResult(answerList);
            bottom_audio_bar.setVisibility(View.GONE);
        }


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(RealTestActivity.this, ResultActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    private void loadQuestionsForResult(List<Answer> answerList) {
        String testName = DataLocalManager.getTestName();
        partOneQuestionList = new ArrayList<>();
        partTwoQuestionList = new ArrayList<>();
        partThreeQuestionList = new ArrayList<>();
        partFourQuestionList = new ArrayList<>();
        DBQuery.db.collection("Tests").document(testName).collection("Part1")
                .orderBy("number").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(DocumentSnapshot doc : queryDocumentSnapshots)
                {
                    QuestionPartOne ques = doc.toObject(QuestionPartOne.class);
                    partOneQuestionList.add(ques);
                }
                partOneAdapter = new RealTestPartOneAdapter(RealTestActivity.this,partOneQuestionList,answerList);
                rcv_test_part1.setLayoutManager(new LinearLayoutManager(RealTestActivity.this));
                rcv_test_part1.setHasFixedSize(true);
                rcv_test_part1.setAdapter(partOneAdapter);
            }
        });
        DBQuery.db.collection("Tests").document(testName).collection("Part2")
                .orderBy("number").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(DocumentSnapshot doc : queryDocumentSnapshots)
                {
                    QuestionPartTwo ques = doc.toObject(QuestionPartTwo.class);
                    partTwoQuestionList.add(ques);
                }
                partTwoAdapter = new RealTestPartTwoAdapter(RealTestActivity.this,partTwoQuestionList,answerList);
                rcv_test_part2.setLayoutManager(new LinearLayoutManager(RealTestActivity.this));
                rcv_test_part2.setHasFixedSize(true);
                rcv_test_part2.setAdapter(partTwoAdapter);
            }
        });
        DBQuery.db.collection("Tests").document(testName).collection("Part3")
                .orderBy("number1").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(DocumentSnapshot doc : queryDocumentSnapshots)
                {
                    QuestionPartThreeAndFour ques = doc.toObject(QuestionPartThreeAndFour.class);
                    partThreeQuestionList.add(ques);
                }
                partThreeAdapter = new RealTestPartThreeAndFourAdapter(RealTestActivity.this,partThreeQuestionList,answerList);
                rcv_test_part3.setLayoutManager(new LinearLayoutManager(RealTestActivity.this));
                rcv_test_part3.setHasFixedSize(true);
                rcv_test_part3.setAdapter(partThreeAdapter);
            }
        });
        DBQuery.db.collection("Tests").document(testName).collection("Part4")
                .orderBy("number1").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(DocumentSnapshot doc : queryDocumentSnapshots)
                {
                    QuestionPartThreeAndFour ques = doc.toObject(QuestionPartThreeAndFour.class);
                    partFourQuestionList.add(ques);
                }
                partFourAdapter = new RealTestPartThreeAndFourAdapter(RealTestActivity.this,partFourQuestionList,answerList);
                rcv_test_part4.setLayoutManager(new LinearLayoutManager(RealTestActivity.this));
                rcv_test_part4.setHasFixedSize(true);
                rcv_test_part4.setAdapter(partFourAdapter);
            }
        });
    }

    private void loadQuestionForTest(iMyCallback callback) {
        String testName = DataLocalManager.getTestName();
        partOneQuestionList = new ArrayList<>();
        partTwoQuestionList = new ArrayList<>();
        partThreeQuestionList = new ArrayList<>();
        partFourQuestionList = new ArrayList<>();
        keyMap = new HashMap<>();
        DBQuery.db.collection("Tests").document(testName).collection("Part1")
                .orderBy("number").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(DocumentSnapshot doc : queryDocumentSnapshots)
                {
                    QuestionPartOne ques = doc.toObject(QuestionPartOne.class);
                    keyMap.put(ques.getNumber(), ques.getKey());
                    partOneQuestionList.add(ques);
                }
                partOneAdapter = new RealTestPartOneAdapter(RealTestActivity.this,partOneQuestionList,null);
                rcv_test_part1.setLayoutManager(new LinearLayoutManager(RealTestActivity.this));
                rcv_test_part1.setHasFixedSize(true);
                rcv_test_part1.setAdapter(partOneAdapter);
            }
        });
        DBQuery.db.collection("Tests").document(testName).collection("Part2")
                .orderBy("number").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(DocumentSnapshot doc : queryDocumentSnapshots)
                {
                    QuestionPartTwo ques = doc.toObject(QuestionPartTwo.class);
                    keyMap.put(ques.getNumber(), ques.getKey());
                    partTwoQuestionList.add(ques);
                }
                partTwoAdapter = new RealTestPartTwoAdapter(RealTestActivity.this,partTwoQuestionList, null);
                rcv_test_part2.setLayoutManager(new LinearLayoutManager(RealTestActivity.this));
                rcv_test_part2.setHasFixedSize(true);
                rcv_test_part2.setAdapter(partTwoAdapter);
            }
        });
        DBQuery.db.collection("Tests").document(testName).collection("Part3")
                .orderBy("number1").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(DocumentSnapshot doc : queryDocumentSnapshots)
                {
                    QuestionPartThreeAndFour ques = doc.toObject(QuestionPartThreeAndFour.class);
                    keyMap.put(ques.getNumber1(), ques.getKey1());
                    keyMap.put(ques.getNumber2(), ques.getKey2());
                    keyMap.put(ques.getNumber3(), ques.getKey3());
                    partThreeQuestionList.add(ques);
                }
                partThreeAdapter = new RealTestPartThreeAndFourAdapter(RealTestActivity.this,partThreeQuestionList,null);
                rcv_test_part3.setLayoutManager(new LinearLayoutManager(RealTestActivity.this));
                rcv_test_part3.setHasFixedSize(true);
                rcv_test_part3.setAdapter(partThreeAdapter);
            }
        });
        DBQuery.db.collection("Tests").document(testName).collection("Part4")
                .orderBy("number1").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(DocumentSnapshot doc : queryDocumentSnapshots)
                {
                    QuestionPartThreeAndFour ques = doc.toObject(QuestionPartThreeAndFour.class);
                    keyMap.put(ques.getNumber1(), ques.getKey1());
                    keyMap.put(ques.getNumber2(), ques.getKey2());
                    keyMap.put(ques.getNumber3(), ques.getKey3());
                    partFourQuestionList.add(ques);
                }
                partFourAdapter = new RealTestPartThreeAndFourAdapter(RealTestActivity.this,partFourQuestionList,null);
                rcv_test_part4.setLayoutManager(new LinearLayoutManager(RealTestActivity.this));
                rcv_test_part4.setHasFixedSize(true);
                rcv_test_part4.setAdapter(partFourAdapter);
                Log.d("keymapsize", keyMap.size()+"");
                callback.onCallBack(keyMap);
            }
        });


    }

    private void addControls() {
        rcv_test_part1 = findViewById(R.id.rcv_test_part1);
        rcv_test_part2 = findViewById(R.id.rcv_test_part2);
        rcv_test_part3 = findViewById(R.id.rcv_test_part3);
        rcv_test_part4 = findViewById(R.id.rcv_test_part4);
        bottom_audio_bar = findViewById(R.id.bottom_audio_bar);
        btn_submit = findViewById(R.id.btn_submit);
    }
    public interface iMyCallback
    {
        void onCallBack(Map<Long, String> map);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        DataLocalManager.clearAnswers();
    }
}