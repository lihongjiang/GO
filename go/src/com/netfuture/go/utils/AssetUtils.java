package com.netfuture.go.utils;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.Resources;

public class AssetUtils {
	public static InputStream readSampleJson(Resources resources){
		try {
			return resources.getAssets().open("sample.json");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
