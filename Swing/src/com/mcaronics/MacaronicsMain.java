package com.mcaronics;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mcaronics.controller.AgriculturaController;
import com.mcaronics.controller.CopyRightController;
import com.mcaronics.controller.JavaEducationController;
import com.mcaronics.controller.MemoCalendarController;
import com.mcaronics.controller.RssConstroller;
import com.mcaronics.controller.SpringEducationController;
import com.mcaronics.controller.WebEducationController;
import com.mcaronics.controller.WikipediaController;

public class MacaronicsMain extends JFrame {

	private JPanel contentPane;
	static MacaronicsMain frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MacaronicsMain(null);
				 	
					//아이콘 설정
					frame.setTitle("Macaronics 교육 및 소식");
					Toolkit toolkit = Toolkit.getDefaultToolkit();
					Image img = toolkit.getImage("icon.png");
					frame.setIconImage(img);
					
					
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	public MacaronicsMain(Object obj) {
		if(obj!=null){
			AgriculturaController agri=(AgriculturaController)obj;
			//((Window) agri).dispose();	
			
		}
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		
		contentPane.setBackground(new Color(51, 51, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		leftMenu(contentPane, MacaronicsMain.this, frame);
	}
	
	
	
	
    public static void leftMenu(JPanel contentPane, Object classNameThis, Object classFrame){
		
		JButton btnHome = new JButton("Home");
		btnHome.setBackground(new Color(249 , 68 , 90));
		btnHome.setForeground(new Color(255, 255, 255));
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MacaronicsMain macaronicsMain=new MacaronicsMain(classFrame);
				macaronicsMain.setVisible(true);
				((Window) classNameThis).dispose();
			}
			
		});
		btnHome.setBounds(25, 65, 137, 46);
		contentPane.add(btnHome);
		
		
		JButton btnNewButton = new JButton("날씨");
		btnNewButton.setBackground(new Color(12 , 155 , 175));
		btnNewButton.setForeground(new Color(255, 255, 255));
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AgriculturaController agriculturaController =AgriculturaController.getInstance();
				iconSetting(agriculturaController);
				agriculturaController.setVisible(true);
				((Window) classNameThis).dispose();
			}
		});
		btnNewButton.setBounds(25, 135, 137, 46);
		contentPane.add(btnNewButton);
		

		JButton btnWiki = new JButton("위키 검색");
		btnWiki.setBackground(new Color(12 , 155 , 175));
		btnWiki.setForeground(new Color(255, 255, 255));
		btnWiki.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WikipediaController wiki =WikipediaController.getInstance();
				iconSetting(wiki);
				wiki.setVisible(true);
				((Window)classNameThis).dispose();
			}
		});
		btnWiki.setBounds(25, 205, 137, 46);
		contentPane.add(btnWiki);
		
		
		
		
		JButton javaButton = new JButton("자바 강좌");
		javaButton.setBackground(new Color(12 , 155 , 175));
		javaButton.setForeground(new Color(255, 255, 255));
		javaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JavaEducationController java = JavaEducationController.getInstance();
				iconSetting(java);
				java.setVisible(true);
				((Window) classNameThis).dispose();
				
			}
		});
		javaButton.setBounds(25, 275, 137, 46);
		contentPane.add(javaButton);
		
		
		JButton btnJsp = new JButton("웹프로그래밍 강좌");
		btnJsp.setBackground(new Color(12 , 155 , 175));
		btnJsp.setForeground(new Color(255, 255, 255));
		btnJsp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				WebEducationController web = WebEducationController.getInstance();
				iconSetting(web);
				web.setVisible(true);
				((Window)classNameThis).dispose();
				
			}
		});
		
		
		btnJsp.setBounds(25, 345, 137, 46);
		contentPane.add(btnJsp);
		
		JButton btnSpring = new JButton("스프링 강좌");
		btnSpring.setBackground(new Color(12 , 155 , 175));
		btnSpring.setForeground(new Color(255, 255, 255));
		btnSpring.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SpringEducationController spring= SpringEducationController.getInstance();
				iconSetting(spring);
				spring.setVisible(true);
				((Window)classNameThis).dispose();
			}
		});
		btnSpring.setBounds(25, 415, 137, 46);
		contentPane.add(btnSpring);
		
		JButton btnScheduling = new JButton("스케쥴러");
		btnScheduling.setBackground(new Color(12 , 155 , 175));
		btnScheduling.setForeground(new Color(255, 255, 255));
		btnScheduling.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemoCalendarController memo = MemoCalendarController.getInstance();
				iconSetting(memo);
				memo.setVisible(true);
				((Window)classNameThis).dispose();
				
			}
		});
		btnScheduling.setBounds(25, 485, 137, 46);
		contentPane.add(btnScheduling);
		
		

		
		
		JButton btnNews = new JButton("오늘의 뉴스");
		btnNews.setBackground(new Color(12 , 155 , 175));
		btnNews.setForeground(new Color(255, 255, 255));
		btnNews.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RssConstroller rss = RssConstroller.getInstance();
				iconSetting(rss);
				rss.setVisible(true);
			   ((Window)classNameThis).dispose();
			}
		});
		btnNews.setBounds(25, 555, 137, 46);
		contentPane.add(btnNews);
		
		
		JButton btnCopyright = new JButton("Copyright");
		btnCopyright.setBackground(new Color(12 , 155 , 175));
		btnCopyright.setForeground(new Color(255, 255, 255));
		btnCopyright.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CopyRightController copy =new CopyRightController();
				iconSetting(copy);
				copy.setVisible(true);
				((Window)classNameThis).dispose();
			}
		});
		btnCopyright.setBounds(25, 625, 137, 46);
		contentPane.add(btnCopyright);
	}
	
	
    public static void iconSetting(Object frame){
		//아이콘 설정
		((Frame) frame).setTitle("Macaronics 교육 및 소식");
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("icon.png");
		((Frame) frame).setIconImage(img);
		
		
    }
    
	
}



