package com.zs.wuzhi.bean;

import java.util.List;

public class Result {
	
	private List<Long> maxstars;
	private int invalidCount;
	private int privateCount;
	private int publicCount;
	public List<Long> getMaxstars() {
		return maxstars;
	}
	public void setMaxstars(List<Long> maxstars) {
		this.maxstars = maxstars;
	}
	public int getInvalidCount() {
		return invalidCount;
	}
	public void setInvalidCount(int invalidCount) {
		this.invalidCount = invalidCount;
	}
	public int getPrivateCount() {
		return privateCount;
	}
	public void setPrivateCount(int privateCount) {
		this.privateCount = privateCount;
	}
	public int getPublicCount() {
		return publicCount;
	}
	public void setPublicCount(int publicCount) {
		this.publicCount = publicCount;
	}
	
}
