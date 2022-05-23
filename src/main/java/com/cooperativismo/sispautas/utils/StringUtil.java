package com.cooperativismo.sispautas.utils;

public class StringUtil {
	
	public static boolean vazio(String string) {
		if(string == null || string.trim().isEmpty()) {
			return true;
		}
		return false;
	}

}
