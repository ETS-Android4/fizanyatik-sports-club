package com.sportsclub.fizanyatik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;

public class APXI extends AppCompatActivity {
    CardView aryan, prateek;

    @Override
    public void onBackPressed(){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apxi);

        ImageView back = findViewById(R.id.back_apxi);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        aryan = findViewById(R.id.card_aryan);
        ImageView image = findViewById(R.id.aryan_photo);
        prateek = findViewById(R.id.card_prateek);
        ConstraintLayout imageView = findViewById(R.id.cons_prateek);

        aryan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(APXI.this, AryanSrivastava.class);
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(image, "aryan");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(APXI.this, pairs);
                startActivity(mainIntent, options.toBundle());
            }
        });

        prateek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(APXI.this, PrateekSinha.class);
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(imageView, "prateek");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(APXI.this, pairs);
                startActivity(mainIntent, options.toBundle());
            }
        });

    }
}