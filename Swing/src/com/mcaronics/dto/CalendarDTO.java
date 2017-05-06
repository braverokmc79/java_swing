package com.mcaronics.dto;

public class CalendarDTO {

	private String memoDate;
	private String memo;
	
	
	public String getMemoDate() {
		return memoDate;
	}
	public void setMemoDate(String memoDate) {
		this.memoDate = memoDate;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Override
	public String toString() {
		return "CalendarDTO [memoDate=" + memoDate + ", memo=" + memo + "]";
	}
	
	
	
}
