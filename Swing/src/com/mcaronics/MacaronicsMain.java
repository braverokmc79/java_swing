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
		
		JButton btnNewButton = new JButton("농업 기상 관측");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AgriculturaController agriculturaController =new AgriculturaController(frame);
				agriculturaController.setVisible(true);
				MacaronicsMain.this.dispose();
			}
		});
		btnNewButton.setBounds(25, 170, 137, 36);
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
		
		JButton btnJsp = new JButton("웹프로그래밍");
		btnJsp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		
		btnJsp.setBounds(25, 304, 137, 46);
		contentPane.add(btnJsp);
		
		JButton button_3 = new JButton("\uC2A4\uD504\uB9C1 \uAC15\uC88C");
		button_3.setBounds(25, 377, 137, 46);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("\uB18D\uC5C5 \uAE30\uC0C1 \uAD00\uCE21");
		button_4.setBounds(25, 450, 137, 46);
		contentPane.add(button_4);
		
		JButton button_5 = new JButton("\uB0A0\uC528");
		button_5.setBounds(25, 123, 137, 31);
		contentPane.add(button_5);
		
		JButton button_6 = new JButton("\uB18D\uC5C5 \uAE30\uC0C1 \uAD00\uCE21");
		button_6.setBounds(25, 534, 137, 46);
		contentPane.add(button_6);
		
		

		
		
	}
	
	
}



