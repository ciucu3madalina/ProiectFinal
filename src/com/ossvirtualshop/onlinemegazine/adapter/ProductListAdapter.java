package com.ossvirtualshop.onlinemegazine.adapter;

import java.util.List;

import com.ossvirtualshop.onlinemegazine.R;
import com.ossvirtualshop.onlinemegazine.model.Product;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductListAdapter extends ArrayAdapter<Product> {
	private Context context;

	public ProductListAdapter(Context context, int textViewResourceId,
			List<Product> items) {
		super(context, textViewResourceId, items);
		this.context = context;
	}

	private class ViewHolder {

		TextView name;
		

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_row_view, null);
			holder = new ViewHolder();
			holder.name = (TextView) convertView
					.findViewById(R.id.text1);
			convertView.setTag(holder);

		} else
			holder = (ViewHolder) convertView.getTag();
		Product product = getItem(position);
		String str="ITEM"+product.getCount()+"(";
		str=str+product.getId()+","+product.getName()+","+product.getPrice()+")";
		holder.name.setText(str);
		
		

		return convertView;
	}

}