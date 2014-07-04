package com.ossvirtualshop.onlinemegazine;

import java.util.ArrayList;

import com.ossvirtualshop.onlinemegazine.adapter.ProductListAdapter;
import com.ossvirtualshop.onlinemegazine.dhhelper.DBOpenHelper;
import com.ossvirtualshop.onlinemegazine.model.Product;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Toast;

public class CartActivity extends Activity implements OnClickListener {

	private ListView lvProducts;
	private ArrayList<Product> list;
	private ProductListAdapter adapter;
	private Button btnAdd, btnBuy;
	DBOpenHelper dbhelper = new DBOpenHelper(CartActivity.this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cart);
		lvProducts = (ListView) findViewById(R.id.listView1);
		setAdapter();
		btnAdd = (Button) findViewById(R.id.button1);
		btnBuy = (Button) findViewById(R.id.button2);
		btnAdd.setOnClickListener(this);
		btnBuy.setOnClickListener(this);
	}

	private void setAdapter() {
		
		list = dbhelper.getAllList();
		adapter = new ProductListAdapter(getApplicationContext(),
				R.layout.list_row_view, list);
		lvProducts.setAdapter(adapter);

	}

	@Override
	public void onClick(View v) {

		if (v.getId() == R.id.button1) {
			finish();

		} else if (v.getId() == R.id.button2) {
			
			dbhelper.deleteAllItemsFromCart();
			list.clear();
			adapter.notifyDataSetChanged();
			Toast.makeText(getApplicationContext(),"Products bought",Toast.LENGTH_LONG).show();

		}
	}

}
