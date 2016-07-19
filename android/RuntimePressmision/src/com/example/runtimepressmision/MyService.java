package com.example.runtimepressmision;

import java.util.ArrayList;

import android.app.Service;
import android.content.AsyncQueryHandler;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.ContactsContract.Contacts.Data;
import android.util.Log;

public class MyService extends Service {
	private SaflyApplication mApplication;
	private AsyncQueryHandler mAsyncQuery;
	private Uri commonPhoneUri = Uri
			.parse("content://com.android.contacts/contacts");

	public MyService() {
		super();
		Log.i("Safly", "MyService 。。。");
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.i("Safly", "MyService onCreate");
	}

	protected void initSQL() {
		// 获得contact_id属性
		if (mAsyncQuery != null)
			mAsyncQuery.startQuery(0, null, commonPhoneUri,
					new String[] { Data._ID }, null, null, null);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i("Safly", "MyService onStartCommand");
		mApplication = SaflyApplication.getInstance();
		mAsyncQuery = new MyAsyncQueryHandler(getContentResolver());
		initSQL();

		return super.onStartCommand(intent, flags, startId);
	}

	private class MyAsyncQueryHandler extends AsyncQueryHandler {
		public MyAsyncQueryHandler(ContentResolver cr) {
			super(cr);
		}

		protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
			querying(cursor);
		}
	}

	private void querying(final Cursor cursor) {

		Handler handlerInsertOrder = new Handler() {
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case MAsyncTask.DOWNLOAD_END_MESSAGE:
					Log.i("Safly", "onQueryComplete");
					Bundle bundle1 = msg.getData();
					ArrayList<ContactInfo> list = (ArrayList<ContactInfo>) bundle1
							.get("finished");
					mApplication.setContactBeanList(list);

					break;

				case MAsyncTask.DOWNLOADING_START_MESSAGE:
					Log.i("Safly", "onQueryPre");
					break;
				default:
					break;
				}
				super.handleMessage(msg);
			}
		};
		MAsyncTask.startRequestServerData(MyService.this, handlerInsertOrder,
				cursor);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public boolean onUnbind(Intent intent) {
		return super.onUnbind(intent);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

}
