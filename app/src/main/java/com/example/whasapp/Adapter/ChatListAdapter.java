package com.example.whasapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.whasapp.Model.ChatList;
import com.example.whasapp.R;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

public class ChatListAdapter  extends RecyclerView.Adapter<ChatListAdapter.Holder> {

    private List<ChatList> list;
    private Context context;

    public ChatListAdapter(List<ChatList> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from( context ).inflate( R.layout.layout_chat_list,parent, false );
      return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        ChatList chatList = list.get(position);
        holder.tvName.setText(chatList.getUserName());
        holder.tvDate.setText(chatList.getDate());
        holder.tvDesc.setText(chatList.getDescription());


        Glide.with( context ).load( chatList.getUrlProfile()).into( holder.profile );



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView tvName, tvDate,tvDesc;
        private CircularImageView profile;
        private ImageView arrow;
        public Holder(@NonNull View itemView) {
            super( itemView );

            tvDate = itemView.findViewById(R.id.tv_date);
            tvName = itemView.findViewById(R.id.tv_name);
            profile = itemView.findViewById(R.id.image_profile);
            tvDesc = itemView.findViewById(R.id.tv_desc);
          //  arrow = itemView.findViewById(R.id.img_arrow);
        }
    }
}
