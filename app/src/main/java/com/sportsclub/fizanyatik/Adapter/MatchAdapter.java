package com.sportsclub.fizanyatik.Adapter;

import static android.os.Environment.DIRECTORY_DOCUMENTS;
import static android.os.Environment.DIRECTORY_DOWNLOADS;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sportsclub.fizanyatik.Model.MatchList;
import com.sportsclub.fizanyatik.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchViewHolder>{

    private Context context;
    private List<MatchList> matchLists;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    public MatchAdapter(Context context, List<MatchList> matchLists) {
        this.context = context;
        this.matchLists = matchLists;
    }

    @NonNull
    @Override
    public MatchAdapter.MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.match_layout, parent, false);
        return new MatchAdapter.MatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.team1_name.setText(matchLists.get(position).getTeam1_name());
        holder.team2_name.setText(matchLists.get(position).getTeam2_name());
        holder.team1_score.setText(matchLists.get(position).getTeam1_score());
        holder.team2_score.setText(matchLists.get(position).getTeam2_score());
        holder.team1_over.setText(matchLists.get(position).getTeam1_over());
        holder.team2_over.setText(matchLists.get(position).getTeam2_over());
        holder.match_result.setText(matchLists.get(position).getMatch_result());

        Glide.with(context).load(matchLists.get(position).getTeam1_img()).into(holder.team1_img);
        Glide.with(context).load(matchLists.get(position).getTeam2_img()).into(holder.team2_img);

        holder.more_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(matchLists.get(position).getMore_btn().equals("no")){
                    Toast.makeText(context, "Not uploaded yet", Toast.LENGTH_LONG).show();
                } else{
                firebaseStorage = FirebaseStorage.getInstance();
                storageReference = firebaseStorage.getReference("match").child(matchLists.get(position).getMore_btn());
                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Toast.makeText(context, "Download has started", Toast.LENGTH_SHORT).show();
                        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
                        DownloadManager.Request request = new DownloadManager.Request(uri);
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        request.setDestinationInExternalFilesDir(context, DIRECTORY_DOCUMENTS, matchLists.get(position).getTeam1_name() + matchLists.get(position).getTeam2_name() + "_" + matchLists.get(position).getMatch_result() + ".pdf");
                        downloadManager.enqueue(request);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Download failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }}
        });
    }

    @Override
    public int getItemCount() {
        return matchLists.size();
    }

    public class MatchViewHolder extends RecyclerView.ViewHolder{

        private TextView team1_name, team2_name, team1_score, team2_score, team1_over, team2_over, match_result, more_btn;
        private ImageView team1_img, team2_img;

        public MatchViewHolder(View itemView) {
            super(itemView);

            team1_img = itemView.findViewById(R.id.team1_img);
            team1_name = itemView.findViewById(R.id.team1_name);
            team1_score = itemView.findViewById(R.id.team1_score);
            team1_over = itemView.findViewById(R.id.team1_over);
            team2_img = itemView.findViewById(R.id.team2_img);
            team2_name = itemView.findViewById(R.id.team2_name);
            team2_score = itemView.findViewById(R.id.team2_score);
            team2_over = itemView.findViewById(R.id.team2_over);
            match_result = itemView.findViewById(R.id.match_result);
            more_btn = itemView.findViewById(R.id.more_btn);

        }
    }

}
