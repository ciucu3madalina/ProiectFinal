package com.ossvirtualshop.onlinemegazine.dhhelper;

import java.util.ArrayList;
import java.util.HashMap;

import com.ossvirtualshop.onlinemegazine.model.Product;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBOpenHelper extends SQLiteOpenHelper {

	private static String Databasename = "MYCART";
	private static int Databasevertion = 1;
	private String CARTTABLE = "carttable";

	public DBOpenHelper(Context context) {
		super(context, Databasename, null, Databasevertion);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table " + CARTTABLE + "(" + "_id "
				+ "INTEGER PRIMARY KEY AUTOINCREMENT," + " id " + " TEXT, "
				+ " name " + " TEXT, " + "price " + " TEXT" + ")";
		db.execSQL(sql);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public ArrayList<Product> getAllList() {
		ArrayList<Product> list = new ArrayList<Product>();
		SQLiteDatabase db = this.getReadableDatabase();
		String[] colums = { "id", "name", "price" };
		Cursor c = db.query(CARTTABLE, colums, null, null, null, null, null);
		int count = 0;
		while (c.moveToNext()) {
			count = count + 1;
			list.add(new Product(c.getString(0), c.getString(1), c.getInt(2),
					count));
		}
		c.close();
		db.close();
		return list;
	}

	public void saveToCart(String id, String name, int price) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("id", id);
		values.put("name", name);
		values.put("price", price);
		db.insert(CARTTABLE, null, values);
		db.close();
	}
	public void deleteAllItemsFromCart() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(CARTTABLE, null, null);
		db.close();
	}

}
