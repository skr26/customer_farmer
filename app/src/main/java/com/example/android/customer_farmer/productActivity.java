package com.example.android.customer_farmer;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class productActivity extends AppCompatActivity {

    EditText productname, productdesc, productprice;
    Button addproduct;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        productname = (EditText) findViewById(R.id.productname);
        productdesc = (EditText) findViewById(R.id.productdesc);
        productprice = (EditText) findViewById(R.id.productprice);
        addproduct = (Button) findViewById(R.id.add);
        DB = new DBHelper(this);

        addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String p_name = productname.getText().toString();
                String p_desc = productdesc.getText().toString();
                String p_price = productprice.getText().toString();
                String f_id = getIntent().getStringExtra("GET_FARMER_ID");
                if(p_name.equals("")||p_desc.equals("")||p_price.equals(""))
                    Toast.makeText(productActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{

                    Boolean insert = DB.insertProduct(p_name, p_desc, p_price, f_id);
                    if(insert==true){
                        Toast.makeText(productActivity.this, "Product added successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(productActivity.this, "Product Adding Failed", Toast.LENGTH_SHORT).show();
                    }

                } }
        });

    }
}
