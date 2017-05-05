package com.mcaronics.common;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

import javax.swing.JPanel;

public class PrintComponent extends JPanel{//프린트될부분을 컴포넌트에 표시
	 protected Printable printable; //Printable 인스턴스변수 선언

	 protected PageFormat pageFormat; //PageFormat 인스턴스변수 선언

	 protected int displayPage; //표시할 페이지번호를 저장

	 protected double scaleFactor; //스케일을 저장할 변수 선언

	 public PrintComponent(Printable p, PageFormat pf){

	//생성자 함수, Printable과 PageFormat 개체를 인수로 넘겨받아,
	  setPrintable(p);//선언했던 Printable변수와
	  setPageFormat(pf);//PageFormat변수에 저장
	  setDisplayPage(0);//표시할 페이지인덱스는 0(첫번째),
	  setScaleFactor(100);//스케일은 100%
	  setBackground(Color.white);//배경색은 흰색으로 세팅
	 }

	 public void setPrintable(Printable p){ //Printable 세팅
	  printable = p;
	  revalidate();//
	 }

	 public void setPageFormat(PageFormat pf){ //PageFormat 세팅
	  pageFormat = pf;
	  revalidate();//갱신
	 }

	 public void setDisplayPage(int page){ //displayPage세팅
	  displayPage = page;
	  revalidate();//갱신
	 }

	 public void setScaleFactor(double scale){//스케일 세팅
	  scaleFactor = scale;
	  revalidate();//갱신
	 }

	 public double getScaleFactor(){//현재 스케일 리턴
	  return scaleFactor;
	 }

	 public Dimension getSizeWithScale(double scale){
	  Insets insets = getInsets();
	  int width = ((int)(pageFormat.getWidth() * scale / 100d))+
	   insets.left + insets.right;
	  int height = ((int)(pageFormat.getHeight() * scale / 100d)) + insets.top + insets.bottom;
	  return new Dimension(width, height);
	 }

	 public Dimension getPreferredSize(){
	  return getSizeWithScale(scaleFactor);
	 }

	 public Dimension getMinimumSize(){
	  return getPreferredSize();
	 }

	 public void paintComponent(Graphics g){
		  super.paintComponent(g);
		  Graphics2D g2 = (Graphics2D)g;
		  Rectangle clipRect = g2.getClipBounds();
		  AffineTransform at = g2.getTransform();
		  int x = (int)(pageFormat.getImageableX() * scaleFactor / 100d);
		  int y = (int)(pageFormat.getImageableY() * scaleFactor / 100d);
		  int w = (int)(pageFormat.getImageableWidth() * scaleFactor / 100d);
		  int h = (int)(pageFormat.getImageableHeight() * scaleFactor / 100d);
		  g2.clipRect(x, y, w, h);
		  g2.scale(scaleFactor / 100, scaleFactor / 100);
		  
		  try{
		   printable.print(g, pageFormat, displayPage);
		  }catch(PrinterException pe){};
		  g2.setTransform(at);
		  g2.setClip(clipRect);

	 }
	 
}



	