package com.netfuture.go;

import android.os.Bundle;

import com.netfuture.go.R;
import com.netfuture.go.ui.BaseActivity;

/**
 * @author bslee
 * @description 主界面
 * 
 */
public class MainActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
}
