package com.example.android.votingiva.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.votingiva.Noti;
import com.example.android.votingiva.Poll;
import com.example.android.votingiva.R;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Random;

public class RecyclerNoti extends RecyclerView.Adapter<RecyclerNoti.ViewHolder> {
    private Context context;
    private List<Noti> NotiList;
    Random r;
    Integer[] images = {
            R.drawable.ic_avatar1,
            R.drawable.ic_avatar2,
            R.drawable.ic_avatar3,
            R.drawable.ic_avatar4,
            R.drawable.ic_avatar5,
            R.drawable.ic_avatar6,
            R.drawable.ic_avatar7,
            R.drawable.ic_avatar8,
            R.drawable.ic_avatar9,
            R.drawable.ic_avatar10,
            R.drawable.ic_avatar12,
            R.drawable.ic_avatar13,
            R.drawable.ic_avatar15,
            R.drawable.ic_avatar16,
            R.drawable.ic_avatar17
    };

    public RecyclerNoti(Context context, List<Noti> notiList) {
        this.context = context;
        NotiList = notiList;
    }

    @NonNull
    @Override
    public RecyclerNoti.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.noti_row, parent, false);
        r = new Random();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Noti noti = NotiList.get(position);
        holder.UserName.setText(noti.getUname());
        holder.PollName.setText(noti.getPname());
        holder.imageView.setImageResource(images[r.nextInt(images.length)]);

    }

    @Override
    public int getItemCount(){
        return NotiList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView UserName;
        public TextView PollName;
        public ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            UserName = itemView.findViewById(R.id.UserVoted);
            PollName = itemView.findViewById(R.id.PollVoted);
            imageView = itemView.findViewById(R.id.prof);
        }

        @Override
        public void onClick(View view) {

        }
    }

}
