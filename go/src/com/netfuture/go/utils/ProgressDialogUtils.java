package com.netfuture.go.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class ProgressDialogUtils {

	//进度提示对话框
	public static ProgressDialog showProgressDiaolg(Context context,String title,String content) {
		return ProgressDialog.show(context, title, content);
	}
}
