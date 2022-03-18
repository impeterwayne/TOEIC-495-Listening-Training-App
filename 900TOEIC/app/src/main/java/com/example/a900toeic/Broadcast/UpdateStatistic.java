package com.example.a900toeic.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.example.a900toeic.Database.DBQuery;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UpdateStatistic extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String localDate = sdf.format(date);
        //TODO: check if firebase contains the same date
        String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference ref = DBQuery.db.collection("User").document(user_id)
                .collection("Statistic").document(localDate);
        ref.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if(!value.exists())
                {
                    Map<String, Integer> map = new HashMap<>();
                    map.put("num_part1", 0);
                    map.put("num_part2", 0);
                    map.put("num_part3", 0);
                    map.put("num_part4", 0);
                    ref.set(map);
                }
            }
        });
    }
}
