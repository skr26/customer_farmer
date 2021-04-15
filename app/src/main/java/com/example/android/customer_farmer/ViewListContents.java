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

public class ViewListContents extends AppCompatActivity {

    DBHelper myDB;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_contents);

        listView = (ListView) findViewById(R.id.listView);
        myDB = new DBHelper(this);
        final String customer_id = getIntent().getStringExtra("customer_id");

        //populate an ArrayList<String> from the database and then view it
        final ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB.getListContents();
        if(data.getCount() == 0){
            Toast.makeText(this, "There are no contents in this list!",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
//                StringBuffer bf = new StringBuffer();
//                    bf.append(data.getString(0) + "\n");
//                    bf.append(data.getString(1) + "\n");
//                theList.add(bf.toString());
                theList.add(data.getString(0));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                listView.setAdapter(listAdapter);
            }
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
//                Intent i = new Intent(getApplicationContext(), OrderProducts.class);
//                i.putExtra("farmer_id", selectedItem);
//                startActivity(i);
                Toast.makeText(getBaseContext(),"touch working",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), OrderProducts.class);
                i.putExtra("farmer_id", theList.get(position));
                i.putExtra("customer_id",customer_id);
                startActivity(i);

            }
        });

    }


}