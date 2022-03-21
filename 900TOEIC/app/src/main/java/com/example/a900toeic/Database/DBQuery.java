package com.example.a900toeic.Database;

import android.util.Log;

import com.example.a900toeic.Model.DataStatistic;
import com.example.a900toeic.Model.Question;
import com.example.a900toeic.Model.QuestionPartOne;
import com.example.a900toeic.Model.QuestionPartThreeAndFour;
import com.example.a900toeic.Model.QuestionPartTwo;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBQuery {
    public static FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static List<QuestionPartOne> questionPartOneList = new ArrayList<>();
    public static List<QuestionPartTwo> questionPartTwoList = new ArrayList<>();
    public static List<QuestionPartThreeAndFour> questionPartThreeList = new ArrayList<>();
    public static List<QuestionPartThreeAndFour> questionPartFourList = new ArrayList<>();
    public static List<QuestionPartOne> questionPartOneReviewList = new ArrayList<>();
    public static List<QuestionPartTwo> questionPartTwoReviewList = new ArrayList<>();
    public static List<QuestionPartThreeAndFour> questionPartThreeReviewList = new ArrayList<>();
    public static List<QuestionPartThreeAndFour> questionPartFourReviewList = new ArrayList<>();
    public static ArrayList<DataStatistic> dataStatisticArr = new ArrayList<>();

    public static void loadTestNameList(iTestNameCallback callback) {
        ArrayList<String> listTest = new ArrayList<>();
        db.collection("Tests").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot doc : queryDocumentSnapshots) {
                    listTest.add(doc.get("name", String.class));
                }
                callback.onCallBack(listTest);
                Log.d("ListTestsize", listTest.size()+"");
            }
        });
    }
    public static void loadDataPartOne(List<String> listTest, iTrainingCallback<QuestionPartOne> dataCallback) {
                questionPartOneList.clear();
                for(String test : listTest)
                {
                    db.collection("Tests").document(test).collection("Part1").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for(DocumentSnapshot doc: queryDocumentSnapshots)
                            {
                                questionPartOneList.add(doc.toObject(QuestionPartOne.class));
                            }
                            dataCallback.onCallBack(questionPartOneList);
                        }
                    });
                }

            }


    public static void loadDataPartTwo(List<String> listTest,iTrainingCallback<QuestionPartTwo> callback) {
        questionPartTwoList.clear();
        db.collection("Tests").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(DocumentSnapshot doc: queryDocumentSnapshots)
                {
                    listTest.add(doc.get("name",String.class));
                }
                for(String test : listTest)
                {
                    db.collection("Tests").document(test).collection("Part2").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for(DocumentSnapshot doc: queryDocumentSnapshots)
                            {
                                questionPartTwoList.add(doc.toObject(QuestionPartTwo.class));
                            }
                            callback.onCallBack(questionPartTwoList);
                        }
                    });
                }

            }
        });
    }

    public static void loadDataPartThree(List<String> listTest, iTrainingCallback<QuestionPartThreeAndFour> callback) {
        questionPartThreeList.clear();
                for(String test : listTest)
                {
                    db.collection("Tests").document(test).collection("Part3").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for(DocumentSnapshot doc: queryDocumentSnapshots)
                            {
                                questionPartThreeList.add(doc.toObject(QuestionPartThreeAndFour.class));
                            }
                            callback.onCallBack(questionPartThreeList);
                        }
                    });
                }

            }

    public static void loadDataPartFour(List<String> listTest, iTrainingCallback<QuestionPartThreeAndFour> callback) {
        questionPartFourList.clear();
                for(String test : listTest)
                {
                    db.collection("Tests").document(test).collection("Part4").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for(DocumentSnapshot doc: queryDocumentSnapshots)
                            {
                                questionPartFourList.add(doc.toObject(QuestionPartThreeAndFour.class));
                            }
                            callback.onCallBack(questionPartFourList);
                        }
                    });
                }
            }


    public static void loadDataReviewPartOne(String uid, iTrainingCallback<QuestionPartOne> callback) {

        questionPartOneReviewList.clear();
        db.collection("User").document(uid).collection("Part1").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(DocumentSnapshot doc : queryDocumentSnapshots)
                        {
                            QuestionPartOne ques = doc.toObject(QuestionPartOne.class);
                            questionPartOneReviewList.add(ques);
                        }
                        callback.onCallBack(questionPartOneReviewList);
                    }
                });

    }

    public static void loadDataReviewPartTwo(String uid, iTrainingCallback<QuestionPartTwo> callback) {

        questionPartTwoReviewList.clear();
        db.collection("User").document(uid).collection("Part2").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(DocumentSnapshot doc : queryDocumentSnapshots)
                        {
                            QuestionPartTwo ques = doc.toObject(QuestionPartTwo.class);
                            questionPartTwoReviewList.add(ques);
                        }
                        callback.onCallBack(questionPartTwoReviewList);
                    }
                });

    }

    public static void loadDataReviewPartThree(String uid,iTrainingCallback<QuestionPartThreeAndFour> callback) {
        questionPartThreeReviewList.clear();
        db.collection("User").document(uid).collection("Part3").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(DocumentSnapshot doc : queryDocumentSnapshots)
                        {
                            QuestionPartThreeAndFour ques = doc.toObject(QuestionPartThreeAndFour.class);
                            questionPartThreeReviewList.add(ques);
                        }
                        callback.onCallBack(questionPartThreeList);
                    }
                });
    }

    public static void loadDataReviewPartFour(String uid,iTrainingCallback<QuestionPartThreeAndFour> callback) {
        questionPartFourReviewList.clear();
        db.collection("User").document(uid).collection("Part4").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(DocumentSnapshot doc : queryDocumentSnapshots)
                        {
                            QuestionPartThreeAndFour ques = doc.toObject(QuestionPartThreeAndFour.class);
                            questionPartFourReviewList.add(ques);
                        }
                        callback.onCallBack(questionPartFourList);
                    }
                });
    }

    public static void loadDataToNewUser(String uid) {
        DocumentReference ref = db.collection("User").document(uid);
        Map<String, Object> basic_information = new HashMap<>();
        basic_information.put("id", uid);
        basic_information.put("goal", 450);
        basic_information.put("max_score", 0);
        ref.set(basic_information);

    }

    public static void loadUserGoal(String uid, iUserGoalCallback callback) {
        db.collection("User").document(uid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                long user_goal = documentSnapshot.getLong("goal");
                long user_highest_score = documentSnapshot.getLong("max_score");
                callback.onCallBack(user_goal, user_highest_score);
            }
        });

    }

    public static void updateStatisticValues(int part_num) {
        String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date localDate = new Date();
        DocumentReference ref = DBQuery.db.collection("User").document(user_id)
                .collection("Statistic").document(sdf.format(localDate));
        int increaseValue = 1;
        if (part_num == 3 || part_num == 4) increaseValue = 3;
        ref.update("num_part" + part_num, FieldValue.increment(increaseValue));
    }

    public static void loadDataStatistic(iDataStatisticCallback callback) {
        String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        ArrayList<DataStatistic> res = new ArrayList<>();
        CollectionReference ref = DBQuery.db.collection("User").document(user_id)
                .collection("Statistic");
        ref.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for (DocumentSnapshot doc : queryDocumentSnapshots) {
                    DataStatistic dataStatistic = new DataStatistic();
                    dataStatistic.setDate((String)doc.get("date"));
                    dataStatistic.setTime((int)(long)doc.get("time"));
                    dataStatistic.setTestName((String) doc.get("testName"));
                    dataStatistic.setScore((int) (long)doc.get("score"));
                    res.add(dataStatistic);
                }
                Collections.sort(res, new Comparator<DataStatistic>() {
                    @Override
                    public int compare(DataStatistic data1, DataStatistic data2) {
                        return data1.getTime() - data2.getTime();
                    }
                });
                callback.onCallBack(res);
            }
        });
    }

    public interface iDataStatisticCallback {
        void onCallBack(ArrayList<DataStatistic> res);
    }
    public interface iTrainingCallback <T extends Question> {
        void onCallBack(List<T> res);
    }

    public interface iTestNameCallback {
        void onCallBack(List<String> res);
    }

    public interface iUserGoalCallback
    {
        void onCallBack(long user_goal, long highest_score);
    }
}
