package com.ossvirtualshop.onlinemegazine;

import java.util.ArrayList;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.*;
import com.ossvirtualshop.onlinemegazine.CartActivity;
import com.ossvirtualshop.onlinemegazine.NewActivity;
import com.ossvirtualshop.onlinemegazine.R;
import com.ossvirtualshop.onlinemegazine.adapter.ProductListAdapter;
import com.ossvirtualshop.onlinemegazine.dhhelper.DBOpenHelper;
import com.ossvirtualshop.onlinemegazine.model.Product;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter.LengthFilter;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.http.Header;

public class NewActivity extends Activity {

	private ListView lvProducts;
	private ArrayList<Product> list=new ArrayList<Product>();
	private ProductListAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new);
		lvProducts=(ListView) findViewById(R.id.listView1);
		RequestParams params = new RequestParams();
        params.put("api_auth", "VECeKU2puHatudreb7A3");
        params.put("trigger", "user_profile");
        params.put("id_user", "35");
        params.put("session", "61ff95571aa3db550df827f0cae10c938cd31fd2");

        AsyncHttpClient client = new AsyncHttpClient();
        
        client.get("http://10.0.2.2:5000/api/list", new JsonHttpResponseHandler() {

            @Override
            public void onStart() {
            	System.out.println("onStart");
                // called before request is started
            	
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // called when response HTTP status is "200 OK"
            	System.out.println("onSucess");
                System.out.println(response.toString());
                parseJson(response.toString());
                Toast.makeText(getApplicationContext(), "Product list",Toast.LENGTH_LONG).show();
            
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, java.lang.Throwable throwable, JSONObject errorResponse) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                System.out.println("status code = "+throwable);
            	System.out.println("Fail");
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            	System.out.println("onRetry");
            }
        });
    }
	
	private void parseJson(String json)
	{
		try {
			JSONObject jobj=new JSONObject(json);
			JSONArray jarryId=jobj.getJSONArray("id_list");
			JSONArray jarryName=jobj.getJSONArray("name_list");
			JSONArray jarryPrice=jobj.getJSONArray("price_list");
			int x=jarryId.length();
			for(int i=0;i<x;i++)
			{
				Product obj=new Product(jarryId.getString(i),jarryName.getString(i),jarryPrice.getInt(i),i+1);
				list.add(obj);
			}
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		setAdapter();
		
	}
	private void setAdapter(){
		adapter=new ProductListAdapter(getApplicationContext(),R.layout.list_row_view,list);
		lvProducts.setAdapter(adapter);
		lvProducts.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				Product product=list.get(position);
				Intent intent=new Intent(getApplicationContext(),CartActivity.class);
				DBOpenHelper dbhelper=new DBOpenHelper(NewActivity.this);
				dbhelper.saveToCart(product.getId(),product.getName(),product.getPrice());
				Toast.makeText(getApplicationContext(),"Item added to cart",Toast.LENGTH_LONG).show();
				startActivity(intent);
				
				return false;
			}
		});
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}