package com.cb.system.util;

public class SelectItem {

	private String selectText;
	private String selectValue;
	
	public SelectItem()
	{
		
	}

	public SelectItem(String selectText, String selectValue)
	{
		this.selectText = selectText;
	    this.selectValue = selectValue;
	}
	
	public String getSelectText() {
		return selectText;
	}
	public void setSelectText(String selectText) {
		this.selectText = selectText;
	}
	public String getSelectValue() {
		return selectValue;
	}
	public void setSelectValue(String selectValue) {
		this.selectValue = selectValue;
	}
	
}
