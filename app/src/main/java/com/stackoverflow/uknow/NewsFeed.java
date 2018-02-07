package com.stackoverflow.uknow;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.stackoverflow.uknow.Classes.InterviewResult;

import java.util.ArrayList;
import java.util.List;


public class NewsFeed extends Fragment {

    List<InterviewResult> interviewResults = new ArrayList<>();

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    RecyclerView recycle;


    public NewsFeed() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_feed, container, false);

        // init firebase database
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();

        //setup Recycler view
        recycle = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        addEventFirebaseListener();


        return view;
    }

    private void addEventFirebaseListener() {

        //Read from database
        mDatabaseReference.child("Interview").child("share").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (interviewResults.size() > 0)
                    interviewResults.clear();
                for (DataSnapshot postSnapshot:dataSnapshot.getChildren()) {

                    InterviewResult interviewResult1 = postSnapshot.getValue(InterviewResult.class);

                    interviewResults.add(interviewResult1);

                }

                recycle.setHasFixedSize(true);
                RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), interviewResults);
                recycle.setLayoutManager(new LinearLayoutManager(getContext()));
                recycle.setAdapter(recyclerViewAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

}
