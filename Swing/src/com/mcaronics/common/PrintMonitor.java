package com.mcaronics.common;

import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class PrintMonitor implements Pageable{ //프린팅 작업관리 클래스
	 protected PrinterJob printerJob;
	 protected Pageable pageable;
	 protected JOptionPane optionPane;
	 protected JDialog statusDialog;

	 public PrintMonitor(Pageable p){
	  pageable = p;
	  printerJob = PrinterJob.getPrinterJob();
	  String[] Options = {"Cancel"};
	  optionPane = new JOptionPane("", JOptionPane.INFORMATION_MESSAGE, JOptionPane.CANCEL_OPTION, null, Options);
	  statusDialog = optionPane.createDialog(null, "Printer Job Status");
	 }

	 public void performPrint(boolean showDialog) throws PrinterException {
	  printerJob.setPageable(this);
	  if(showDialog){
	   boolean isOk = printerJob.printDialog();
	   if(!isOk) return;
	  }
	  optionPane.setMessage("Initiating printer job...");
	  Thread t = new Thread(new Runnable(){
	   public void run(){
	    statusDialog.setVisible(true);
	    if(optionPane.getValue() !=
	     JOptionPane.UNINITIALIZED_VALUE){
	     printerJob.cancel();
	    }
	   }
	  });
	  t.start();
	  printerJob.print();
	  statusDialog.setVisible(false);
	 }

	 public int getNumberOfPages(){
	  return pageable.getNumberOfPages();
	 }

	 public Printable getPrintable(int index){
	  optionPane.setMessage("Printing page " + (index + 1));
	  return pageable.getPrintable(index);
	 }

	 public PageFormat getPageFormat(int index){
	  return pageable.getPageFormat(index);
	 }
	}



	
