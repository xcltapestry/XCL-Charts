package com.data;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
/**
 * @author Seven
 */
public class Common {
	public static int screenWidth = 0;
	public static int screenHeight = 0;
	
	public static String title = "";
	public static int  titleX = 20; 
	public static int  titleY = 40; 
	public static int titleColor = Color.BLACK;
	
	public static int keyWidth = 30;
	public static int keyHeight = 10;
	public static int keyToLeft = 200;
	public static int keyToTop = 80;
	public static int keyToNext = 80;
	public static int keyTextPadding = 5;
	
	public static String[] xScaleArray = new String[]{"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
	public static int xScaleColor = Color.rgb(25, 156, 240);
	
	public static int[] yScaleArray = new int[]{0,25,50,100,200,300,500};
	public static String[] levelName = new String[]{"优","良","轻度","中度","重度","严重"};
	public static int[] yScaleColor = new int[]{0xff00ff00,0xffffff00,0xffffa500,0xffff4500,0xffdc143c,0xffa52a2a};
	
	public static List<MyData> DataSeries = new ArrayList<MyData>();
}
