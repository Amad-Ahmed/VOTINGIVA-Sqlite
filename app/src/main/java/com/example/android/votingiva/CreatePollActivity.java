package com.example.android.votingiva;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CreatePollActivity extends AppCompatActivity {

    password_db db;
    EditText pollName,pollQues,optA,optB,optC,optD;
    Button disc,create,save;
    TextView spin;
    ArrayList<String> arrayList;
    Dialog dialog;
    String cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_poll);

        Bundle bundle=getIntent().getExtras();
        String email= bundle.getString("email");
//        Toast.makeText(CreatePollActivity.this,"Email done! :: " + email, Toast.LENGTH_SHORT).show();
        spin=findViewById(R.id.spin);//for the category
        db=new password_db(this);
        pollName=findViewById(R.id.pollname);
        pollQues=findViewById(R.id.question);
        optA=findViewById(R.id.optA);
        optB=findViewById(R.id.optB);
        optC=findViewById(R.id.optC);
        optD=findViewById(R.id.optD);
        disc=findViewById(R.id.discard);
        create=findViewById(R.id.post);
        save=findViewById(R.id.save);

        arrayList=db.CategoryList();//Passing all the details in the spinner

        Create_Poll(email);
        Save_Poll(email);

        spin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog=new Dialog(CreatePollActivity.this);
                dialog.setContentView(R.layout.dialog_searchable_spinner);
                dialog.getWindow().setLayout(650,800);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                EditText editText=dialog.findViewById(R.id.edit_text);
                ListView listView=dialog.findViewById(R.id.list_view);
                ArrayAdapter<String> adapter=new ArrayAdapter<>(CreatePollActivity.this,android.R.layout.simple_list_item_1,arrayList);
                listView.setAdapter(adapter);

                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //when an item in spinner is selected.
                        cat=adapter.getItem(position);
                        spin.setText(adapter.getItem(position));
                        dialog.dismiss();
                    }
                });
            }
        });

        //if discard poll is clicked
        disc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(CreatePollActivity.this,HomeActivity.class);
                startActivity(i);
            }
        });

    }
    private void Save_Poll(String email){
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String poName= pollName.getText().toString();
                String poQues= pollQues.getText().toString();
                String optionA= optA.getText().toString();
                String optionB= optB.getText().toString();
                String optionC= optC.getText().toString();
                String optionD= optD.getText().toString();
                if(poName.isEmpty() || poQues.isEmpty() || optionA.isEmpty() || optionB.isEmpty()){
                    Toast.makeText(CreatePollActivity.this,"The first four fields cannot be empty!", Toast.LENGTH_SHORT).show();
                }
                else{
                    boolean isInserted=db.insertDataTable2(poName,poQues,optionA,optionB,optionC,optionD,email,cat);
                    if (!isInserted) {
                        Toast.makeText(CreatePollActivity.this, "Poll not created.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(CreatePollActivity.this, "Poll created successfully.", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(CreatePollActivity.this,HomeActivity.class);
                        startActivity(i);
                    }
                }
            }
        });
    }

    private void Create_Poll(String email){//When create POLL is pressed and data has to be inserted in resultPOLL and createPOLL table
        create.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String poName= pollName.getText().toString();
                String poQues= pollQues.getText().toString();
                String optionA= optA.getText().toString();
                String optionB= optB.getText().toString();
                String optionC= optC.getText().toString();
                String optionD= optD.getText().toString();
                if(poName.isEmpty() || poQues.isEmpty() || optionA.isEmpty() || optionB.isEmpty()){
                    Toast.makeText(CreatePollActivity.this,"The first four fields cannot be empty!", Toast.LENGTH_SHORT).show();
                }
                else{
                    boolean isInserted=db.insertDataTable2_4(poName,poQues,optionA,optionB,optionC,optionD,email,cat);
                    if (!isInserted) {
                        Toast.makeText(CreatePollActivity.this, "Poll not created.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(CreatePollActivity.this, "Poll created successfully.", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(CreatePollActivity.this,HomeActivity.class);
                        startActivity(i);
                    }
                }
            }
        });
    }
}
