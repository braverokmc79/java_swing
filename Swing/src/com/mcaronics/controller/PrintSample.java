package com.mcaronics.controller;

import java.awt.event.ActionEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.mcaronics.common.PrintMonitor;
import com.mcaronics.common.PrintPreviewer;
import com.mcaronics.common.TablePrinter;

public class PrintSample extends JFrame{  //메인 실행 클래스
 protected JTable table;
 protected PageFormat pageFormat;

 
 
	 public JTable getTable() {
		return table;
	}
	
	public void setTable(JTable table) {
		this.table = table;
	}
	
	public PageFormat getPageFormat() {
		return pageFormat;
	}
	
	public void setPageFormat(PageFormat pageFormat) {
		this.pageFormat = pageFormat;
	}

public static void main(String[] args){
/*	  PrintSample ps = new PrintSample();
	  ps.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  ps.pack();
	  ps.setVisible(true);*/
 }

 public PrintSample(JTable table){  //생성자 함수
  super("Sample Print Application");
  this.table = table;

 //샘플 테이블 인스턴스생성하여 인수로 넘겨준다
  //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); //테이블 리사이즈모드 오프
  table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS); //테이블 리사이즈모드 오프
  setContentPane(new JScrollPane(table));  //작업영역 스크롤페인에 테이블 삽입하여 설정
  pageFormat = new PageFormat();  //페이지포멧 인스턴스 생성
  buildMenuBar(); //메뉴생성 메소드 호출
 }

 protected void buildMenuBar(){  //메뉴생성 메소드
  JMenuBar menuBar = new JMenuBar();
  JMenu menu = new JMenu("File");
  menu.add(new AbstractAction("Page Setup", new ImageIcon("empty.gif")) {
   public void actionPerformed(ActionEvent event){
    onPageSetup(); //페이지 셋팅메소드 호출
   }
  });
  menu.add(new AbstractAction("Print Preview", new ImageIcon("preview.gif")) {
   public void actionPerformed(ActionEvent event){
    onPrintPreview();  // 미리보기 메소드 호출
   }
  });
  menu.add(new AbstractAction("Print", new ImageIcon("print.gif")) {
   public void actionPerformed(ActionEvent event){
    onPrint(); //프린트작업 실행메소드 호출
   }
  });
  menu.addSeparator();
  menu.add(new AbstractAction("Exit", new ImageIcon("empty.gif")) {
   public void actionPerformed(ActionEvent event){
    System.exit(0); //나가기
   }
  });
  menuBar.add(menu);
  setJMenuBar(menuBar);  //현재 프레임에 메뉴바 세팅
 }

 protected void onPageSetup(){  //페이지세팅 메소드
  Thread t = new Thread(new Runnable() {
   public void run(){
    PrinterJob pj = PrinterJob.getPrinterJob(); 

//현재시스템의 프린터인스턴스 가져와서 프린터잡 인스턴스 생성
    pageFormat = pj.pageDialog(pageFormat);  //다이얼로그 호출하여 페이지포멧 저장
   }
   });
   t.start();
  }

  protected void onPrintPreview() { //미리보기 메소드
   Thread t = new Thread(new Runnable(){
    public void run(){
     TablePrinter tp = new TablePrinter(table, pageFormat);

//샘플테이블과 페이지를 인스턴스로 넘겨주어 테이블 프린터 객체 생성
     PrintPreviewer pp = new PrintPreviewer(tp, 0, table);

 //테이블 프린터객체를 인수로 넘겨주어 미리보기객체 생성
     JDialog dlg = new JDialog(PrintSample.this, "프린트 미리보기");

//다이얼로그 출력, 오너는 현재창...
     dlg.getContentPane().add(pp);

//다이얼로그 작업영역 가져온후 미리보기 패널 추가(작업영역 미리보기패널로 설정)
     dlg.setSize(400, 300);
     dlg.setVisible(true);
    }
   });
   t.start();
  }

  public  void onPrint(){  //실제 출력메소드
  Thread t = new Thread(new Runnable(){
   public void run(){
    TablePrinter tp = new TablePrinter(table, pageFormat); //테이블프린터 객체 생성
    PrintMonitor pm = new PrintMonitor(tp);

//테이블프린터 객체를 인수로 넘겨주어 프린터모니터 객체 생성
    try{
     pm.performPrint(true);  //프린트 실행...
    }catch(PrinterException pe){
     JOptionPane.showMessageDialog(PrintSample.this, "Printing error:"+pe.getMessage());

//에러발생시 메시지박스 출력
    }
   }
  });
  t.start();
  }


}



