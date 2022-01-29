package com.example.android.votingiva.Adapters;

import static android.content.Context.MODE_PRIVATE;

import static java.security.AccessController.getContext;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.votingiva.BarActivity;
import com.example.android.votingiva.CreatePollActivity;
import com.example.android.votingiva.Poll;
import com.example.android.votingiva.R;
import com.example.android.votingiva.password_db;

import org.w3c.dom.Text;

import java.util.List;
//THIS CLASS IS FOR ROW.XML
public class RecyclerViewAdaptor extends RecyclerView.Adapter<RecyclerViewAdaptor.ViewHolder> {

    public Context context;
    public List<Poll> pollList;
    int id;
    double total;
    password_db db;
    int count1=0, count2=0, count3=0, count4=0;
    double per1=0, per2=0, per3=0, per4=0;
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
        db = new password_db(context);
        return new ViewHolder(view);
    }

    // What will happen after we created viewHolder object
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdaptor.ViewHolder holder, int position) {//for displaying
        Poll poll=pollList.get(holder.getAbsoluteAdapterPosition());

        holder.pollname.setText(poll.getPollName());
        holder.question.setText(poll.getQuestion());
        holder.optionA.setText(poll.getOp1());
        holder.optionB.setText(poll.getOp2());
        holder.optionC.setText(poll.getOp3());
        holder.optionD.setText(poll.getOp4());
        //holder.seekBarA.setProgress((int) per1);
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
        public SeekBar seekBarA;
        public SeekBar seekBarB;
        public SeekBar seekBarC;
        public SeekBar seekBarD;
        //public Button btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    id=pollList.get(getAbsoluteAdapterPosition()).getId();
                }
            });
            //btn = itemView.findViewById(R.id.button);
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
            seekBarA = itemView.findViewById(R.id.seekBarA);
            seekBarB = itemView.findViewById(R.id.seekBarB);
            seekBarC = itemView.findViewById(R.id.seekBarC);
            seekBarD = itemView.findViewById(R.id.seekBarD);
