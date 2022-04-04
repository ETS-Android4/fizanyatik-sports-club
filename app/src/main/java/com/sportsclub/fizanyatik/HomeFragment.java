package com.sportsclub.fizanyatik;

import static android.os.Environment.DIRECTORY_DOCUMENTS;
import static android.os.Environment.DIRECTORY_DOWNLOADS;

import android.app.ActivityOptions;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    ImageSlider sliderView;
    CardView apxi, psxi, rulebook;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    private BottomNavigationView navigationBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        navigationBar = getActivity().findViewById(R.id.bottom_nav);
        sliderView = root.findViewById(R.id.image_slider);
        rulebook = root.findViewById(R.id.rulebook_cv);

        rulebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseStorage = FirebaseStorage.getInstance();
                storageReference = firebaseStorage.getReference("uploads").child("FSC Rulebook 2nd Edition.pdf");
                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Toast.makeText(getContext(), "Download has started", Toast.LENGTH_SHORT).show();
                            DownloadManager downloadManager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
                            DownloadManager.Request request = new DownloadManager.Request(uri);
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                            request.setDestinationInExternalFilesDir(getContext(), DIRECTORY_DOWNLOADS, "FSC Rulebook 2nd Edition.pdf");
                            downloadManager.enqueue(request);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "Download failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }});

        apxi = root.findViewById(R.id.card_apxi);
        psxi = root.findViewById(R.id.card_psxi);

        ImageView image = root.findViewById(R.id.apxi_photo);
        ImageView image1 = root.findViewById(R.id.psxi_photo);

        apxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getActivity(), APXI.class);
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(image, "logo_image");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), pairs);
                startActivity(mainIntent, options.toBundle());
            }
        });

        psxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getActivity(), PSXI.class);
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(image1, "psxi_logo");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), pairs);
                startActivity(mainIntent, options.toBundle());
            }
        });

        root.findViewById(R.id.nested_scroll_view).setFocusable(false);
        root.findViewById(R.id.image_slider).requestFocus();

        final List<SlideModel> imagelist = new ArrayList<>();
        imagelist.add(new SlideModel(R.drawable.slider1,
                "", ScaleTypes.FIT));
        imagelist.add(new SlideModel(R.drawable.slider2,
                "", ScaleTypes.FIT));
        imagelist.add(new SlideModel(R.drawable.slider3,
                "", ScaleTypes.FIT));
        imagelist.add(new SlideModel(R.drawable.slider4,
                "", ScaleTypes.FIT));
        imagelist.add(new SlideModel(R.drawable.slider5,
                "", ScaleTypes.FIT));
        sliderView.setImageList(imagelist, ScaleTypes.FIT);

        sliderView.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemSelected(int i) {
                if(i == 0) {
                }
                if(i == 1) {
                    startActivity(new Intent(getActivity(), ChatActivity.class));
                }
                if(i == 2) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://bit.ly/fizantofuzzsite"));
                    startActivity(intent);
                }
                if(i == 3) {
                    startActivity(new Intent(getActivity(), PrateekSinha.class));
                }
                if(i == 4) {
                    startActivity(new Intent(getActivity(), PranjalSingh.class));
                }
            }
        });

        return root;

    }
}
