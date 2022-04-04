package com.sportsclub.fizanyatik;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sportsclub.fizanyatik.Adapter.MatchAdapter;
import com.sportsclub.fizanyatik.Model.MatchList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import xyz.peridy.shimmerlayout.ShimmerLayout;

public class MatchFragment extends Fragment{

    private RecyclerView recyclerView;
    private List<MatchList> matchLists;
    RecyclerView.Adapter matchAdapter;
    String email_admin;
    ShimmerLayout shimmer_match;
    DatabaseReference matchReference;
    ConstraintLayout addMatch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_match, container, false);
        recyclerView = root.findViewById(R.id.matchrecyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        matchLists = new ArrayList<>();
        addMatch = root.findViewById(R.id.add_match);
        shimmer_match = root.findViewById(R.id.shimmer_match);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Fusers").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                email_admin = snapshot.child("email").getValue().toString();
                if (email_admin.equals("moderator@fsc.com")){
                    addMatch.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        
        addMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        matchReference = FirebaseDatabase.getInstance().getReference("Match");

        matchReference.orderByChild("time").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                matchLists.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String team1_img = dataSnapshot.child("team1_img").getValue().toString();
                    String team1_name = dataSnapshot.child("team1_name").getValue().toString();
                    String team1_score = dataSnapshot.child("team1_score").getValue().toString();
                    String team1_over = dataSnapshot.child("team1_over").getValue().toString();
                    String team2_img = dataSnapshot.child("team2_img").getValue().toString();
                    String team2_name = dataSnapshot.child("team2_name").getValue().toString();
                    String team2_score = dataSnapshot.child("team2_score").getValue().toString();
                    String team2_over = dataSnapshot.child("team2_over").getValue().toString();
                    String match_result = dataSnapshot.child("match_result").getValue().toString();
                    String more_btn = dataSnapshot.child("more_btn").getValue().toString();

                    MatchList matchList = new MatchList(team1_img, team1_name, team1_score, team1_over, team2_img, team2_name, team2_score, team2_over, match_result, more_btn);
                    matchLists.add(matchList);
                }
                Collections.reverse(matchLists);
                matchAdapter = new MatchAdapter(getContext(), matchLists);
                recyclerView.setAdapter(matchAdapter);
                matchAdapter.notifyDataSetChanged();
                shimmer_match.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return root;
    }

    private void openDialog() {
        MatchDialog matchDialog = new MatchDialog();
        matchDialog.show(getActivity().getSupportFragmentManager(), "Match Dialog");
    }
}