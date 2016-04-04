package com.zs.util;

public class BuildHelper {
	
	enum Build{
		test,product
	}
	
	private static Build mBuild=Build.test; 
	
	public static boolean needInit() {
		return false;
	}


}
