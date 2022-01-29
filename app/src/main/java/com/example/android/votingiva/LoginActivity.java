package com.example.android.votingiva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText editTextEmail,editTextPassword;
    Button button_login;
    password_db myDatabase;
    ImageView goto_reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myDatabase=new password_db(this);
        editTextEmail=findViewById(R.id.email);
        editTextPassword=findViewById(R.id.password);
        button_login=findViewById(R.id.login);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attempt_login();
            }
        });


        goto_reg=findViewById(R.id.goto_reg);
        //onclick listener for the Plus button on login page
        goto_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent i=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    private void attempt_login() {
        String email=editTextEmail.getText().toString();
        String password=editTextPassword.getText().toString();
        if(email.isEmpty() && password.isEmpty()){
            Toast.makeText(this,"Please fill in the details to proceed.",Toast.LENGTH_SHORT).show();
        }
        else if(email.isEmpty()){
            Toast.makeText(this,"Please enter email.",Toast.LENGTH_SHORT).show();
        }
        else if(password.isEmpty()){
            Toast.makeText(this,"Please enter password.",Toast.LENGTH_SHORT).show();
        }
        else if(!isEmailValid(email)){
            Toast.makeText(this,"Email entered is not valid.",Toast.LENGTH_SHORT).show();
        }
        else if(!isPasswordValid(password)){
            Toast.makeText(this,"Password length too short.",Toast.LENGTH_SHORT).show();
        }
        else {
            Cursor res = myDatabase.login_user(email, password);
            if (res.getCount() == 1) {
                Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                // Storing data into SharedPreferences
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MySharedPref",MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("email", email);
                myEdit.apply();

                i.putExtra("email",email);
                startActivity(i);
            } else {
                Toast.makeText(this, "Invalid credentials.", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private Boolean isEmailValid(String email){
        return email.contains("@");
    }
    private Boolean isPasswordValid(String password){
        return password.length()>5;
    }
}