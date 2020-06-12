package com.hongyuan.fitness.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferencesUtil {
	private static final String FILENAME = "CDJ";

	private static String getFileName(Context context) {
		return context.getPackageName() + FILENAME;
	}

	private static SharedPreferences getSharedPreferences(Context context) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				getFileName(context), Context.MODE_MULTI_PROCESS);
		return sharedPreferences;
	}

	/**
	 * 存放实体类以及任意类型
	 *
	 * @param context 上下文对象
	 * @param key
	 * @param obj
	 */
	public static void putBean(Context context, String key, Object obj) {
		if (obj instanceof Serializable) {// obj必须实现Serializable接口，否则会出问题
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(baos);
				oos.writeObject(obj);
				String string64 = new String(Base64.encode(baos.toByteArray(), 0));
				SharedPreferences.Editor editor = getSharedPreferences(context).edit();
				editor.putString(key, string64).commit();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			throw new IllegalArgumentException("the obj must implement Serializble");
		}

	}

	public static Object getBean(Context context, String key) {
		Object obj = null;
		try {
			String base64 = getSharedPreferences(context).getString(key, "");
			if (base64.equals("")) {
				return null;
			}
			byte[] base64Bytes = Base64.decode(base64.getBytes(), 1);
			ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			obj = ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	/*
	* 存储字符串
	* */
	public static void putString(Context context, String key,String text){
		try {
			SharedPreferences.Editor editor = getSharedPreferences(context).edit();
			editor.putString(key,text);
			editor.commit();
		}catch (Exception e){
			e.printStackTrace();
		}

	}

	/*
	* 获取字符串
	* */
	public static String getString(Context context,String key){
		try {
			SharedPreferences preferences = getSharedPreferences(context);
			return preferences.getString(key,"");
		}catch (Exception e){
			e.printStackTrace();
		}
		return "";
	}
}