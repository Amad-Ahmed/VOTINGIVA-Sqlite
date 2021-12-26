package com.example.android.votingiva;

//import static com.example.android.votingiva.password_db.T_EMAIL;

import android.content.Intent;
        import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
import android.widget.SeekBar;

import com.example.android.votingiva.Adapters.RecyclerViewAdaptor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

        import java.util.ArrayList;


public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerViewAdaptor recyclerViewAdaptor;
    private ArrayList<Poll> pollArrayList;
    private ArrayAdapter<String> arrayAdapter;
    password_db db;

    // MyAdapter myAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new password_db(getContext());
        pollArrayList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        FloatingActionButton fab = view.findViewById(R.id.fab);
        String email="";
        if(getArguments()!=null){
            email=getArguments().getString("email");
        }
        String finalEmail = email;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),CreatePollActivity.class);
                i.putExtra("email", finalEmail);//email passed onto createPOLLActivity
                startActivity(i);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        // Recycler View Initialization
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));   // we can dynamically change that // we want to show over cards linearly
        loadDataListView();//calling our custom created function

    }

    public void loadDataListView() {//this passes the values into the adapter
        pollArrayList = db.getAllData();
    // Use Recycler View
        recyclerViewAdaptor = new RecyclerViewAdaptor(getContext(), pollArrayList);
        recyclerView.setAdapter(recyclerViewAdaptor);
    }
}

