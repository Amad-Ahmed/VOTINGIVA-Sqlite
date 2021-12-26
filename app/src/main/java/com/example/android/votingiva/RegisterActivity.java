package com.example.android.votingiva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import cn.pedant.SweetAlert.SweetAlertDialog;

//import static com.example.android.votingiva.password_db.T_EMAIL;
//import static com.example.android.votingiva.password_db.TABLE_NAME;

public class RegisterActivity extends AppCompatActivity {
    ImageView back_to_login;
    EditText editTextEmail,editTextPassword,editTextConPassword,editTextUsername;
    Button register_user;
    password_db myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myDatabase= new password_db(this);
        editTextEmail=findViewById(R.id.email);
        editTextPassword=findViewById(R.id.password);
        editTextConPassword=findViewById(R.id.password_con);
        register_user=findViewById(R.id.register);
        editTextUsername=findViewById(R.id.username);
        Register_user();


        back_to_login=findViewById(R.id.back_to_login);
        back_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent i=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }

    private void Register_user() {
        register_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=editTextEmail.getText().toString();
                String password=editTextPassword.getText().toString();
                String con_password=editTextConPassword.getText().toString();
                String username=editTextUsername.getText().toString();

                if(!password.equals(con_password)){
                    Toast.makeText(RegisterActivity.this,"The passwords do not match.",Toast.LENGTH_SHORT).show();
                }
                else if(!isEmailValid(email)){
                    Toast.makeText(RegisterActivity.this,"Email entered is not valid.",Toast.LENGTH_SHORT).show();
                }
                else if(!isPasswordValid(password)){
                    Toast.makeText(RegisterActivity.this,"Password length too short.",Toast.LENGTH_SHORT).show();
                }
                else if(email.isEmpty()){
                    Toast.makeText(RegisterActivity.this,"Please enter email.",Toast.LENGTH_SHORT).show();
                }
                else if(password.isEmpty() || con_password.isEmpty()){
                    Toast.makeText(RegisterActivity.this,"Please enter password.",Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean isInserted = myDatabase.insertData(email, password, username);
                    if (!isInserted) {
                        Toast.makeText(RegisterActivity.this, "Credentials not unique.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Message")
                                .setContentText("Registration Successful")
                                .setConfirmText("OK")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                                        startActivity(i);
                                    }
                                })
                                .show();
                    }

                }
            }
        });
    }

    private Boolean isEmailValid(String email){
        return email.contains("@");
    }
    private Boolean isPasswordValid(String password){
        return password.length()>5;
    }

}