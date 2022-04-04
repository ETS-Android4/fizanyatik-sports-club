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

public class ShikharSinha extends AppCompatActivity {
    ImageSlider is19, is20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shikhar_sinha);

        ImageView back = findViewById(R.id.back_sh);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        is19 = findViewById(R.id.image_slider1);
        is20 = findViewById(R.id.image_slider2);

        final List<SlideModel> imagelist = new ArrayList<>();
        imagelist.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FShikhar%202019%2FFuture%20Star.png?alt=media&token=a93f4d66-e76d-46eb-a8b8-e9fa308bf2a7",
                "", ScaleTypes.FIT));
        imagelist.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FShikhar%202019%2FS-Bowler.png?alt=media&token=443f774b-9270-4f02-9fe2-75c58825b9a8",
                "", ScaleTypes.FIT));
        imagelist.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FShikhar%202019%2FS-Catcher.png?alt=media&token=d74d2e76-f85c-4af0-9d73-360fe5050db6",
                "", ScaleTypes.FIT));
        imagelist.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FShikhar%202019%2FS-WAB.png?alt=media&token=2ef11928-2bc1-4306-aabf-923dc15881a5",
                "", ScaleTypes.FIT));
        imagelist.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FShikhar%202019%2FSixHitter.png?alt=media&token=e200eeef-fc1c-424a-8aa1-f63e4209dfca",
                "", ScaleTypes.FIT));
        is19.setImageList(imagelist, ScaleTypes.FIT);

        final List<SlideModel> imagelist2 = new ArrayList<>();
        imagelist2.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FShikhar%202020%2F0001-15810144822_20210121_164613_0000.png?alt=media&token=91378dca-2d00-4c9f-b33c-c24c1615b577",
                "", ScaleTypes.FIT));
        imagelist2.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FShikhar%202020%2F0001-15806731184_20210121_144145_0000.png?alt=media&token=0d67493e-a6b0-4062-8424-1a8c5056b99d",
                "", ScaleTypes.FIT));
        imagelist2.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FShikhar%202020%2F0001-15807035758_20210121_145327_0000.png?alt=media&token=8cbc9237-6eb9-47f0-9cb7-1fc41e3db87a",
                "", ScaleTypes.FIT));
        imagelist2.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FShikhar%202020%2F0001-15807371423_20210121_150608_0000.png?alt=media&token=8a9af066-e6f3-4da8-b7c4-49ff396b9af4",
                "", ScaleTypes.FIT));
        imagelist2.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FShikhar%202020%2F0001-15807384547_20210121_150654_0000.png?alt=media&token=2901a87c-912d-4549-9b6c-7a8364e47758",
                "", ScaleTypes.FIT));
        imagelist2.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FShikhar%202020%2F0001-15807405677_20210121_150742_0000.png?alt=media&token=80f93c5a-b39f-47cf-91c5-cca99c361a8f",
                "", ScaleTypes.FIT));

        is20.setImageList(imagelist2, ScaleTypes.FIT);

    }
}