//            btn.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(context, "Trend", Toast.LENGTH_SHORT).show();
//                    Intent i=new Intent(view.getContext(), BarActivity.class);
//                    context.startActivity(i);
//                }
//                });

            optionA.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @SuppressLint("DefaultLocale")
                @Override
                public void onClick(View view) {
                    id = pollList.get(getAbsoluteAdapterPosition()).getId();//pollID
                    String date = db.getDate(id);
                    if(context!=null) {
                    SharedPreferences sh = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
                    String email = sh.getString("email", "");
                    int user_id = db.fetch_UserID(email);//user id
                    boolean verify = db.checkVote(id, user_id);//if user has already voted.
                    if (verify) {
                        Log.d("clickFrom", "true");
                        Toast.makeText(context, "Already voted", Toast.LENGTH_SHORT).show();
                        count1 = pollList.get(getAbsoluteAdapterPosition()).getC1();
                        count2 = pollList.get(getAbsoluteAdapterPosition()).getC2();
                        count3 = pollList.get(getAbsoluteAdapterPosition()).getC3();
                        count4 = pollList.get(getAbsoluteAdapterPosition()).getC4();
                        total = count1 + count2 + count3 + count4;
                        if (total == 0)
                            total = 1;
                        per1 = (((double)count1) / total) * 100;
                        per2 = (((double)count2) / total) * 100;
                        per3 = (((double)count3) / total) * 100;
                        per4 = (((double)count4) / total) * 100;
                        voteA.setText(String.format("%.2f", per1) + "%");
                        voteB.setText(String.format("%.2f", per2) + "%");
                        voteC.setText(String.format("%.2f", per3) + "%");
                        voteD.setText(String.format("%.2f", per4) + "%");
                    } else {
                        db.insertDataTable6(user_id, id, "A");
                        count1 = pollList.get(getAbsoluteAdapterPosition()).getC1();
                        count2 = pollList.get(getAbsoluteAdapterPosition()).getC2();
                        count3 = pollList.get(getAbsoluteAdapterPosition()).getC3();
                        count4 = pollList.get(getAbsoluteAdapterPosition()).getC4();
                        count1++;
                        db.updateItem(id, date, count1, count2, count3, count4);
                        total = count1 + count2 + count3 + count4;
                        if (total == 0)
                            total = 1;
                        per1 = (((double)count1) / total) * 100;
                        per2 = (((double)count2) / total) * 100;
                        per3 = (((double)count3) / total) * 100;
                        per4 = (((double)count4) / total) * 100;
                        voteA.setText(String.format("%.2f", per1) + "%");
                        voteB.setText(String.format("%.2f", per2) + "%");
                        voteC.setText(String.format("%.2f", per3) + "%");
                        voteD.setText(String.format("%.2f", per4) + "%");

                    }
                    seekBarA.setProgress((int) per1, true);
                    seekBarB.setProgress((int) per2, true);
                    seekBarC.setProgress((int) per3, true);
                    seekBarD.setProgress((int) per4, true);
                }
                }
            });

            optionB.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View view) {
                    id=pollList.get(getAbsoluteAdapterPosition()).getId();//pollID
                    String date = db.getDate(id);
                    if(context!=null) {
                        SharedPreferences sh = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
                        String email = sh.getString("email", "");
                        int user_id = db.fetch_UserID(email);//user id
                        boolean verify = db.checkVote(id, user_id);
                        if (verify) {
                            Log.d("clickFrom", "true");
                            Toast.makeText(context, "Already voted", Toast.LENGTH_SHORT).show();
                            count1 = pollList.get(getAbsoluteAdapterPosition()).getC1();
                            count2 = pollList.get(getAbsoluteAdapterPosition()).getC2();
                            count3 = pollList.get(getAbsoluteAdapterPosition()).getC3();
                            count4 = pollList.get(getAbsoluteAdapterPosition()).getC4();
                            total = count1 + count2 + count3 + count4;
                            if (total==0)
                                total = 1;
                            per1 = (((double)count1) / total) * 100;
                            per2 = (((double)count2) / total) * 100;
                            per3 = (((double)count3) / total) * 100;
                            per4 = (((double)count4) / total) * 100;
                            voteA.setText(String.format("%.2f", per1) + "%");
                            voteB.setText(String.format("%.2f", per2) + "%");
                            voteC.setText(String.format("%.2f", per3) + "%");
                            voteD.setText(String.format("%.2f", per4) + "%");
                        } else {
                            db.insertDataTable6(user_id, id, "B");
                            count1 = pollList.get(getAbsoluteAdapterPosition()).getC1();
                            count2 = pollList.get(getAbsoluteAdapterPosition()).getC2();
                            count3 = pollList.get(getAbsoluteAdapterPosition()).getC3();
                            count4 = pollList.get(getAbsoluteAdapterPosition()).getC4();
                            count2++;
                            db.updateItem(id, date, count1, count2, count3, count4);
                            total = count1 + count2 + count3 + count4;
                            if (total==0)
                                total = 1;
                            per1 = (((double)count1) / total) * 100;
                            per2 = (((double)count2) / total) * 100;
                            per3 = (((double)count3) / total) * 100;
                            per4 = (((double)count4) / total) * 100;
                            voteA.setText(String.format("%.2f", per1) + "%");
                            voteB.setText(String.format("%.2f", per2) + "%");
                            voteC.setText(String.format("%.2f", per3) + "%");
                            voteD.setText(String.format("%.2f", per4) + "%");
                        }
                        seekBarA.setProgress((int) per1, true);
                        seekBarB.setProgress((int) per2, true);
                        seekBarC.setProgress((int) per3, true);
                        seekBarD.setProgress((int) per4, true);
                    }
                }
            });

            optionC.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View view) {
                    id=pollList.get(getAbsoluteAdapterPosition()).getId();//pollID
                    String date = db.getDate(id);
                    if(context!=null) {
                        SharedPreferences sh = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
                        String email = sh.getString("email", "");
                        int user_id = db.fetch_UserID(email);//user id
                        boolean verify = db.checkVote(id, user_id);
                        if (verify) {
                            Log.d("clickFrom", "true");
                            Toast.makeText(context, "Already voted", Toast.LENGTH_SHORT).show();
                            count1 = pollList.get(getAbsoluteAdapterPosition()).getC1();
                            count2 = pollList.get(getAbsoluteAdapterPosition()).getC2();
                            count3 = pollList.get(getAbsoluteAdapterPosition()).getC3();
                            count4 = pollList.get(getAbsoluteAdapterPosition()).getC4();
                            total = count1 + count2 + count3 + count4;
                            if (total==0)
                                total = 1;
                            per1 = (((double)count1) / total) * 100;
                            per2 = (((double)count2) / total) * 100;
                            per3 = (((double)count3) / total) * 100;
                            per4 = (((double)count4) / total) * 100;
                            voteA.setText(String.format("%.2f", per1) + "%");
                            voteB.setText(String.format("%.2f", per2) + "%");
                            voteC.setText(String.format("%.2f", per3) + "%");
                            voteD.setText(String.format("%.2f", per4) + "%");
                        } else {
                            db.insertDataTable6(user_id, id, "C");
                            count1 = pollList.get(getAbsoluteAdapterPosition()).getC1();
                            count2 = pollList.get(getAbsoluteAdapterPosition()).getC2();
                            count3 = pollList.get(getAbsoluteAdapterPosition()).getC3();
                            count4 = pollList.get(getAbsoluteAdapterPosition()).getC4();
                            count3++;
                            db.updateItem(id, date, count1, count2, count3, count4);
                            total = count1 + count2 + count3 + count4;
                            if (total==0)
                                total = 1;
                            per1 = (((double)count1) / total) * 100;
                            per2 = (((double)count2) / total) * 100;
                            per3 = (((double)count3) / total) * 100;
                            per4 = (((double)count4) / total) * 100;
                            voteA.setText(String.format("%.2f", per1) + "%");
                            voteB.setText(String.format("%.2f", per2) + "%");
                            voteC.setText(String.format("%.2f", per3) + "%");
                            voteD.setText(String.format("%.2f", per4) + "%");
                        }
                        seekBarA.setProgress((int) per1, true);
                        seekBarB.setProgress((int) per2, true);
                        seekBarC.setProgress((int) per3, true);
                        seekBarD.setProgress((int) per4, true);
                    }
                }
            });

            optionD.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View view) {
                    id=pollList.get(getAbsoluteAdapterPosition()).getId();//pollID
                    String date = db.getDate(id);
                    if(context!=null) {
                        SharedPreferences sh = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
                        String email = sh.getString("email", "");
                        int user_id = db.fetch_UserID(email);//user id
                        boolean verify = db.checkVote(id, user_id);
                        if (verify) {
                            Log.d("clickFrom", "true");
                            Toast.makeText(context, "Already voted", Toast.LENGTH_SHORT).show();
                            count1 = pollList.get(getAbsoluteAdapterPosition()).getC1();
                            count2 = pollList.get(getAbsoluteAdapterPosition()).getC2();
                            count3 = pollList.get(getAbsoluteAdapterPosition()).getC3();
                            count4 = pollList.get(getAbsoluteAdapterPosition()).getC4();
                            total = count1 + count2 + count3 + count4;
                            if (total==0)
                                total = 1;
                            per1 = (((double)count1) / total) * 100;
                            per2 = (((double)count2) / total) * 100;
                            per3 = (((double)count3) / total) * 100;
                            per4 = (((double)count4) / total) * 100;
                            voteA.setText(String.format("%.2f", per1) + "%");
                            voteB.setText(String.format("%.2f", per2) + "%");
                            voteC.setText(String.format("%.2f", per3) + "%");
                            voteD.setText(String.format("%.2f", per4) + "%");
                        } else {
                            db.insertDataTable6(user_id, id, "D");
                            count1 = pollList.get(getAbsoluteAdapterPosition()).getC1();
                            count2 = pollList.get(getAbsoluteAdapterPosition()).getC2();
                            count3 = pollList.get(getAbsoluteAdapterPosition()).getC3();
                            count4 = pollList.get(getAbsoluteAdapterPosition()).getC4();
                            count4++;
                            db.updateItem(id, date, count1, count2, count3, count4);
                            total = count1 + count2 + count3 + count4;
                            if (total==0)
                                total = 1;
                            per1 = (((double)count1) / total) * 100;
                            per2 = (((double)count2) / total) * 100;
                            per3 = (((double)count3) / total) * 100;
                            per4 = (((double)count4) / total) * 100;
                            voteA.setText(String.format("%.2f", per1) + "%");
                            voteB.setText(String.format("%.2f", per2) + "%");
                            voteC.setText(String.format("%.2f", per3) + "%");
                            voteD.setText(String.format("%.2f", per4) + "%");
                        }
                        seekBarA.setProgress((int) per1, true);
                        seekBarB.setProgress((int) per2, true);
                        seekBarC.setProgress((int) per3, true);
                        seekBarD.setProgress((int) per4, true);
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
