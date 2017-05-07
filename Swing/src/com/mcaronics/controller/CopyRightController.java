package com.mcaronics.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import com.mcaronics.MacaronicsMain;

public class CopyRightController extends JFrame {

	private JPanel contentPane;

	private static CopyRightController controller;
	private JTextPane textPane;
	private JLabel lblNewLabel_1;

	public static CopyRightController getInstance(){
		if(controller==null){
			controller= new CopyRightController();
		}
		return controller;
	}
	
	

	private CopyRightController() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 51, 51));
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//좌측 메뉴	
		MacaronicsMain.leftMenu(contentPane, CopyRightController.this, CopyRightController.this);
		
		JLabel lblNewLabel = new JLabel("CopyRight");
		lblNewLabel.setForeground(new Color(255, 255,255));
		lblNewLabel.setFont(new Font("한컴 윤고딕 230", Font.PLAIN, 30));
		lblNewLabel.setBounds(509, 26, 162, 60);
		contentPane.add(lblNewLabel);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBackground(Color.GRAY);
		textPane.setForeground(Color.WHITE);
		textPane.setFont(new Font("Arial", Font.PLAIN, 20));
		textPane.setBounds(273, 143, 715, 527);
		contentPane.add(textPane);
		
			
		StringBuilder builder =new StringBuilder();
		builder.append("\n\n\n\n\n\n" );
		builder.append(" ⓒ 2017. Junho Choi all rights reserved. \n\n" );
		builder.append(" (C) 2017. Junho Choi all rights reserved. \n\n" );
		builder.append(" Copyright 2017. Junho Choi all rights reserved. \n\n" );
		builder.append(" Copyright 2017. Junho Choi All pictures cannot be copied without permission. \n\n" );
		textPane.setText(builder.toString());
		
		
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(420, 22, 77, 77);
		File file =new File("icon.png");
		ImageIcon icon=null;
		try {
			icon = new ImageIcon(ImageIO.read(file));
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image imageSrc=icon.getImage();
		Image imageNew =imageSrc.getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
		icon=new ImageIcon(imageNew);
		lblNewLabel_1.setIcon(icon);
		
		contentPane.add(lblNewLabel_1);
	}
}





