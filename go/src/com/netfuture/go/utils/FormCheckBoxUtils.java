package com.netfuture.go.utils;

import android.widget.CheckBox;

public class FormCheckBoxUtils {
	
	public static String getVaule(CheckBox view, String trueValue,
			String falseValue) {
		if (view.isChecked()) {
			return trueValue;
		} else {
			return falseValue;
		}
	}
}
