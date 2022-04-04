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

public class AryanSrivastava extends AppCompatActivity {
    ImageSlider is19, is20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aryan_srivastava);

        ImageView back = findViewById(R.id.back_ar);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        is19 = findViewById(R.id.image_slider1);
        is20 = findViewById(R.id.image_slider2);

        final List<SlideModel> imagelist = new ArrayList<>();
        imagelist.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FAryan%202019%2FBowler.png?alt=media&token=f422cd5f-4230-4b67-b7aa-ca64b85bf301",
                "", ScaleTypes.FIT));
        imagelist.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FAryan%202019%2FFielder.png?alt=media&token=439873fd-e4e1-4f42-92d9-67ada27f6443",
                "", ScaleTypes.FIT));
        imagelist.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FAryan%202019%2FKeeper.png?alt=media&token=3df42913-6ca5-4916-b073-cebab7c9e2a1",
                "", ScaleTypes.FIT));
        imagelist.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FAryan%202019%2FRunner.png?alt=media&token=21e82cfa-3293-400d-a50b-974f5d511657",
                "", ScaleTypes.FIT));
        imagelist.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FAryan%202019%2FSpinner.png?alt=media&token=5dc5cdd6-2d35-48f6-954b-b7a4a813cd31",
                "", ScaleTypes.FIT));
        imagelist.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FAryan%202019%2FWall.png?alt=media&token=a095cf2b-5a7c-4a44-b971-c659565e09f1",
                "", ScaleTypes.FIT));
        imagelist.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FAryan%202019%2FS-Wide.png?alt=media&token=ff967550-0fe5-4145-9ad0-2882b6a342ae",
                "", ScaleTypes.FIT));
        is19.setImageList(imagelist, ScaleTypes.FIT);

        final List<SlideModel> imagelist2 = new ArrayList<>();
        imagelist2.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FAryan%202020%2F20210121_142743_0000.png?alt=media&token=f9096198-3599-4ed9-b797-2dc78508e426",
                "", ScaleTypes.FIT));
        imagelist2.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FAryan%202020%2F20210121_142807_0000.png?alt=media&token=800b25b5-61b1-466e-82f3-ed100102fa87",
                "", ScaleTypes.FIT));
        imagelist2.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FAryan%202020%2F20210121_142831_0000.png?alt=media&token=25bd1aa6-c342-4dd3-acde-3ceaed8ce204",
                "", ScaleTypes.FIT));
        imagelist2.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FAryan%202020%2F20210121_142853_0000.png?alt=media&token=e2cd41cb-fe09-4425-8c96-474335a62179",
                "", ScaleTypes.FIT));
        imagelist2.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FAryan%202020%2F20210121_142910_0000.png?alt=media&token=875d3ce4-127c-457f-9306-9782eef34ddc",
                "", ScaleTypes.FIT));
        imagelist2.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FAryan%202020%2F20210121_145557_0000.png?alt=media&token=bd5ba993-1d8b-45ba-9205-c80a1cf5db3e",
                "", ScaleTypes.FIT));
        imagelist2.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FAryan%202020%2F20210121_150423_0000.png?alt=media&token=5c86088a-4775-4793-a6f0-dccccf411ffb",
                "", ScaleTypes.FIT));
        imagelist2.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/Awards%2FAryan%202020%2F20210121_150448_0000.png?alt=media&token=72f44970-2c6c-4aa2-aadb-d2722f783de6",
                "", ScaleTypes.FIT));

        is20.setImageList(imagelist2, ScaleTypes.FIT);

    }
}