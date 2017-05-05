package com.mcaronics.common;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Pageable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import com.mcaronics.controller.PrintSample;

public class PrintPreviewer extends JPanel{ //프린트 미리보기 클래스,  각종 기능들을 구현..
	 protected Pageable pageable;
	 protected PrintComponent printComponent;
	 protected int pageIndex;

	 protected JScrollPane scrollPane;
	 protected JButton previousButton;
	 protected JButton nextButton;
	 protected JButton sizeButton;
	 protected JButton printButton;
	 protected JTextField scaleText;

	 protected JTable agTable;
	 
	 public PrintPreviewer(Pageable p, int page, JTable agTable){
		
		 this.agTable=agTable;
		 
	  pageable = p;
	  pageIndex = page;
	  printComponent = new PrintComponent(null, null);
	  printComponent.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	  buildLayout();
	  displayPage(pageIndex);
	 }

	 protected void buildLayout(){
	  setLayout(new BorderLayout());
	  JPanel panel = new JPanel();
	  panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
	  panel.add(printComponent);
	  scrollPane = new JScrollPane(panel);
	  add(scrollPane, BorderLayout.CENTER);
	  add(getButtomPanel(), BorderLayout.SOUTH);
	  addListener();
	 }

	 protected JPanel getButtomPanel(){
	  JPanel outer= new JPanel();
	  outer.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
	  JPanel inner = new JPanel();
	  inner.setLayout(new GridLayout(1, 2, 10, 0));
	  previousButton = new JButton("이전");
	  inner.add(previousButton);
	  nextButton = new JButton("다음");
	  inner.add(nextButton);
	  outer.add(inner);
	  scaleText = new JTextField(3);
	  outer.add(scaleText);
	  sizeButton = new JButton("사이즈 고정");
	  outer.add(sizeButton);
	  printButton = new JButton("프리트");
	  outer.add(printButton);
	  return outer;
	 }

	 protected void addListener(){
	  previousButton.addActionListener(new ActionListener(){
	   public void actionPerformed(ActionEvent event){
	    displayPage(pageIndex -1);
	   }
	  });

	  nextButton.addActionListener(new ActionListener(){
	   public void actionPerformed(ActionEvent event){
	    displayPage(pageIndex + 1);
	   }
	  });

	  sizeButton.addActionListener(new ActionListener(){
	   public void actionPerformed(ActionEvent event){
	    sizeToFit();
	   }
	  });

	  printButton.addActionListener(new ActionListener(){
		   public void actionPerformed(ActionEvent event){
				//프린트하기
				PrintSample print=new PrintSample(agTable);
				
				print.onPrint();
		   }
		 });

		  
	  
	  
	  scaleText.addActionListener(new ActionListener(){
	   public void actionPerformed(ActionEvent event){
	    try{
	     int scale = Integer.parseInt(scaleText.getText());
	     printComponent.setScaleFactor(scale);
	    }catch(NumberFormatException nfe){};
	   }
	  });
	 }

	 protected void displayPage(int index){
	  pageIndex = index;
	  printComponent.setPrintable(pageable.getPrintable(pageIndex));
	  printComponent.setPageFormat(pageable.getPageFormat(pageIndex));
	  printComponent.setDisplayPage(index);
	  previousButton.setEnabled(pageIndex > 0);
	  nextButton.setEnabled(pageIndex < (pageable.getNumberOfPages() - 1));
	  repaint();
	 }

	 protected void sizeToFit(){
	  int newScaleFactor;
	  Dimension compSize = printComponent.getSizeWithScale(100d);
	  Dimension viewSize = scrollPane.getSize();

	  int scaleX = (viewSize.width - 25) * 100 / compSize.width;
	  int scaleY = (viewSize.height - 25) * 100 / compSize.height;
	  newScaleFactor = Math.min(scaleX, scaleY);

	  printComponent.setScaleFactor(newScaleFactor);
	  scaleText.setText(Integer.toString(newScaleFactor));
	  repaint();
	 }
	 
	 
}


	