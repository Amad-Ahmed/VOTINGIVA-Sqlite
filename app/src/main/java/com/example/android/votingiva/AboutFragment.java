package com.example.android.votingiva;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.votingiva.Adapters.RecyclerViewAdaptor;

import java.util.ArrayList;


public class AboutFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerViewAdaptor recyclerViewAdaptor;
    private ArrayList<Poll> pollArrayList;
    private ArrayAdapter<String> arrayAdapter;
    TextView email,user;
    password_db myDatabase;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDatabase = new password_db(getContext());
        pollArrayList = new ArrayList<>();
    }
// add the code of query extraction in on Create or onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_about, container, false);
        email=view.findViewById(R.id.email);
        user=view.findViewById(R.id.username);
        myDatabase=new password_db(this.getContext());//context is always context
        if(getArguments()!=null){
            String email_var=getArguments().getString("email");//extracting email
            email.setText(email_var);
            user.setText(myDatabase.fetch_about(email_var));
        }
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
        pollArrayList = myDatabase.getSavedData();
        // Use Recycler View
        recyclerViewAdaptor = new RecyclerViewAdaptor(getContext(), pollArrayList);
        recyclerView.setAdapter(recyclerViewAdaptor);
    }
}
