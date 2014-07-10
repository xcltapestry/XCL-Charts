package com.data;

public class MyData {
	/**
	 * 数据名称
	 */
	private String name;
	
	/**
	 * 数据内容
	 */
	private int[] data;
	
	/**
	 * 数据颜色
	 */
	private int color;
	
	
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	
	public void setData(int[] data){
		this.data = data;
	}
	public int[] getData(){
		return data;
	}
	
	public void setColor(int color){
		this.color = color;
	}
	public int getColor(){
		return color;
	}
}
