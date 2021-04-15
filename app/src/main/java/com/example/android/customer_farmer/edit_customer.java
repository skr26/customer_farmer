package com.example.android.customer_farmer;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class edit_customer extends AppCompatActivity {

    EditText oldpassword, newpassword, confpassword;
    Button change;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_customer);

        oldpassword = (EditText) findViewById(R.id.oldpasswordc);
        newpassword = (EditText) findViewById(R.id.newpasswordc);
        confpassword = (EditText) findViewById(R.id.confpasswordc);
        change = (Button) findViewById(R.id.change2);
        DB = new DBHelper(this);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String o_pass = oldpassword.getText().toString();
                String n_pass = newpassword.getText().toString();
                String re_pass = confpassword.getText().toString();
                String f_id = getIntent().getStringExtra("customer_id");
                if(o_pass.equals("")||n_pass.equals("")||re_pass.equals(""))
                    Toast.makeText(edit_customer.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(n_pass.equals(re_pass)){
                        Boolean checkuser = DB.checkusernamepassword(f_id,o_pass);
                        if(checkuser==true){
                            Boolean update = DB.updatepassword(f_id, n_pass);
                            if(update==true){
                                Toast.makeText(edit_customer.this, "Password changed successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),CustomerLoginActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(edit_customer.this, "Updating failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(edit_customer.this, "Password and username not matched", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(edit_customer.this, "New passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                } }
        });

    }
}
