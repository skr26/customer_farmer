package com.example.android.customer_farmer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Login.db";
    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table farmer(username_f TEXT primary key, password_f TEXT)");
        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT)");
        MyDB.execSQL("create Table farmer_products(product_id INTEGER PRIMARY KEY AUTOINCREMENT, farmer_id TEXT,product_name TEXT, product_desc TEXT, product_price INTEGER, FOREIGN KEY (farmer_id) REFERENCES farmer (username_f))");
        MyDB.execSQL("create Table orders(order_id INTEGER PRIMARY KEY AUTOINCREMENT,farmer_id TEXT,customer_id TEXT,product_id INTEGER,FOREIGN KEY(farmer_id) REFERENCES farmer(username_f),FOREIGN KEY(product_id) REFERENCES farmer_products(product_id),FOREIGN KEY (customer_id) references users(username))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }
    public Boolean insertDatafarmer(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username_f", username);
        contentValues.put("password_f", password);
        long result = MyDB.insert("farmer", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public Boolean checkfarmername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from farmer where username_f = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean checkfarmerpassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from farmer where username_f = ? and password_f = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean insertProduct(String productname, String productdesc, String productprice, String farmerid){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("product_name", productname);
        contentValues.put("product_desc", productdesc);
        contentValues.put("product_price", productprice);
        contentValues.put("farmer_id", farmerid);
        long result = MyDB.insert("farmer_products", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM farmer", null);
        return data;
    }

    public Cursor displayProducts(String farmer_id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM farmer_products WHERE farmer_id = ?", new String[] {farmer_id});
        return data;
    }

    public Boolean createOrder(String farmer_id, String customer_id, Integer product_id){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("farmer_id", farmer_id);
        contentValues.put("customer_id", customer_id);
        contentValues.put("product_id", product_id);
        long result = MyDB.insert("orders", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Cursor getOrderContents(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM orders,farmer_products where orders.farmer_id = ? and farmer_products.product_id=orders.product_id", new String[]{id});
        return data;
    }

    public Cursor getCustomerOrder(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM orders,farmer_products where customer_id = ?and orders.product_id=farmer_products.product_id", new String[]{id});
        return data;
    }
    public Boolean updatepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDB.update("users", contentValues, "username = ?",new String[] { username } );
        if(result==-1) return false;
        else
            return true;
    }
    public Boolean updatepassword_farmer(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username_f", username);
        contentValues.put("password_f", password);
        long result = MyDB.update("farmer", contentValues, "username_f = ?",new String[] { username } );
        if(result==-1) return false;
        else
            return true;
    }


}