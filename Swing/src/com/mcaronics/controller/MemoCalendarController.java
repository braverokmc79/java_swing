package com.mcaronics.controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mcaronics.MacaronicsMain;
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

	

	private static MemoCalendarController memoCalendarController;

	public static MemoCalendarController getInstance(){
		if(memoCalendarController==null){
			memoCalendarController= new MemoCalendarController();
		}
		return memoCalendarController;
	}
	
	


	private MemoCalendarController() {

				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		
		contentPane.setBackground(new Color(51, 51, 51));
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		//panel.setBounds(248, 75, 733, 634);
		panel.setBounds(179, 72, 827, 625);
		panel.setLayout(null);
		
		JDBCpro jdbcType =new JDBCpro();
		jdbcType.setBounds(12, 10, 709, 625);
		panel.add(jdbcType);
		jdbcType.setLayout(null);
	
		
		
		//좌측 메뉴	
		MacaronicsMain.leftMenu(contentPane, MemoCalendarController.this, MemoCalendarController.this);
		
	
		
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("스케쥴러");
		lblNewLabel.setFont(new Font("한컴 윤고딕 230", Font.PLAIN, 30));
		lblNewLabel.setForeground(UIManager.getColor("Button.highlight"));
		lblNewLabel.setBounds(516, 10, 145, 60);
		contentPane.add(lblNewLabel);
		
				
		
	}
	
	
}



