package com.mcaronics.rss;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import com.mcaronics.dto.RssDTO;

public class RssConstroller extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;

	private String[] titles={
			"속보", "정치", "경제","사회", "국제" , "문화", "연예", "스포츠",
			"풀영상", "뉴스랭킹","뉴스룸","아침&", "뉴스현장", "정치부회의"
	};
	
	private Color[] colos ={			
		    new Color(154, 205, 50, 150), new Color(250, 235,215, 150), new Color (255, 192,203, 200), new Color (211, 211,211),
            new Color (176, 224,230, 150), new Color (255, 255,255), new Color(251, 251,147),
		    new Color(154, 205,50, 150), new Color(250, 235,215), new Color (255, 192,203), new Color (211, 211,211),
            new Color (176, 224,230), new Color (255, 255,255), new Color(251, 251,147)
	};

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RssConstroller frame = new RssConstroller();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public RssConstroller() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setResizable(false);
		setBounds(100, 100, 1024, 768);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 51, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
				
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(236, 120, 770, 598);
		contentPane.add(tabbedPane);
		
		JLabel lblNewLabel = new JLabel("오늘의 뉴스");
		
		
		
		lblNewLabel.setFont(new Font("한컴 윤고딕 230", Font.PLAIN, 30));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(564, 35, 155, 61);
		contentPane.add(lblNewLabel);
		
	
		dataList();
		
	}
	
	
	
	public void dataList(){
		RssReader reader= RssReader.getInstance();

        try {
	
          String rssTxt ="./data/rss.txt";
          StringBuffer strUrl =reader.readFileScanner(rssTxt,  RssReader.ENCODING);
          if(strUrl != null ){
              String[] urls = strUrl.toString().split(RssReader.NL);
            
              for(int i=0; i < urls.length;i++) {           
            	  List<RssDTO> items =reader.writeNews(urls[i]);
      
	          		JScrollPane scrollPane = new JScrollPane();
	          		
	        		tabbedPane.addTab(titles[i], null, scrollPane, null);
	        		tabbedPane.setBackgroundAt(i , colos[i]);
	        		
	        		JTextPane textPane = new JTextPane();    		
	        		textPane.setBackground(colos[i]);
	        		textPane.setEditable(false);
	        		
	        		
	        		StringBuffer buffer =new StringBuffer();
        		
	        		for(RssDTO dto : items){
	        				
	        				buffer.append("제목 :  " + dto.getTitle() + "\n");
	        				buffer.append("링크 :  " + dto.getLink() +"\n");
	        				buffer.append("날짜 : " + dto.getPubDate() +"\n");
	        				buffer.append("내용 : " + dto.getDescription() +"\n\n");
	        				buffer.append("-------------------------------------------------------------------"
	        						+ "----------------------------------------------------------------------"
	        						+ "--------------------------------------------");
	        				buffer.append("\n\n");
	        		}
	        	

	                textPane.setText(buffer.toString());
	    			scrollPane.setViewportView(textPane);
              }
              
          }
           
      } catch (Exception e) {

          System.out.println("지정된 파일을 찾을 수 없습니다. 파일을 확인하시기 바랍니다.");
      }

		
		
		
  }
	
	
	

	
	
	
	
	
}
