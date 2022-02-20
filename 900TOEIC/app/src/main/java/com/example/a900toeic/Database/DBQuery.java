package com.example.a900toeic.Database;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.arch.core.internal.SafeIterableMap;

import com.example.a900toeic.LocalData.DataLocalManager;
import com.example.a900toeic.Model.QuestionPartOne;
import com.example.a900toeic.Model.QuestionPartThreeAndFour;
import com.example.a900toeic.Model.QuestionPartTwo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBQuery {
    public static FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static List<QuestionPartOne> questionPartOneList = new ArrayList<>();

    public static List<QuestionPartTwo> questionPartTwoList = new ArrayList<>();
    public static List<QuestionPartThreeAndFour> questionPartThreeList = new ArrayList<>();
    public static List<QuestionPartThreeAndFour> questionPartFourList = new ArrayList<>();
    public static String user_id = "";
    public static long user_goal;
    public static long user_highest_score;
    public static List<QuestionPartOne> questionPartOneReviewList = new ArrayList<>();
    public static List<QuestionPartTwo> questionPartTwoReviewList = new ArrayList<>();
    public static List<QuestionPartThreeAndFour> questionPartThreeReviewList = new ArrayList<>();
    public static List<QuestionPartThreeAndFour> questionPartFourReviewList = new ArrayList<>();

    public static void loadDataPartOne()
    {
        questionPartOneList.clear();
        db.collection("Quiz").document("Questions").collection("Part1").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot doc : queryDocumentSnapshots)
                {
                    QuestionPartOne ques = doc.toObject(QuestionPartOne.class);
                    if(!DataLocalManager.isDone(ques.getId())) questionPartOneList.add(ques);
                }
                Log.d("Success retrieve data1", String.valueOf(questionPartOneList.size()));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Failed to retrieve data", e.toString());
            }
        });
    }
    public static void loadDataPartTwo()
    {
        questionPartTwoList.clear();
        db.collection("Quiz").document("Questions").collection("Part2").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot doc : queryDocumentSnapshots)
                {
                    QuestionPartTwo ques = doc.toObject(QuestionPartTwo.class);
                    if(!DataLocalManager.isDone(ques.getId())) questionPartTwoList.add(ques);
                }
                Log.d("Success retrieve data2", String.valueOf(questionPartTwoList.size()));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Failed to retrieve data", e.toString());
            }
        });
    }
    public static void loadDataPartThree()
    {
        questionPartThreeList.clear();
        db.collection("Quiz").document("Questions").collection("Part3").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot doc : queryDocumentSnapshots)
                {
                    QuestionPartThreeAndFour ques = doc.toObject(QuestionPartThreeAndFour.class);
                    if(!DataLocalManager.isDone(ques.getId())) questionPartThreeList.add(ques);
                }
                Log.d("Success retrieve data3", String.valueOf(questionPartThreeList.size()));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Failed to retrieve data", e.toString());
            }
        });
    }
    public static void loadDataPartFour()
    {
        questionPartFourList.clear();
        db.collection("Quiz").document("Questions").collection("Part4").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot doc : queryDocumentSnapshots)
                {
                    QuestionPartThreeAndFour ques = doc.toObject(QuestionPartThreeAndFour.class);
                    if(!DataLocalManager.isDone(ques.getId())) questionPartFourList.add(ques);
                }
                Log.d("Success retrieve data4", String.valueOf(questionPartFourList.size()));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Failed to retrieve data", e.toString());
            }
        });
    }
    public static void loadDataReviewPartOne(String uid)
    {

        questionPartOneReviewList.clear();
        CollectionReference ref = db.collection("Quiz").document("Questions").collection("Part1");
        db.collection("User").document(uid).collection("Part1").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot doc : queryDocumentSnapshots)
                {
                    String questionId = doc.get("id",String.class);
                    ref.whereEqualTo("id",questionId).limit(1).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for(QueryDocumentSnapshot doc : queryDocumentSnapshots)
                            {
                                QuestionPartOne ques = doc.toObject(QuestionPartOne.class);
                                questionPartOneReviewList.add(ques);
                            }
                        }
                    });
                }
            }
        });
    }
    public static void loadDataReviewPartTwo(String uid)
    {

        questionPartTwoReviewList.clear();
        CollectionReference ref = db.collection("Quiz").document("Questions").collection("Part2");
        db.collection("User").document(uid).collection("Part2").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot doc : queryDocumentSnapshots)
                {
                    String questionId = doc.get("id",String.class);
                    ref.whereEqualTo("id",questionId).limit(1).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for(QueryDocumentSnapshot doc : queryDocumentSnapshots)
                            {
                                QuestionPartTwo ques = doc.toObject(QuestionPartTwo.class);
                                questionPartTwoReviewList.add(ques);
                            }
                        }
                    });
                }
            }
        });
    }
    public static void loadDataReviewPartThree(String uid)
    {

        questionPartThreeReviewList.clear();
        CollectionReference ref = db.collection("Quiz").document("Questions").collection("Part3");
        db.collection("User").document(uid).collection("Part3").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot doc : queryDocumentSnapshots)
                {
                    String questionId = doc.get("id",String.class);
                    ref.whereEqualTo("id",questionId).limit(1).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for(QueryDocumentSnapshot doc : queryDocumentSnapshots)
                            {
                                QuestionPartThreeAndFour ques = doc.toObject(QuestionPartThreeAndFour.class);
                                questionPartThreeReviewList.add(ques);
                            }
                        }
                    });
                }
            }
        });
    }
    public static void loadDataReviewPartFour(String uid)
    {
        questionPartFourReviewList.clear();
        CollectionReference ref = db.collection("Quiz").document("Questions").collection("Part4");
        db.collection("User").document(uid).collection("Part4").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot doc : queryDocumentSnapshots)
                {
                    String questionId = doc.get("id",String.class);
                    ref.whereEqualTo("id",questionId).limit(1).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for(QueryDocumentSnapshot doc : queryDocumentSnapshots)
                            {
                                QuestionPartThreeAndFour ques = doc.toObject(QuestionPartThreeAndFour.class);
                                questionPartFourReviewList.add(ques);
                            }
                        }
                    });
                }
            }
        });
    }
    public static void loadDataToNewUser(String uid)
    {
        DocumentReference ref = db.collection("User").document(uid);
        Map<String, Object> basic_information = new HashMap<>();
        basic_information.put("id",uid);
        basic_information.put("goal", 450);
        basic_information.put("max_score", 0);
        ref.set(basic_information);
        
    }
    public static void loadUserId()
    {
        user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
    }
    public static void loadUserGoal(String uid)
    {
        db.collection("User").document(uid)
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        user_goal = value.getLong("goal");
                        user_highest_score = value.getLong("max_score");
                    }
                });
    }
}
