package com.netfuture.go.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

public class BraodcastUtils {
	//内部广播注册
	public void registerReceiver(Context cntext, BroadcastReceiver mReceiver,
			IntentFilter filter) {
		LocalBroadcastManager mLocalBroadcastManager;
		mLocalBroadcastManager = LocalBroadcastManager.getInstance(cntext);
		mLocalBroadcastManager.registerReceiver(mReceiver, filter);
	}
	//内部广播反注册
	public void unregisterReceiver(Context cntext, BroadcastReceiver mReceiver) {
		LocalBroadcastManager mLocalBroadcastManager;
		mLocalBroadcastManager = LocalBroadcastManager.getInstance(cntext);
		mLocalBroadcastManager.unregisterReceiver(mReceiver);
	}
}
