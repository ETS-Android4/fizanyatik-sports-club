package com.sportsclub.fizanyatik;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class MatchDialog extends DialogFragment {

    private EditText apxi_score, apxi_over, psxi_score, psxi_over, match_result;
    private String as, ao, ps, po, ms;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view =inflater.inflate(R.layout.match_dialog, null);

        apxi_score = view.findViewById(R.id.apxi_score);
        apxi_over = view.findViewById(R.id.apxi_over);
        psxi_score = view.findViewById(R.id.psxi_score);
        psxi_over = view.findViewById(R.id.psxi_over);
        match_result = view.findViewById(R.id.match_ending);

        builder.setView(view)
                .setTitle("Add Match")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        as = apxi_score.getText().toString();
                        ao = apxi_over.getText().toString();
                        ps = psxi_score.getText().toString();
                        po = psxi_over.getText().toString();
                        ms = match_result.getText().toString();

                        if(!(as.equals("") || ao.equals("") || ps.equals("") || po.equals("") || ms.equals(""))){
                            final Date currentTime = Calendar.getInstance().getTime();

                            DatabaseReference matchreff = FirebaseDatabase.getInstance().getReference().child("Match").child("Match " + currentTime.toString());

                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("team1_img", "https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/match%2Fapxi.png?alt=media&token=543fd614-5523-4ad2-9452-762dd01b3bbe");
                            hashMap.put("team1_name", "APXI");
                            hashMap.put("team1_score", as);
                            hashMap.put("team1_over", ao);
                            hashMap.put("team2_img", "https://firebasestorage.googleapis.com/v0/b/fizanto-fuzz.appspot.com/o/match%2Fpsxi.png?alt=media&token=732d14bd-a07e-4bae-93d6-331caadb3b91");
                            hashMap.put("team2_name", "PSXI");
                            hashMap.put("team2_score", ps);
                            hashMap.put("team2_over", po);
                            hashMap.put("time", (0 - new Date().getTime()) + "");
                            hashMap.put("match_result", ms);
                            hashMap.put("more_btn", "no");

                            matchreff.setValue(hashMap);

                        } else {
                            Toast.makeText(getContext(), "Invalid details entered", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

        return builder.create();
    }
}
