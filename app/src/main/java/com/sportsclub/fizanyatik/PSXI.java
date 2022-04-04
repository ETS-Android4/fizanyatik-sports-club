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

public class PSXI extends AppCompatActivity {
    CardView shikhar, pranjal;

    @Override
    public void onBackPressed(){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psxi);

        ImageView back = findViewById(R.id.back_psxi);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        shikhar = findViewById(R.id.card_shikhar);
        ImageView image = findViewById(R.id.shikhar_photo);
        pranjal = findViewById(R.id.card_pranjal);
        ConstraintLayout imageView = findViewById(R.id.cons_pranjal);

        shikhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(PSXI.this, ShikharSinha.class);
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(image, "shikhar");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(PSXI.this, pairs);
                startActivity(mainIntent, options.toBundle());
            }
        });

        pranjal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(PSXI.this, PranjalSingh.class);
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(imageView, "pranjal");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(PSXI.this, pairs);
                startActivity(mainIntent, options.toBundle());
            }
        });

    }
}