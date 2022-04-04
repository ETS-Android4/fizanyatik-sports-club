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

public class PrateekSinha extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prateek_sinha);

        ImageView back = findViewById(R.id.back_pr);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageSlider is19, is20;

        is19 = findViewById(R.id.image_slider1);
        is20 = findViewById(R.id.image_slider2);

        final List<SlideModel> imagelist = new ArrayList<>();
        imagelist.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FPrateek%202019%2FBatsman.png?alt=media&token=612cd14a-ce7b-4d1c-b554-4ca5c985e55e",
                "", ScaleTypes.FIT));
        imagelist.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FPrateek%202019%2FCatch.png?alt=media&token=82d67b75-0f67-4bcd-b5b3-0135f8943e8e",
                "", ScaleTypes.FIT));
        imagelist.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FPrateek%202019%2FEnergizer.png?alt=media&token=93fc4453-8093-4133-a638-1097e2f26fca",
                "", ScaleTypes.FIT));
        imagelist.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FPrateek%202019%2FRunner.png?alt=media&token=e4f4b52d-4269-4f2c-af24-fb1afeb37b33",
                "", ScaleTypes.FIT));
        imagelist.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FPrateek%202019%2FShot%20invent.png?alt=media&token=897d7d95-815b-4e3d-aebe-e9029309db1e",
                "", ScaleTypes.FIT));
        imagelist.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FPrateek%202019%2FSmart.png?alt=media&token=c5d355b8-42b9-4ec3-b4ec-ddd79fa1a5e1",
                "", ScaleTypes.FIT));
        is19.setImageList(imagelist, ScaleTypes.FIT);

        final List<SlideModel> imagelist2 = new ArrayList<>();
        imagelist2.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FPrateek%202020%2F0001-15957905187_20210125_130157_0000.png?alt=media&token=63eb85c9-164c-4c6a-94fe-1550695c3ee3",
                "", ScaleTypes.FIT));
        imagelist2.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FPrateek%202020%2F0001-15807035758_20210121_145327_0000.png?alt=media&token=c73ffcc1-926f-4c5c-80b5-36b033622f31",
                "", ScaleTypes.FIT));
        imagelist2.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FPrateek%202020%2F20210121_142200_0000.png?alt=media&token=e4189b97-ca7d-4c5d-8777-e1e19840b383",
                "", ScaleTypes.FIT));
        imagelist2.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FPrateek%202020%2F20210121_142255_0000.png?alt=media&token=8cd664bf-6f10-4309-8ae0-ecea1effd4e8",
                "", ScaleTypes.FIT));
        imagelist2.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FPrateek%202020%2F20210121_142334_0000.png?alt=media&token=5971b3ad-f7dc-4445-b0d1-639c1641da51",
                "", ScaleTypes.FIT));
        imagelist2.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FPrateek%202020%2F20210121_142400_0000.png?alt=media&token=31bcbc6d-2828-4354-bc43-a4f47ee9e871",
                "", ScaleTypes.FIT));
        imagelist2.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FPrateek%202020%2F20210121_145557_0000.png?alt=media&token=db8246aa-6640-41d9-b080-f64c3ab6ca9e",
                "", ScaleTypes.FIT));
        imagelist2.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FPrateek%202020%2F20210121_150008_0000.png?alt=media&token=118df3ae-048a-4c38-a722-37bf9738c177",
                "", ScaleTypes.FIT));
        imagelist2.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FPrateek%202020%2F20210121_145921_0000.png?alt=media&token=5ff3ab69-7a02-45a3-b1a2-c4fd77657fad",
                "", ScaleTypes.FIT));
        imagelist2.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FPrateek%202020%2F20210121_145845_0000.png?alt=media&token=0b4a6012-8436-46a9-961a-acda382f3601",
                "", ScaleTypes.FIT));

        is20.setImageList(imagelist2, ScaleTypes.FIT);

    }
}