package com.sportsclub.fizanyatik;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sportsclub.fizanyatik.Adapter.FeedTextAdapter;
import com.sportsclub.fizanyatik.Model.FeedTextList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import xyz.peridy.shimmerlayout.ShimmerLayout;

public class FeedsFragment extends Fragment {
    LinearLayout pill1, pill2;
    TextView sub_btn;
    ShimmerLayout shimmer2;
    RecyclerView feedtextrecyclerview;
    private List<FeedTextList> feeditems;
    RecyclerView.Adapter feedtextAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_feeds, container, false);

        pill1 = root.findViewById(R.id.pill1);
        pill2 = root.findViewById(R.id.pill2);
        shimmer2 = root.findViewById(R.id.shimmer2);
        sub_btn = root.findViewById(R.id.sub_btntextView2);
        feedtextrecyclerview = root.findViewById(R.id.feed_text);
        feedtextrecyclerview.setHasFixedSize(true);
        feedtextrecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        feeditems = new ArrayList<>();

        sub_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCljG6xDx_rvrC4ZCWp38gcA"));
                startActivity(intent);
            }
        });

        pill1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/view/fsclub"));
                startActivity(intent);
            }
        });

        pill2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:fizanyatiksc@gmail.com?subject=Feedback%20On%20App&body=Hey!"));
                startActivity(emailIntent);
            }
        });

        FirebaseDatabase.getInstance().getReference("FFeed").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                feeditems.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String body = dataSnapshot.child("body").getValue().toString();
                    String date = dataSnapshot.child("date").getValue().toString();
                    String topic = dataSnapshot.child("topic").getValue().toString();
                    String image = dataSnapshot.child("image").getValue().toString();

                    FeedTextList feedTextList = new FeedTextList(body, date, topic, image);
                    feeditems.add(feedTextList);
                }

                Collections.reverse(feeditems);
                feedtextAdapter = new FeedTextAdapter(feeditems, getContext());
                feedtextrecyclerview.setAdapter(feedtextAdapter);
                shimmer2.setVisibility(View.GONE);
                feedtextrecyclerview.setVisibility(View.VISIBLE);
                feedtextAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return root;
    }
}