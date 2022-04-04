package com.sportsclub.fizanyatik;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class PranjalSingh extends AppCompatActivity {
    ImageSlider is19, is20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pranjal_singh);

        ImageView back = findViewById(R.id.back_ps);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        is19 = findViewById(R.id.image_slider1);
        is20 = findViewById(R.id.image_slider2);

        final List<SlideModel> imagelist = new ArrayList<>();
        imagelist.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FPranjal%202019%2FSpeed.png?alt=media&token=69d3f2ad-d4cf-4899-a0bd-cbfd45964aaa",
                "", ScaleTypes.FIT));
        imagelist.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FPranjal%202019%2FS-Lazy%20Lad.png?alt=media&token=8fc619c2-5b80-4079-ba68-8cc4eb33aa76",
                "", ScaleTypes.FIT));

        is19.setImageList(imagelist, ScaleTypes.FIT);

        final List<SlideModel> imagelist2 = new ArrayList<>();
        imagelist2.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FPranjal%202020%2F20210121_143255_0000.png?alt=media&token=d5bf9ecd-eea8-42c3-a896-57395c51da02",
                "", ScaleTypes.FIT));
        imagelist2.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FPranjal%202020%2F20210121_143322_0000.png?alt=media&token=971bd603-21df-4832-874b-ecfc818fe63e",
                "", ScaleTypes.FIT));
        imagelist2.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FPranjal%202020%2F20210121_150922_0000.png?alt=media&token=c468da32-e374-4ea4-a76c-0e16a5d2ee35",
                "", ScaleTypes.FIT));
        imagelist2.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FPranjal%202020%2F20210121_143341_0000.png?alt=media&token=44bf02ea-7eae-4782-bd87-93297bbe9192",
                "", ScaleTypes.FIT));
        imagelist2.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FPranjal%202020%2F20210121_150852_0000.png?alt=media&token=289ce6db-69b3-46af-a1bc-1c2761ecfb08",
                "", ScaleTypes.FIT));

        is20.setImageList(imagelist2, ScaleTypes.FIT);
    }
}