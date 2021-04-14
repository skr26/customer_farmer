package com.example.android.customer_farmer;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewOrderCustomer extends AppCompatActivity {
    DBHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_contents);
        ListView listView = (ListView) findViewById(R.id.listView);
        myDB = new DBHelper(this);

        //populate an ArrayList<String> from the database and then view it
        ArrayList<String> theList = new ArrayList<>();
        final String customer = getIntent().getStringExtra("customer_id");
        Cursor data = myDB.getCustomerOrder(customer);
        if(data.getCount() == 0){
            Toast.makeText(this, "There are no contents in this list!",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                StringBuffer bf = new StringBuffer();
                bf.append(data.getString(0) + "\n");
                bf.append(data.getString(1) + "\n");
                bf.append(data.getString(2) + "\n");
                bf.append(data.getString(3) + "\n");
                theList.add(bf.toString());
                // theList.add(data.getString(0));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                listView.setAdapter(listAdapter);
            }
        }


    }
}