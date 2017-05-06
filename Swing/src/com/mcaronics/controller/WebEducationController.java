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

import com.mcaronics.dto.JavaDATA;
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

public class WebEducationController extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WebEducationController frame = new WebEducationController();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WebEducationController() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 51, 51));
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(187, 97, 803, 621);
		contentPane.add(scrollPane);
		
		String[] titles=new String[WebDATA.titles.length];
		for(int i=0; i<WebDATA.titles.length; i++){
			titles[i]= (i+1) +" " + WebDATA.titles[i];
		}
		
		JList list = new JList(titles);
		//list.setBorder(new BevelBorder(FRAMEBITS));
		list.setFont(new Font("굴림", Font.PLAIN, 20));
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				if(e.getValueIsAdjusting()){
					int index =list.getSelectedIndex();
		/*			
					System.out.println("index : " + list.getSelectedIndex());
					System.out.println("list.getSelectedValue()  : " +list.getSelectedValue());
					*/
					for(int i=0; i<WebDATA.urls.length; i++){
						if(index==i){
							String title=titles[i];
							String url =WebDATA.urls[i];
							//System.out.println(title + " : " +  url);
							
							YouTubeViewer youTubeViewer =new YouTubeViewer(title, url);
							Thread thread =new Thread(youTubeViewer);
							thread.start();
						}
					}
					
				}
			}
		});
		
		
		
		scrollPane.setViewportView(list);
		
		JLabel lblNewLabel = new JLabel("웹프로그래밍");
		lblNewLabel.setForeground(new Color(255, 255,255));
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 33));
		lblNewLabel.setBounds(509, 26, 241, 60);
		contentPane.add(lblNewLabel);
		
	
		JButton button1 = new JButton("New button");
		button1.setBounds(136, 479, 196, 192);
		
		JButton button2 = new JButton("New button");
		button2.setBounds(136, 579, 196, 192);
		
	}
}





