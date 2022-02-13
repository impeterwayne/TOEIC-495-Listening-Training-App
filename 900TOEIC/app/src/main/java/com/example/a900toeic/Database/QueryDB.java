package com.example.a900toeic.Database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.a900toeic.LocalData.DataLocalManager;
import com.example.a900toeic.Model.QuestionPartOne;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryDB {
    public static FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static List<QuestionPartOne> questionPartOneList = new ArrayList<>();
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
                Log.d("Success retrieve data", String.valueOf(questionPartOneList.size()));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Failed to retrieve data", e.toString());
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
}
