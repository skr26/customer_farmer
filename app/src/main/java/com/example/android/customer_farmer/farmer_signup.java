package com.example.android.customer_farmer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class farmer_signup extends AppCompatActivity {

    EditText username, password, repassword;
    Button signup;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_signup);

        username = (EditText) findViewById(R.id.username_f);
        password = (EditText) findViewById(R.id.password_f);
        repassword = (EditText) findViewById(R.id.repassword_f);
        signup = (Button) findViewById(R.id.btnsignup_f);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                if (user.equals("") || pass.equals("") || repass.equals(""))
                    Toast.makeText(farmer_signup.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(repass)) {
                        Boolean checkuser = DB.checkfarmername(user);
                        if (checkuser == false) {
                            Boolean insert = DB.insertDatafarmer(user, pass);
                            if (insert == true) {
                                Toast.makeText(farmer_signup.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), farmer_login.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(farmer_signup.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(farmer_signup.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(farmer_signup.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}