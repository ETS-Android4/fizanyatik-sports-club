package com.sportsclub.fizanyatik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shreyaspatil.MaterialDialog.BottomSheetMaterialDialog;
import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;
import com.wessam.library.NetworkChecker;

public class HomeActivity extends AppCompatActivity {

    private String version, latest_v, link;
    BottomNavigationView mBottomNavigationView;
    private DatabaseReference firebaseDatabase, fdata;
    CoordinatorLayout coordinatorLayout;

    @Override
    public void onBackPressed(){
        BottomSheetMaterialDialog mDialog = new BottomSheetMaterialDialog.Builder(this)
                .setTitle("Exit?")
                .setMessage("Do you want to exit the Fizanyatik Sports Club app?")
                .setCancelable(false)
                .setPositiveButton("Yes", R.drawable.ic_round_done_24, new MaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        HomeActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", R.drawable.ic_outline_cancel_24, new MaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                    }
                })
                .setAnimation(R.raw.exit_app)
                .build();
        mDialog.getAnimationView().setScaleType(ImageView.ScaleType.FIT_CENTER);
        mDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        coordinatorLayout = findViewById(R.id.coordinator_layout);

        if(!NetworkChecker.isNetworkConnected(this)){
            Snackbar.make(coordinatorLayout, "No internet connection.", Snackbar.LENGTH_LONG)
                    .setAction("Dismiss", null).setActionTextColor(getResources().getColor(R.color.ui_accent3)).setTextColor(getResources().getColor(R.color.text_main)).setBackgroundTint(getResources().getColor(R.color.back_dark)).show();
        }

        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        mBottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        mBottomNavigationView.setSelectedItemId(R.id.bottom_home);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) mBottomNavigationView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationViewBehavior());

        firebaseDatabase = FirebaseDatabase.getInstance().getReference("Fversion");
        firebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                latest_v = snapshot.getValue().toString();

                try {
                    PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                    version = pInfo.versionName;
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }

                if(!version.equals(latest_v)){
                    fdata = FirebaseDatabase.getInstance().getReference("FLink");
                    fdata.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            link = snapshot.getValue().toString();

                            MaterialDialog matDialog = new MaterialDialog.Builder(HomeActivity.this)
                                    .setTitle("New Version Available!")
                                    .setMessage("Do you want to update your app to get latest features?")
                                    .setCancelable(false)
                                    .setPositiveButton("Download", R.drawable.ic_round_done_24, new MaterialDialog.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int which) {
                                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                                            startActivity(intent);
                                            dialogInterface.dismiss();
                                        }
                                    })
                                    .setNegativeButton("Skip", R.drawable.ic_outline_cancel_24, new MaterialDialog.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int which) {
                                            dialogInterface.dismiss();
                                        }
                                    })
                                    .setAnimation(R.raw.update)
                                    .build();
                            matDialog.show();

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()){
                        case R.id.bottom_home:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                            break;

                        case R.id.bottom_matches:
                            if(!NetworkChecker.isNetworkConnected(HomeActivity.this)){
                                mBottomNavigationView.setSelectedItemId(R.id.bottom_home);
                                Snackbar.make(coordinatorLayout, "No internet connection.", Snackbar.LENGTH_LONG)
                                        .setAction("Dismiss", null).setActionTextColor(getResources().getColor(R.color.ui_accent3)).setTextColor(getResources().getColor(R.color.text_main)).setBackgroundTint(getResources().getColor(R.color.back_dark)).show();
                            } else {
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MatchFragment()).commit();
                            }
                            break;

                        case R.id.bottom_chats:
                            if(!NetworkChecker.isNetworkConnected(HomeActivity.this)){
                                Snackbar.make(coordinatorLayout, "No internet connection.", Snackbar.LENGTH_LONG)
                                        .setAction("Dismiss", null).setActionTextColor(getResources().getColor(R.color.ui_accent3)).setTextColor(getResources().getColor(R.color.text_main)).setBackgroundTint(getResources().getColor(R.color.back_dark)).show();
                            } else {
                                startActivity(new Intent(HomeActivity.this, ChatActivity.class));
                            }
                            mBottomNavigationView.setSelectedItemId(R.id.bottom_home);
                            break;

                        case R.id.bottom_feeds:
                            if(!NetworkChecker.isNetworkConnected(HomeActivity.this)){
                                mBottomNavigationView.setSelectedItemId(R.id.bottom_home);
                                Snackbar.make(coordinatorLayout, "No internet connection.", Snackbar.LENGTH_LONG)
                                        .setAction("Dismiss", null).setActionTextColor(getResources().getColor(R.color.ui_accent3)).setTextColor(getResources().getColor(R.color.text_main)).setBackgroundTint(getResources().getColor(R.color.back_dark)).show();
                            } else {
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FeedsFragment()).commit();
                            }
                            break;
                    }
                    return true;
                }
            };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}