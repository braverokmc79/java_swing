package com.mcaronics.controller;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.w3c.dom.css.RGBColor;

import com.mcaronics.MacaronicsMain;
import com.mcaronics.common.PDFWebViewer;
import com.mcaronics.dto.JavaDATA;
import com.mcaronics.dto.SpringDATA;
import com.mcaronics.dto.WebDATA;
import com.mcaronics.youtube.YouTubeViewer;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;

import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTree;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.Font;
import javax.swing.UIManager;

public class SpringEducationController extends JFrame {

	private JPanel contentPane;

	
	private static SpringEducationController springEducationController;

	public static SpringEducationController getInstance(){
		if(springEducationController==null){
			springEducationController= new SpringEducationController();
		}
		return springEducationController;
	}
	
	
	
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpringEducationController frame = new SpringEducationController();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	
	
	private SpringEducationController() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 51, 51));
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(179, 72, 827, 625);
		contentPane.add(scrollPane);
		
		//좌측 메뉴	
		MacaronicsMain.leftMenu(contentPane, SpringEducationController.this, SpringEducationController.this);
		
		
		final JList list = new JList(SpringDATA.titles);
		//list.setBorder(new BevelBorder(FRAMEBITS));
		list.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				if(e.getValueIsAdjusting()){
					int index =list.getSelectedIndex();

					for(int i=0; i<WebDATA.urls.length; i++){
						if(index==i){
							String title=SpringDATA.titles[i];
							String url =SpringDATA.urls[i];
							//System.out.println(title + " : " +  url);
							new PDFWebViewer().openURL(url);
					/*		YouTubeViewer youTubeViewer =new YouTubeViewer(title, url);
							Thread thread =new Thread(youTubeViewer);
							thread.start();*/
							
						}
					}
					
				}
			}
		});
		
		
		
		scrollPane.setViewportView(list);
		
		JLabel lblNewLabel = new JLabel("스프링");
		lblNewLabel.setForeground(new Color(255, 255,255));
		lblNewLabel.setFont(new Font("한컴 윤고딕 230", Font.PLAIN, 30));
		lblNewLabel.setBounds(519, 10, 113, 60);
		contentPane.add(lblNewLabel);
		
	
		
	}
}





