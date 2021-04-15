package com.example.android.customer_farmer;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class edit_farmer extends AppCompatActivity {

    EditText oldpassword, newpassword, confpassword;
    Button change;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_farmer);

        oldpassword = (EditText) findViewById(R.id.oldpassword);
        newpassword = (EditText) findViewById(R.id.newpassword);
        confpassword = (EditText) findViewById(R.id.confpassword);
        change = (Button) findViewById(R.id.change);
        DB = new DBHelper(this);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String o_pass = oldpassword.getText().toString();
                String n_pass = newpassword.getText().toString();
                String re_pass = confpassword.getText().toString();
                String f_id = getIntent().getStringExtra("farmer_id");
                if(o_pass.equals("")||n_pass.equals("")||re_pass.equals(""))
                    Toast.makeText(edit_farmer.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(n_pass.equals(re_pass)){
                        Boolean checkuser = DB.checkfarmerpassword(f_id,o_pass);
                        if(checkuser==true){
                            Boolean update = DB.updatepassword_farmer(f_id, n_pass);
                            if(update==true){
                                Toast.makeText(edit_farmer.this, "Password changed successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),FarmerLoginActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(edit_farmer.this, "Updating failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(edit_farmer.this, "Password and username not matched", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(edit_farmer.this, "New passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                } }
        });

    }
}
