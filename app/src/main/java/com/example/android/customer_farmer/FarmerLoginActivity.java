package com.example.android.customer_farmer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FarmerLoginActivity extends AppCompatActivity {
    Button addproduct;
    Button vieworders;
    Button editpassword,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_login2);

        addproduct = (Button) findViewById(R.id.addproduct);
        vieworders = (Button) findViewById(R.id.vieworders_farm);
        editpassword = (Button) findViewById(R.id.editpass_farm);
        logout =(Button) findViewById(R.id.logout_farm);
        final String f_id = getIntent().getStringExtra("GET_FARMER_ID");
        addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(), productActivity.class);
                intent.putExtra("farmer_id",f_id);
                startActivity(intent);
            }
        });
        vieworders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), vieworders_farmer.class);
                intent.putExtra("farmer_id",f_id);
                startActivity(intent);
            }
        });
        editpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(), edit_farmer.class);
                intent.putExtra("farmer_id",f_id);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(), WelcomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
