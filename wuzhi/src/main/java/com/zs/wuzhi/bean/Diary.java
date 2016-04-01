package com.zs.wuzhi.bean;

import java.util.Date;
import java.util.List;

public class Diary {
	private long userId;
	private String userName;
	private String sign;
	private String img;
	private int star;
	private Date last;
	private int isPublic=1;//0:private 1:public
	private int accountStatu=1;
	private List<DiaryContent> diaryContent;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public Date getLast() {
		return last;
	}
	public void setLast(Date last) {
		this.last = last;
	}
	
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	
	public int getAccountStatu() {
		return accountStatu;
	}
	public void setAccountStatu(int accountStatu) {
		this.accountStatu = accountStatu;
	}
	public int getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(int isPublic) {
		this.isPublic = isPublic;
	}
	public List<DiaryContent> getDiaryContent() {
		return diaryContent;
	}
	public void setDiaryContent(List<DiaryContent> diaryContent) {
		this.diaryContent = diaryContent;
	}
	
}
