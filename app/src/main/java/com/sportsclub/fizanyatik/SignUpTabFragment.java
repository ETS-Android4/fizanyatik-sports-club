package com.sportsclub.fizanyatik;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;

public class SignUpTabFragment extends Fragment {

    private TextInputEditText email, password, name, username;
    private FirebaseAuth mauth;
    DatabaseReference mbase;
    float v = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.sign_up_fragment, container, false);

        email = root.findViewById(R.id.emailsign);
        password = root.findViewById(R.id.passwordsign);
        name = root.findViewById(R.id.namesign);
        username = root.findViewById(R.id.usersign);
        TextView signup = root.findViewById(R.id.signup);
        mauth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String semail = email.getText().toString();
                final String spassword = password.getText().toString();
                final String susername = username.getText().toString();
                final String sname = name.getText().toString();

                if(semail.isEmpty() || spassword.isEmpty() || sname.isEmpty() || susername.isEmpty()){
                    Toast.makeText(getContext(), "Check your details", Toast.LENGTH_LONG).show();
                } else if (password.length() < 6) {
                    Toast.makeText(getContext(), "Password must be at least 6 characters long", Toast.LENGTH_LONG).show();
                } else{
                    CreateUserAccount();
                }
            }
        });

        return root;
    }

    private void CreateUserAccount() {

        MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(getContext());
        dialogBuilder.setView(R.layout.progress_material)
        .setCancelable(false).create();

        AlertDialog materialDialogs = dialogBuilder.create();
        materialDialogs.show();
        mauth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    mbase = FirebaseDatabase.getInstance().getReference("Fusers").child(mauth.getCurrentUser().getUid());
                    final HashMap<String, String> hashMap = new HashMap<>();

                    hashMap.put("id", mauth.getCurrentUser().getUid());
                    hashMap.put("username", name.getText().toString());
                    hashMap.put("imageURL", "default");
                    hashMap.put("status", "offline");
                    hashMap.put("bio", username.getText().toString());
                    hashMap.put("search", name.getText().toString().toLowerCase());
                    hashMap.put("name", name.getText().toString());
                    hashMap.put("email", email.getText().toString());
                    mbase.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task1) {
                            if (task1.isSuccessful()){
                                Intent home = new Intent(getActivity(), HomeActivity.class);
                                Toast.makeText(getContext(), "Sign up successful", Toast.LENGTH_SHORT).show();
                                materialDialogs.dismiss();
                                FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            MaterialAlertDialogBuilder alertDialog = new MaterialAlertDialogBuilder(getContext());
                                            alertDialog.setTitle("Verification");
                                            alertDialog.setMessage("A Verification mail has been sent to your Email Address. Please Verify your Email by following the instructions given in the respective mail.");
                                            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    startActivity(home);
                                                    getActivity().finish();
                                                }
                                            }).setCancelable(false)
                                                    .create();
                                            alertDialog.show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(getContext(), "Sign up unsuccessful. " + task1.getException(), Toast.LENGTH_LONG).show();
                                materialDialogs.dismiss();
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(getContext(), "Sign up unsuccessful. " + task.getException(), Toast.LENGTH_LONG).show();
                    materialDialogs.dismiss();
                }
            }
        });
    }
}
