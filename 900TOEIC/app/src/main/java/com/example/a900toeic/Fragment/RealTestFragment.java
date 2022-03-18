package com.example.a900toeic.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a900toeic.Activity.MainActivity;
import com.example.a900toeic.Adapter.RealTestAdapter;
import com.example.a900toeic.Database.DBQuery;
import com.example.a900toeic.Model.RealTest;
import com.example.a900toeic.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class RealTestFragment extends Fragment {
    private Toolbar toolbar;
    private RecyclerView rcv_tests;
    private RealTestAdapter realTestAdapter;
    private List<RealTest> realTestList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_real_test, container, false);
        addControls(view);
        loadData();
        return view;
    }

    private void loadData() {
        realTestList = new ArrayList<>();
        DBQuery.db.collection("Tests").orderBy("number").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(DocumentSnapshot doc : queryDocumentSnapshots)
                {
                    RealTest test = doc.toObject(RealTest.class);
                    realTestList.add(test);
                }
                realTestAdapter = new RealTestAdapter(getContext(), realTestList);
                rcv_tests.setLayoutManager(new LinearLayoutManager(getContext()));
                rcv_tests.setAdapter(realTestAdapter);
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MainActivity.class));
                getActivity().finish();
            }
        });
    }

    private void addControls(View view) {
        rcv_tests = view.findViewById(R.id.rcv_tests);
        toolbar = view.findViewById(R.id.toolbar);
    }

}