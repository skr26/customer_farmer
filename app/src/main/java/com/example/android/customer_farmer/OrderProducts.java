package com.example.android.customer_farmer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderProducts extends AppCompatActivity {

    DBHelper myDB;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_contents);

        listView = (ListView) findViewById(R.id.listView);
        myDB = new DBHelper(this);
        final String farmer_id = getIntent().getStringExtra("farmer_id");
        final String customer_id = getIntent().getStringExtra("customer_id");
        Toast.makeText(getBaseContext(),farmer_id,Toast.LENGTH_SHORT).show();
//
//        //populate an ArrayList<String> from the database and then view it
        final ArrayList<String> pList = new ArrayList<>();
        final ArrayList<Integer> idL = new ArrayList<>();
        Cursor data = myDB.displayProducts(farmer_id);

        if(data.getCount() == 0){
            Toast.makeText(this, "There are no contents in this list!",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                StringBuffer bf = new StringBuffer();
                    bf.append(data.getString(0) + "\n");
                    bf.append(data.getString(1) + "\n");
                bf.append(data.getString(2) + "\n");
                bf.append(data.getString(3) + "\n");
                bf.append(data.getString(4));
                pList.add(bf.toString());
                idL.add(data.getInt(0));
//                pList.add(data.getString(2));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,pList);
                listView.setAdapter(listAdapter);
            }
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Boolean create = myDB.createOrder(farmer_id, customer_id, idL.get(position));
                if(create==true){
                    Toast.makeText(OrderProducts.this, "Order Created", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),ViewOrderCustomer.class);
                    intent.putExtra("customer_id",customer_id);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(OrderProducts.this, "Sorry cannot create order", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }


}