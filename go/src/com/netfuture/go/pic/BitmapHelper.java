package com.netfuture.go.pic;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory.Options;

public class BitmapHelper {
	public static boolean saveBitmap(File file, Bitmap bitmap){
	    if(file == null || bitmap == null)
	        return false;
	    try {
	        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
	        return bitmap.compress(CompressFormat.JPEG, 100, out);
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public static Bitmap decodeFile(File file, Options options){
		return BitmapFactory.decodeFile(file.getAbsolutePath(), options);
	}
}
