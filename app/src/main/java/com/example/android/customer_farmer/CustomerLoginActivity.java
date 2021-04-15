package com.example.android.customer_farmer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CustomerLoginActivity extends AppCompatActivity {
    Button viewfarmers;
    Button vieworders;
    Button editpassword;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        viewfarmers = (Button) findViewById(R.id.viewfarmers);
        vieworders = (Button) findViewById(R.id.vieworders_cust);
        editpassword = (Button) findViewById(R.id.editpass_cust);
        logout =(Button) findViewById(R.id.logout_cust);
        final String f_id = getIntent().getStringExtra("customer_id");
        viewfarmers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(), ViewListContents.class);
                startActivity(intent);
            }
        });
        viewfarmers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ViewListContents.class);
                intent.putExtra("customer_id",f_id);
                startActivity(intent);
            }
        });

        vieworders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ViewOrderCustomer.class);
                intent.putExtra("customer_id",f_id);
                startActivity(intent);
            }
        });

        editpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), edit_customer.class);
                intent.putExtra("customer_id",f_id);
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
