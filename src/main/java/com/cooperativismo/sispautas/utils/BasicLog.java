package com.cooperativismo.sispautas.utils;

import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BasicLog {
	
	private static Logger logger;
	private static String message;
	
	public static void info(String info, Class<?> clazz) {
		
		logger = Logger.getLogger(clazz.getName());
		
		message = String.format("%s, Class: %s",
								info,
								clazz.getName());
		
		logger.log(Level.INFO, message);
		
		
	}
	
	public static void error(String error, Class<?> clazz) {
		
		logger = Logger.getLogger(clazz.getName());
		
		message = String.format("%s, Class: %s",
				error,
				clazz.getName());

		logger.log(Level.SEVERE, message);
		
	}
	
	public static void infoHttpStatus(String info, int httpStatus, Class<?> clazz) {
		
		logger = Logger.getLogger(clazz.getName());
		
		message = String.format("%s, httpStatus: %s, Class: %s",
				info,
				httpStatus,
				clazz.getName());

		logger.log(Level.INFO, message);
	}
	
	public static void errorHttpStatus(String error, int httpStatus, Class<?> clazz) {
		
		logger = Logger.getLogger(clazz.getName());
		
		message = String.format("%s, httpStatus: %s, Class: %s",
				error,
				httpStatus,
				clazz.getName());

		logger.log(Level.SEVERE, message);
	}
	
	
}


