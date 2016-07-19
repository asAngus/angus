package com.example.runtimepressmision;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	ListView mListView;
	private ArrayList<ContactInfo> contactInfos;
	private MyAdapter adapter;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initRes();

		contactInfos = (ArrayList<ContactInfo>) SaflyApplication.getInstance()
				.getContactBeanList();

	}

	@Override
	protected void onResume() {
		super.onResume();
		setAdapter();

	}

	private void setAdapter() {

		if (adapter == null) {
			if (contactInfos != null && contactInfos.size() > 0) {
				adapter = new MyAdapter(this, contactInfos);
				mListView.setAdapter(adapter);
			}
		}

	}

	private void initRes() {
		mListView = (ListView) findViewById(R.id.mListview);
	}

	class MyAdapter extends BaseAdapter {

		Context context;
		ArrayList<ContactInfo> contactInfos;

		public MyAdapter(Context context, ArrayList<ContactInfo> contactInfos) {
			super();
			this.context = context;
			this.contactInfos = contactInfos;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return contactInfos.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return contactInfos.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder viewHolder;
			if (convertView == null) {
				convertView = View
						.inflate(context, R.layout.contact_item, null);
				viewHolder = new ViewHolder(convertView);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}

			if (contactInfos.size() < 1) {
				return null;
			}
			ContactInfo contactInfo = contactInfos.get(position);
			viewHolder.name.setText(contactInfo.getName());
			viewHolder.phone.setText(contactInfo.getPhone());
			return convertView;
		}

		class ViewHolder {

			private TextView name;
			private TextView phone;

			public ViewHolder(View view) {
				name = (TextView) view.findViewById(R.id.name);
				phone = (TextView) view.findViewById(R.id.phone);
			}
		}
	}

}
