package com.mcaronics.youtube;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

public class YouTubeViewer implements Runnable {
	String title;
	String url;
	
	
	public YouTubeViewer(String title, String url) {
		this.title=title;
		this.url=url;
	}
	

	public  JPanel getBrowserPanel() {
	    JPanel webBrowserPanel = new JPanel(new BorderLayout());
	    JWebBrowser webBrowser = new JWebBrowser();
	    //webBrowser.setWebBrowserDecoratorFactory(webBrowserDecoratorFactory);
	    
	    webBrowser.setVerifyInputWhenFocusTarget(true);	    
	    webBrowser.setJavascriptEnabled(true);
	    
	    webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
	    webBrowser.setBarsVisible(false);
	    //webBrowser.navigate("https://www.youtube.com/v/b-Cr0EWwaTk?fs=1");
	    
	    webBrowser.navigate(url);
	    return webBrowserPanel;
	}

	
	@Override
	public void run() {
		
	
		NativeInterface.open();
		
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            JFrame frame = new JFrame(title);
	            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	            frame.getContentPane().add(getBrowserPanel(), BorderLayout.CENTER);
	            frame.setSize(800, 600);
	            frame.setLocationByPlatform(true);
	            frame.setVisible(true);
	        }
	    });
	    
	    try{
		    NativeInterface.runEventPump();
		    // don't forget to properly close native components
		    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
		        @Override
		        public void run() {
		            NativeInterface.close();
		            
		        }
		    }));
	    	
	    }catch(Exception e){
	    
	    }

	}
	
	
	
	
	
	
	
	
}