package com.example.runtimepressmision;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract.Contacts.Data;
import android.util.Log;

public class MAsyncTask extends AsyncTask<Cursor, Void, ArrayList<ContactInfo>> {
	private Context mContext = null;
	private Handler mHandler = null;
	/** 整理结束 */
	public static final int DOWNLOAD_END_MESSAGE = 17;
	/** 开始整理  */
	public static final int DOWNLOADING_START_MESSAGE = 7;
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		sendStartMessage(DOWNLOADING_START_MESSAGE);
	}

	/**
	 * 开始整理
	 * @param messageWhat 
	 */
	private void sendStartMessage(int messageWhat){
		Message message = new Message();
		message.what = messageWhat; 
		if (mHandler != null) {
			mHandler.sendMessage(message);	
		}	
	}


	@Override
	protected ArrayList<ContactInfo> doInBackground(Cursor... params) {
		Log.i("Safly", "doInBackground");
		Cursor cursor = params[0];
		ArrayList<ContactInfo> ciList = new ArrayList<ContactInfo>();

		if (cursor != null && cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				int contactId = cursor.getInt(0);
				// 通过contact_id,去获取data表中对应的值
				Uri parse = Uri
						.parse("content://com.android.contacts/contacts/"
								+ contactId + "/data");
				Cursor cursor2 = mContext.getContentResolver().query(parse,
						new String[] { Data.DATA1, Data.MIMETYPE }, null, null,
						null);
				ContactInfo contactInf = new ContactInfo();
				while (cursor2.moveToNext()) {

					String data = cursor2.getString(cursor2
							.getColumnIndex("data1"));
					if (cursor2.getString(cursor2.getColumnIndex("mimetype"))
							.equals("vnd.android.cursor.item/name")) { // 如果是名字
						Log.i("Safly", "data-->>"+data);
						contactInf.setName(data);
					} else if (cursor2.getString(
							cursor2.getColumnIndex("mimetype")).equals(
							"vnd.android.cursor.item/phone_v2")) { // 如果是电话
						Log.i("Safly", "phone-->>"+data);
						contactInf.setPhone(data);
					}
				}
				ciList.add(contactInf);
			}
		}
		return ciList;
	}

	@Override
	protected void onPostExecute(ArrayList<ContactInfo> result) {
//		super.onPostExecute(result);
		sendEndMessage(DOWNLOAD_END_MESSAGE, result);
	}

	private void sendEndMessage(int messageWhat, ArrayList<ContactInfo> result) {
		{
			Message message = new Message();
			message.what = messageWhat;
			Bundle bundle = new Bundle();
			bundle.putSerializable("finished", result);
			message.setData(bundle);
			if (mHandler != null) {
				mHandler.sendMessage(message);
			}
		}
	}

	public static void startRequestServerData(Context context, Handler handler,
			Cursor cursor) {
		new MAsyncTask(context, handler).execute(cursor);
	}

	protected MAsyncTask(Context context, Handler handler) {
		this.mContext = context;
		this.mHandler = handler;
	}
}
