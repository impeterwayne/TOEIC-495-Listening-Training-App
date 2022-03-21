package com.example.a900toeic.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a900toeic.Activity.LoginActivity;
import com.example.a900toeic.Database.DBQuery;
import com.example.a900toeic.R;
import com.example.a900toeic.Utils.Utils;
import com.google.firebase.auth.FirebaseAuth;


public class AccountFragment extends Fragment {
    private Button btn_log_out;
    private ImageView img_avatar;
    private TextView txt_fullName;
    private TextView txt_solved, txt_highestScore, txt_goal;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_account, container, false);
        addControls(view);
        addEvents(view);
        return view;
    }

    private void addEvents(View view) {
        btn_log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              FirebaseAuth.getInstance().signOut();
              startActivity(new Intent(getContext(), LoginActivity.class));
              getActivity().finish();
            }
        });
    }

    private void addControls(View view) {
        btn_log_out = view.findViewById(R.id.btn_log_out);
        img_avatar = view.findViewById(R.id.img_avatar);
        txt_fullName = view.findViewById(R.id.txt_fullName);
        txt_solved = view.findViewById(R.id.txt_solved);
        txt_highestScore =view.findViewById(R.id.txt_highestScore);
        txt_goal = view.findViewById(R.id.txt_goal);
        txt_fullName.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
        Glide.with(getContext()).load(FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl()).into(img_avatar);
    }
}