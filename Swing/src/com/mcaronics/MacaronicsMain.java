package com.mcaronics;

import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mcaronics.controller.AgriculturaController;
import com.mcaronics.controller.JavaEducationController;
import com.mcaronics.youtube.EmbedYoutube;

public class MacaronicsMain extends JFrame {

	private JPanel contentPane;
	static MacaronicsMain frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MacaronicsMain(null);
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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("날씨");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AgriculturaController agriculturaController =new AgriculturaController(frame);
				agriculturaController.setVisible(true);
				MacaronicsMain.this.dispose();
			}
		});
		btnNewButton.setBounds(25, 119, 137, 36);
		contentPane.add(btnNewButton);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnHome.setBounds(25, 64, 137, 36);
		contentPane.add(btnHome);
		
		JButton javaButton = new JButton("자바 강좌");
		javaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JavaEducationController java =new JavaEducationController();
				java.setVisible(true);
				
			}
		});
		javaButton.setBounds(25, 233, 137, 46);
		contentPane.add(javaButton);
		
		JButton btnJsp = new JButton("웹프로그래밍 강좌");
		btnJsp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		
		btnJsp.setBounds(25, 304, 137, 46);
		contentPane.add(btnJsp);
		
		JButton button_3 = new JButton("\uC2A4\uD504\uB9C1 \uAC15\uC88C");
		button_3.setBounds(25, 377, 137, 46);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("스케쥴러");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		button_4.setBounds(25, 450, 137, 46);
		contentPane.add(button_4);
		
		JButton button_5 = new JButton("위키 검색");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_5.setBounds(25, 181, 137, 31);
		contentPane.add(button_5);
		
		JButton button_6 = new JButton("오늘의 뉴스");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		button_6.setBounds(25, 534, 137, 46);
		contentPane.add(button_6);
		
		JButton btnNewButton_1 = new JButton("Copyright");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(25, 619, 137, 58);
		contentPane.add(btnNewButton_1);
		
		

		
		
	}
	
	
}



