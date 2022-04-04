package com.sportsclub.fizanyatik.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sportsclub.fizanyatik.Image_show;
import com.sportsclub.fizanyatik.R;
import com.sportsclub.fizanyatik.ListItemChat;
import com.zolad.zoominimageview.ZoomInImageViewAttacher;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

public class AppChatAdapter extends RecyclerView.Adapter{

    List<ListItemChat> listViewsChat;
    private Context context;
    DatabaseReference firebaseDatabase;
    String userdata = FirebaseAuth.getInstance().getCurrentUser().getUid();

    public AppChatAdapter(List<ListItemChat> listViewsChat, Context context) {
        this.listViewsChat = listViewsChat;
        this.context = context;
    }

    public String convertTime(String time){
        SimpleDateFormat formatter = new SimpleDateFormat("h:mm a");
        String dateString = formatter.format(new Date(Long.parseLong(time)));
        return dateString;
    }

    @Override
    public int getItemViewType(int position) {
        if(listViewsChat.get(position).getObjectId().equals(userdata) && listViewsChat.get(position).getType().equals("1")){
            return 0;
        } else if(listViewsChat.get(position).getObjectId().equals(userdata) && listViewsChat.get(position).getType().equals("2")){
            return 1; 
        } else if(!listViewsChat.get(position).getObjectId().equals(userdata) && listViewsChat.get(position).getType().equals("1")){
            return 2;
        }
        return 3;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;
        if(viewType == 0){
            view = layoutInflater.inflate(R.layout.sender_chat, parent, false);
            return new ViewHolder1(view);
        } else if(viewType == 2){
        view = layoutInflater.inflate(R.layout.reciever_chat, parent, false);
        return new ViewHolder2(view);
        } else if(viewType == 1){
            view = layoutInflater.inflate(R.layout.sender_image, parent, false);
            return new ViewHolder3(view);
        }
        view = layoutInflater.inflate(R.layout.receiver_image, parent, false);
        return new ViewHolder4(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        if(listViewsChat.get(position).getObjectId().equals(userdata) && listViewsChat.get(position).getType().equals("1")){
            ViewHolder1 viewHolder1 = (ViewHolder1) holder;
            viewHolder1.sendermessage.setText(listViewsChat.get(position).getMessage());
            viewHolder1.linearLayout.setOnLongClickListener(v -> {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(v.getContext());
                builder.setMessage("Are you sure want to delete this message from Chats?");
                builder.setCancelable(true);
                builder.setTitle("Delete?");
                builder.setPositiveButton("Delete", (dialog, which) -> {
                    FirebaseDatabase.getInstance().getReference("FChatsG").child(listViewsChat.get(position).getRoot()).removeValue();
                    Toast.makeText(context, "Deleted successfully", Toast.LENGTH_SHORT).show();
                })
                        .setNegativeButton("Cancel", null).create().show();
                return false;
            });
        }

        else if(!listViewsChat.get(position).getObjectId().equals(userdata) && listViewsChat.get(position).getType().equals("1")){
            final ViewHolder2 viewHolder2 = (ViewHolder2) holder;
            viewHolder2.receiverusername.setText(listViewsChat.get(position).getUserchatname());
            viewHolder2.receivermessage.setText(listViewsChat.get(position).getMessage());

            if (listViewsChat.get(position).getImageURL().equals("default")) {
                viewHolder2.chat_profile.setImageResource(R.drawable.picsign);
            } else {
                Glide.with(context).load(listViewsChat.get(position).getImageURL()).into(viewHolder2.chat_profile);
            }

            firebaseDatabase = FirebaseDatabase.getInstance().getReference("Fusers").child(listViewsChat.get(position).getObjectId());
        }

        else if(listViewsChat.get(position).getObjectId().equals(userdata) && listViewsChat.get(position).getType().equals("2")){
            final ViewHolder3 viewHolder3 = (ViewHolder3) holder;

            Glide.with(context).load(listViewsChat.get(position).getMessage()).into(viewHolder3.sendermessagepic);
            viewHolder3.sendermessagepic.setVisibility(View.VISIBLE);
            ZoomInImageViewAttacher zoom = new ZoomInImageViewAttacher();
            zoom.attachImageView(viewHolder3.sendermessagepic);

            viewHolder3.sendermessagepic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Image_show.class);
                    intent.putExtra("images", listViewsChat.get(position).getMessage());
                    intent.putExtra("date", convertTime(listViewsChat.get(position).getTime()));
                    intent.putExtra("name", listViewsChat.get(position).getUserchatname());
                    intent.putExtra("imgname", listViewsChat.get(position).getImgname());
                    Pair[] pairs = new Pair[1];
                    pairs[0] = new Pair<View, String>(viewHolder3.sendermessagepic, "image_show");
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) v.getContext(), pairs);
                    v.getContext().startActivity(intent, options.toBundle());
                }
            });

            viewHolder3.sendermessagepic.setOnLongClickListener(v -> {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(v.getContext());
                builder.setMessage("Are you sure want to delete this message from Chats?");
                builder.setCancelable(true);
                builder.setTitle("Delete?");
                builder.setPositiveButton("Delete", (dialog, which) -> {
                    FirebaseDatabase.getInstance().getReference("FChatsG").child(listViewsChat.get(position).getRoot()).removeValue();
                    Toast.makeText(context, "Deleted successfully", Toast.LENGTH_SHORT).show();
                })
                        .setNegativeButton("Cancel", null).create().show();
                return false;
            });
        }

        else {
            final ViewHolder4 viewHolder4 = (ViewHolder4) holder;
            viewHolder4.receiverusernamepic.setText(listViewsChat.get(position).getUserchatname());

            firebaseDatabase = FirebaseDatabase.getInstance().getReference("Fusers").child(listViewsChat.get(position).getObjectId());

            if (listViewsChat.get(position).getImageURL().equals("default")) {
                viewHolder4.image_profile.setImageResource(R.drawable.picsign);
            } else {
                Glide.with(context).load(listViewsChat.get(position).getImageURL()).into(viewHolder4.image_profile);
            }

            Glide.with(context).load(listViewsChat.get(position).getMessage()).into(viewHolder4.receivermessagepic);
            viewHolder4.receivermessagepic.setVisibility(View.VISIBLE);
            ZoomInImageViewAttacher zoom = new ZoomInImageViewAttacher();
            zoom.attachImageView(viewHolder4.receivermessagepic);

            viewHolder4.receivermessagepic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Image_show.class);
                    intent.putExtra("images", listViewsChat.get(position).getMessage());
                    intent.putExtra("date", convertTime(listViewsChat.get(position).getTime()));
                    intent.putExtra("name", listViewsChat.get(position).getUserchatname());
                    Pair[] pairs = new Pair[1];
                    pairs[0] = new Pair<View, String>(viewHolder4.receivermessagepic, "image_show");
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) v.getContext(), pairs);
                    v.getContext().startActivity(intent, options.toBundle());
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return listViewsChat.size();
    }

    class ViewHolder1 extends RecyclerView.ViewHolder{

        TextView sendermessage;
        LinearLayout linearLayout;
        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            sendermessage = itemView.findViewById(R.id.usermessagechat);
            linearLayout = itemView.findViewById(R.id.linearLayout_sender);
        }
    }

    class ViewHolder2 extends RecyclerView.ViewHolder{

        TextView receiverusername;
        TextView receivermessage;
        CircleImageView chat_profile;
        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            receiverusername = itemView.findViewById(R.id.receiverchatname);
            receivermessage = itemView.findViewById(R.id.receivermessagechat);
            chat_profile = itemView.findViewById(R.id.chat_profile);
        }
    }
    
    class ViewHolder3 extends RecyclerView.ViewHolder{
        
        ImageView sendermessagepic;
        ProgressBar lottie_sender;
        public ViewHolder3(@NonNull View itemView){
            super(itemView);
            lottie_sender = itemView.findViewById(R.id.lottie_sender);
            sendermessagepic = itemView.findViewById(R.id.usermessagechat_img);
        }
    }

    class ViewHolder4 extends RecyclerView.ViewHolder{

        ImageView receivermessagepic;
        TextView receiverusernamepic;
        ProgressBar lottierecieverpic;
        CircleImageView image_profile;

        public ViewHolder4(@NonNull View itemView){
            super(itemView);
            lottierecieverpic = itemView.findViewById(R.id.lottie_reciever);
            receiverusernamepic = itemView.findViewById(R.id.receiverchatname_img);
            receivermessagepic = itemView.findViewById(R.id.receivermessagechat_img);
            image_profile = itemView.findViewById(R.id.image_profile);
        }
    }
}

