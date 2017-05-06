package com.mcaronics.youtube;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

//https://www.youtube.com/watch?v=GKiHB5AzihE


public class EmbedYoutube  {

	private JPanel contentPane;
	private JPanel panel;
	static EmbedYoutube frame;
	
	public static void main(String[] args) {
/*		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new EmbedYoutube();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
		
		NativeInterface.open();
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				JFrame frame =new JFrame("YouTube Viedo");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().add(getBrowser(), BorderLayout.CENTER);
				//frame.setBounds(100, 100, 1024, 768);
				//frame.setResizable(true);
				frame.setSize(400, 300);
				frame.setVisible(true);
				
				
			}
		});
		
		
		NativeInterface.runEventPump();
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			
			@Override
			public void run() {
			
				NativeInterface.close();
				
			}
		}));
		
	}
	
	
	
	public EmbedYoutube() {
/*		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		getBrowser();
		panel.setBounds(199, 84, 775, 610);
		
		contentPane.add(panel);
		frame.add(contentPane);
		*/

	
		
	}
	
	
	public static JPanel getBrowser(){
		JPanel wbPanel =new JPanel(new BorderLayout());
		JWebBrowser wb =new JWebBrowser();
		wbPanel.add(wb, BorderLayout.CENTER);
		wb.setBarsVisible(false);
		wb.navigate("https://www.youtube.com/watch?v=GKiHB5AzihE");
		
		return wbPanel;
	}
	
}



