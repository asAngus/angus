package com.example.runtimepressmision;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

public class SaflyApplication extends Application {
	private ClientAuthInfo clientAuthInfo;
	private static SaflyApplication instance;
	private List<ContactInfo> mContactBeanList;

	@Override
	public void onCreate() {
		super.onCreate();
		this.instance = this;

	}

	public List<ContactInfo> getContactBeanList() {
		return mContactBeanList;
	}

	public void setContactBeanList(List<ContactInfo> contactBeanList) {
		this.mContactBeanList = contactBeanList;
	}

	/**
	 * 初始化注册信息
	 */

	public static SaflyApplication getInstance() {
		return instance;
	}

}
