package com.netfuture.go.ui;

import com.netfuture.go.R;

import com.netfuture.go.utils.LogUtils;

import android.app.Activity;

import android.app.Dialog;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * @author 李洪江
 * @description Activity基础类, 用于定制应用提示,进度交互对话框
 * 
 */
public class BaseActivity extends Activity {

	private Dialog pd = null;
	private View view;
	private TextView tipTextView;
	private ProgressBar pb;
	private Handler hander2 = new Handler() {
		public void handleMessage(android.os.Message msg) {
			pd.dismiss();
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 进度提示对话框
		initProgressDialog();
	}

	/**
	 * 初始化进度提示对话框
	 */
	private void initProgressDialog() {
		view = getLayoutInflater().inflate(R.layout.familysick_dialog_loading,
				null);
		tipTextView = (TextView) view.findViewById(R.id.textViewDialogLoading);
		pb = (ProgressBar) view.findViewById(R.id.progressBarLoading);
		pd = new Dialog(this, R.style.custom_dialog);
		pd.setContentView(view);
		pd.setCancelable(true);

	}

	/**
	 * 打开进度对话框,已经显示就不在显示
	 * 
	 * @param text
	 */
	public void showProgressDialog(String text) {
		try {
			if (!pd.isShowing()) {
				tipTextView.setText(text);
				pd.show();
			}else{
				doProgressDialog(text);
			}
		} catch (Exception e) {
			LogUtils.Log("线程结束,应用退出,对话框显示回调异常");
			e.printStackTrace();
		}
	}

	/**
	 * 关闭进度对话框
	 */
	public void closeProgressDialog() {
		hander2.sendEmptyMessageDelayed(0x500, 500);
	}

	/**
	 * 在进度中显示的文字
	 * 
	 * @param text
	 */
	public void doProgressDialog(String text) {
		tipTextView.setText(text);
	}

	/**
	 * 在进度中显示的文字加图片
	 * 
	 * @param text
	 * @param drawable
	 */
	public void doProgressDialog(String text, Drawable drawable) {
		pb.setIndeterminateDrawable(drawable);
		tipTextView.setText(text);
	}

	/**
	 * 对话框释放
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 对话框销毁
		pd.dismiss();
	}
}
