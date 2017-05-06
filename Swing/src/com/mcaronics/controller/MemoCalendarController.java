package com.mcaronics.controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mcaronics.calendar.JDBCpro;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.Font;

public class MemoCalendarController extends JFrame {

	private JPanel contentPane;
	static MemoCalendarController frame;
	private JPanel panel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MemoCalendarController(null);
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public MemoCalendarController(Object obj) {
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
		
		JButton btnNewButton = new JButton("농업 기상 관측");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AgriculturaController agriculturaController =new AgriculturaController(frame);
				agriculturaController.setVisible(true);
				MemoCalendarController.this.dispose();
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
		
		panel = new JPanel();
		panel.setBounds(248, 75, 733, 634);
		panel.setLayout(null);
		
		JDBCpro jdbcType =new JDBCpro();
		jdbcType.setBounds(12, 10, 709, 625);
		panel.add(jdbcType);
		jdbcType.setLayout(null);
	
		

	
		
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("\uC2A4\uCF00\uC974\uB7EC");
		lblNewLabel.setFont(new Font("문체부 제목 바탕체", Font.PLAIN, 32));
		lblNewLabel.setForeground(UIManager.getColor("Button.highlight"));
		lblNewLabel.setBounds(516, 10, 145, 60);
		contentPane.add(lblNewLabel);
		
				
		
	}
	
	
}



