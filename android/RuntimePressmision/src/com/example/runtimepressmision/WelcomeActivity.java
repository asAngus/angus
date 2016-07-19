package com.example.runtimepressmision;

import java.util.ArrayList;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Animatable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

public class WelcomeActivity extends Activity {
	private ImageView iv_loading;
	private static final long DELAY = 3000;
	public static final int REQUEST_READ_CONTACTS = 100;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_activity);
		iv_loading = (ImageView) findViewById(R.id.iv_loading);
		((Animatable) iv_loading.getDrawable()).start();
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			int hasReadContactsPermission = checkSelfPermission(Manifest.permission.READ_CONTACTS);
			int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.WRITE_CONTACTS);
			String[] permissions = { Manifest.permission.READ_CONTACTS,
					Manifest.permission.WRITE_CONTACTS };

			if (hasReadContactsPermission != PackageManager.PERMISSION_GRANTED
					|| hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
				requestPermissions(permissions, 100);
				Log.i("Safly", "hasReadContactsPermission != PackageManager.PERMISSION_GRANTED");
			}else {
				Log.i("Safly", "hasReadContactsPermission != PackageManager.PERMISSION_GRANTED else");
				startService(new Intent(this, MyService.class));
				goToNextPage(DELAY);
			}

		} else {
			startService(new Intent(this, MyService.class));
			goToNextPage(DELAY);
			Log.i("Safly", "hasReadContactsPermission == PackageManager.PERMISSION_GRANTED");
		}
		
		
		
	}

	@SuppressLint({ "Override", "NewApi" })
	@Override
	public void onRequestPermissionsResult(int requestCode,
			String permissions[], int[] grantResults) {
		switch (requestCode) {
		case REQUEST_READ_CONTACTS:
			if (grantResults.length > 0
					&& grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				Log.i("Safly", "onRequestPermissionsResult");
				startService(new Intent(this, MyService.class));
				goToNextPage(DELAY);
			}
			break;
		default:
			super.onRequestPermissionsResult(requestCode, permissions,
					grantResults);
		}
	}
	
	private void goToNextPage(long delay) {
		{
			Handler mHandler = new Handler(getMainLooper());
			mHandler.postDelayed(new Runnable() {

				@Override
				public void run() {

					Intent intent = new Intent(WelcomeActivity.this,
							MainActivity.class);
					intent.putExtra("verify", true);
					WelcomeActivity.this.startActivity(intent);
					WelcomeActivity.this.finish();
				}
			}, delay);
		}
	}

}
