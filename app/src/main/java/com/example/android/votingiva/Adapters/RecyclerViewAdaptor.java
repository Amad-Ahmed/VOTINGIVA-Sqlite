package com.example.android.votingiva.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.votingiva.Poll;
import com.example.android.votingiva.R;

import org.w3c.dom.Text;

import java.util.List;
//THIS CLASS IS FOR ROW.XML
public class RecyclerViewAdaptor extends RecyclerView.Adapter<RecyclerViewAdaptor.ViewHolder> {

    private Context context;
    private List<Poll> pollList;
    int id;
    double count1=1, count2 =1, count3 = 1, count4 = 1;
    boolean f1=true, f2=true, f3=true, f4=true;
    public RecyclerViewAdaptor(Context context, List<Poll> pollList) {
        this.context = context;
        this.pollList = pollList;
    }

    // OnCreateViewHolder -> where to get the single card as viewHolder object -> kis cheez ko bar bar dikhana ha
    @NonNull
    @Override
    public RecyclerViewAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    // What will happen after we created viewHolder object
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdaptor.ViewHolder holder, int position) {//for displaying
        Poll poll = pollList.get(position);
        holder.pollname.setText(poll.getPollName());
        holder.question.setText(poll.getQuestion());
        holder.optionA.setText(poll.getOp1());
        holder.optionB.setText(poll.getOp2());
        holder.optionC.setText(poll.getOp3());
        holder.optionD.setText(poll.getOp4());
        //id = pollList.get(getAdapterPosition()).getId();
    }

    // How many Items
    @Override
    public int getItemCount() {
        return pollList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{//for clicking
        public TextView pollname;
        public TextView question;
        public TextView optionA;
        public TextView optionB;
        public TextView optionC;
        public TextView optionD;
        public TextView voteA;
        public TextView voteB;
        public TextView voteC;
        public TextView voteD;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    id = pollList.get(getBindingAdapterPosition()).getId();
                }
            });
            //id = itemView.getId();
            pollname = itemView.findViewById(R.id.PName);
            question = itemView.findViewById(R.id.Question);
            optionA = itemView.findViewById(R.id.optA);
            optionB = itemView.findViewById(R.id.optB);
            optionC = itemView.findViewById(R.id.optC);
            optionD = itemView.findViewById(R.id.optD);
            voteA = itemView.findViewById(R.id.perA);
            voteB = itemView.findViewById(R.id.perB);
            voteC = itemView.findViewById(R.id.perC);
            voteD = itemView.findViewById(R.id.perD);

            optionA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                      String ID = id + "";
                      voteA.setText(ID);
//                    if(f1){
//                        count1++;
//                        count2 = 1;
//                        count3 = 1;
//                        count4 = 1;
//                        f1 = false;
//                        f2 = true;
//                        f3 = true;
//                        f4 = true;
//                        double total = count1 + count2 + count3 + count4;
//                        String per1 = String.valueOf(((count1/total)*100));
//                        String per2 = String.valueOf(((count2/total)*100));
//                        String per3 = String.valueOf(((count3/total)*100));
//                        String per4 = String.valueOf(((count4/total)*100));
//                        voteA.setText(per1);
//                    }
                }
            });

            optionB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(f2){
                        count1 = 1;
                        count2++;
                        count3 = 1;
                        count4 = 1;
                        f1 = true;
                        f2 = false;
                        f3 = true;
                        f4 = true;
                        double total = count1 + count2 + count3 + count4;
                        String per1 = String.valueOf(((count1/total)*100));
                        String per2 = String.valueOf(((count2/total)*100));
                        String per3 = String.valueOf(((count3/total)*100));
                        String per4 = String.valueOf(((count4/total)*100));
                        voteB.setText(per2);
                    }
                }
            });

            optionC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(f3){
                        count1 = 1;
                        count2 = 1;
                        count3++;
                        count4 = 1;
                        f1 = true;
                        f2 = true;
                        f3 = false;
                        f4 = true;
                        double total = count1 + count2 + count3 + count4;
                        String per1 = String.valueOf(((count1/total)*100));
                        String per2 = String.valueOf(((count2/total)*100));
                        String per3 = String.valueOf(((count3/total)*100));
                        String per4 = String.valueOf(((count4/total)*100));
                        voteC.setText(per3);
                    }
                }
            });

            optionD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(f4){
                        count1 = 1;
                        count2 = 1;
                        count3 = 1;
                        count4++;
                        f1 = true;
                        f2 = true;
                        f3 = true;
                        f4 = false;
                        double total = count1 + count2 + count3 + count4;
                        String per1 = String.valueOf(((count1/total)*100));
                        String per2 = String.valueOf(((count2/total)*100));
                        String per3 = String.valueOf(((count3/total)*100));
                        String per4 = String.valueOf(((count4/total)*100));
                        voteD.setText(per4);
                    }
                }
            });
        }

        @Override
        public void onClick(View view) {
            Log.d("clickFrom", "clicked");
        }
    }

}
