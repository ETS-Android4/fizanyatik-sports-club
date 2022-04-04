package com.sportsclub.fizanyatik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.sportsclub.fizanyatik.Adapter.AppChatAdapter;
import com.sportsclub.fizanyatik.Fragments.APIService;
import com.sportsclub.fizanyatik.Notifications.Client;
import com.vanniktech.emoji.EmojiEditText;
import com.vanniktech.emoji.EmojiPopup;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Group extends AppCompatActivity {

    private RecyclerView chat_recyclerview;
    private ImageView emojipng;
    CardView card_progressg;
    private List<ListItemChat> listItemsChat;
    private RecyclerView.Adapter chatappadapter;
    APIService apiService;
    private static final int IMAGE_REQUEST = 1;
    private Uri imageUri;
    StorageReference storageReference;
    private StorageTask uploadTask;
    String mUri;
    private EmojiEditText sendchatedittext;
    private String  fullName, profile_img, imgname = "null";
    private FirebaseUser fuser;
    private DatabaseReference reference;
    boolean notify = false;
    boolean notifyg = false;

    @Override
    public void onBackPressed(){
        finish();
    }

    @SuppressLint({"ResourceAsColor", "NotifyDataSetChanged"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        fuser = FirebaseAuth.getInstance().getCurrentUser();
        View rootview = findViewById(R.id.rootview_chat);
        card_progressg = findViewById(R.id.card_progressg);
        reference = FirebaseDatabase.getInstance().getReference("Fusers").child(fuser.getUid());

        apiService = Client.getClient("https://fcm.googleapis.com/").create(APIService.class);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                fullName = snapshot.child("name").getValue().toString();
                profile_img = snapshot.child("imageURL").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        ImageView sendchat = findViewById(R.id.sendchatbutton);

        ImageView imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(v -> {
            notifyg = true;
            openImage();
        });

        sendchatedittext = findViewById(R.id.sendchatedittext);
        listItemsChat = new ArrayList<>();
        emojipng = findViewById(R.id.emojipng);

        final EmojiPopup emojiPopup = EmojiPopup.Builder.fromRootView(rootview).build(sendchatedittext);
        emojipng.setOnClickListener(v -> {
            if(emojiPopup.isShowing()){
                emojipng.setImageURI(Uri.parse("android.resource://com.sportsclub.fizanyatik/drawable/icon_emoji"));
            } else {
                emojipng.setImageURI(Uri.parse("android.resource://com.sportsclub.fizanyatik/drawable/icon_keyboard"));
            }
            emojiPopup.toggle();
        });


        chat_recyclerview = findViewById(R.id.recycler_chat);
        chat_recyclerview.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        chat_recyclerview.setLayoutManager(linearLayoutManager);
        chatappadapter = new AppChatAdapter(listItemsChat, getApplicationContext());
        chat_recyclerview.setAdapter(chatappadapter);

        ImageView back_grp = findViewById(R.id.back_grp);
        back_grp.setOnClickListener(v -> Group.super.onBackPressed());

        FirebaseDatabase.getInstance().getReference("FChatsG").orderByChild("time").addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listItemsChat.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String name = snapshot.child("senderName").getValue().toString();
                    String id = snapshot.child("senderId").getValue().toString();
                    String type = snapshot.child("type").getValue().toString();
                    String message = snapshot.child("message").getValue().toString();
                    String root = snapshot.child("root").getValue().toString();
                    String time = snapshot.child("time").getValue().toString();
                    String imgname = snapshot.child("imgname").getValue().toString();
                    String imageURL = snapshot.child("imageURL").getValue().toString();
                    ListItemChat listItemChat = new ListItemChat(message, name, id, type, root, time, imageURL, imgname);
                    if(!listItemsChat.contains(listItemChat)){
                        listItemsChat.add(listItemChat);
                        chatappadapter.notifyDataSetChanged();
                        chat_recyclerview.scrollToPosition(listItemsChat.size() - 1);
                        card_progressg.setVisibility(View.GONE);
                        chat_recyclerview.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("TAG", "Read failed");
            }
        });

        sendchat.setOnClickListener(v -> {
            notify = true;
            final String messageString = sendchatedittext.getText().toString();
            if(messageString.isEmpty()){
                Toast.makeText(getApplicationContext(), "Type something", Toast.LENGTH_SHORT).show();
            } else{
                final Date currentTime = Calendar.getInstance().getTime();
                DatabaseReference refe = FirebaseDatabase.getInstance().getReference("FChatsG").child(fuser.getUid() + currentTime);
                HashMap<String, Object> hashMap = new HashMap<>();
                imgname = fuser.getUid() + currentTime.toString()
                        +"."+getFileExtension(imageUri);
                hashMap.put("senderName", fullName);
                hashMap.put("message", messageString);
                hashMap.put("senderId", fuser.getUid());
                hashMap.put("type", "1");
                hashMap.put("root", fuser.getUid() + currentTime.toString());
                hashMap.put("time", (0 - new Date().getTime()) + "");
                hashMap.put("imageURL", profile_img);
                hashMap.put("imgname", "null");

                refe.setValue(hashMap);
                ListItemChat listItem3 = new ListItemChat(messageString, fullName, fuser.getUid(), "1", fuser.getUid() + currentTime.toString(), (0 - new Date().getTime()) + "", profile_img, imgname);
                sendchatedittext.setText("");
                chatappadapter.notifyDataSetChanged();
                listItemsChat.add(listItem3);
                chat_recyclerview.scrollToPosition(listItemsChat.size());

                reference = FirebaseDatabase.getInstance().getReference("Fusers").child(fuser.getUid());
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    private void openImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_REQUEST);
    }

    private String getFileExtension(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void uploadImage(){

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setIndeterminateDrawable(getResources().getDrawable(R.drawable.ic_picture));
        pd.setMessage("Uploading...");
        pd.show();

        if (imageUri != null){
            final Date currentTime = Calendar.getInstance().getTime();
            storageReference = FirebaseStorage.getInstance().getReference("fuploads");
            final StorageReference fileReference = storageReference.child(fuser.getUid() + currentTime.toString()
                    +"."+getFileExtension(imageUri));

            uploadTask = fileReference.putFile(imageUri);
            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()){
                        throw  task.getException();
                    }

                    return  fileReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()){
                        Uri downloadUri = task.getResult();
                        mUri = downloadUri.toString();

                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("FChatsG").child(fuser.getUid() + currentTime.toString());
                        HashMap<String, Object> hashMap = new HashMap<>();

                        hashMap.put("senderName", fullName);
                        hashMap.put("message", ""+mUri);
                        hashMap.put("senderId", fuser.getUid());
                        hashMap.put("type", "2");
                        hashMap.put("root", fuser.getUid() + currentTime.toString());
                        hashMap.put("time", (0 - new Date().getTime()) + "");
                        hashMap.put("imageURL", profile_img);
                        hashMap.put("imgname", fuser.getUid() + currentTime.toString()
                                +"."+getFileExtension(imageUri));
                        reference.setValue(hashMap);
                        reference = FirebaseDatabase.getInstance().getReference("Fusers").child(fuser.getUid());
                        reference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                            }
                        });
                    }

                    else {
                        Toast.makeText(getApplicationContext(), "Failed!", Toast.LENGTH_SHORT).show();
                    }
                    pd.dismiss();
                }

            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            });
        }

        else {
            Toast.makeText(getApplicationContext(), "No image selected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null){
            imageUri = data.getData();

            if (uploadTask != null && uploadTask.isInProgress()){
                Toast.makeText(getApplicationContext(), "Upload in progress", Toast.LENGTH_SHORT).show();
            }

            else {
                uploadImage();
            }
        }
    }
}
