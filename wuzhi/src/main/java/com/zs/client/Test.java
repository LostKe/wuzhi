package com.zs.client;

public class Test {
	public static void main(String[] args) {
		String str="https://wuzhi.me/ava/tar//235.1294058362.jpg";
		int size=str.length();
		int index=str.lastIndexOf("/");
		System.out.println(str.substring(index+1, size));
	}

}
