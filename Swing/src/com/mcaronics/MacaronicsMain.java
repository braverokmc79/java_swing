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
		btnNewButton.setBounds(27, 75, 122, 23);
		contentPane.add(btnNewButton);
	}
	

	
}
