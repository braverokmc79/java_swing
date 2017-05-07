package com.mcaronics;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mcaronics.common.HyperLink;
import com.mcaronics.controller.AgriculturaController;
import com.mcaronics.controller.CopyRightController;
import com.mcaronics.controller.JavaEducationController;
import com.mcaronics.controller.MemoCalendarController;
import com.mcaronics.controller.RssConstroller;
import com.mcaronics.controller.SpringEducationController;
import com.mcaronics.controller.WebEducationController;
import com.mcaronics.controller.WikipediaController;
import com.mcaronics.dto.JavaDATA;
import com.mcaronics.dto.RssDTO;
import com.mcaronics.rss.RssReader;
import com.mcaronics.youtube.YouTubeViewer;

import javax.swing.ListSelectionModel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class MacaronicsMain extends JFrame {

	private JPanel contentPane;
	private static MacaronicsMain frame;
	
	private JList tab_1_List;

	private JTextArea tab_1_textArea;
	private JScrollPane scrollPane_1;
	private JTextArea tab_2_textArea;
	
	int j=1;
	int i=0;
	int arraySize=0;
	String[] titles;
	String[] links;
	List<RssDTO> rssData1, rssData2;
	
	
	public static MacaronicsMain getInstance(){
		if(frame==null){
			frame=new MacaronicsMain();
		}
		return frame;
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = MacaronicsMain.getInstance();
				 	
					//아이콘 설정
					frame.setTitle("Macaronics 교육 및 소식");
					Toolkit toolkit = Toolkit.getDefaultToolkit();
					Image img = toolkit.getImage("icon.png");
					frame.setIconImage(img);
					
					
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	private MacaronicsMain() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		
		contentPane.setBackground(new Color(51, 51, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		leftMenu(contentPane, MacaronicsMain.this, frame);
		

		JLabel lblNewLabel = new JLabel("IT 교육 및 소식");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("한컴 윤고딕 230", Font.PLAIN, 30));
		
		
		lblNewLabel.setBounds(519, 10, 260, 52);
		contentPane.add(lblNewLabel);
		
				
		// 탭팬 
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(179, 72, 612, 625);
		contentPane.add(tabbedPane);
		
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("바보상자", null, scrollPane, null);
		
		tab_1_textArea = new JTextArea();
		tab_1_textArea.setFont(new Font("Monospaced", Font.PLAIN, 17));
		tab_1_textArea.setLineWrap(true);        //활성화, 자동 줄 바꿈 기능 
		tab_1_textArea.setWrapStyleWord(true);            // 끊임없이 글을 기능 활성화 단행하다
		
		scrollPane.setViewportView(tab_1_textArea);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		tabbedPane.addTab("minwoohi", null, scrollPane_2, null);
		
		tab_2_textArea = new JTextArea();
		tab_2_textArea.setFont(new Font("Monospaced", Font.PLAIN, 17));
		tab_2_textArea.setLineWrap(true);        //활성화, 자동 줄 바꿈 기능 
		tab_2_textArea.setWrapStyleWord(true); 
		scrollPane_2.setViewportView(tab_2_textArea);
		
		
		
		dataInput();
		
		//첫번째 탭 불러오기
		rss();
		
		//두번째 탭 불러오기
		rss2();
		
		//우측 링크 불러오기
		rightLink();
		
	}
	
	//자료 가져오기
	public void dataInput(){
		 RssReader reader= RssReader.getInstance();
		
		 try{
		 	 //첫번째 탭 자료
		 	
		    String rssUrl ="http://blog.rss.naver.com/seilius";
		    rssData1=reader.writeNew(rssUrl);
			 
			 //두번째 탭 자료
			 String rssTxt ="./data/main.txt";
	         StringBuffer strUrl =reader.readFileScanner(rssTxt,  RssReader.ENCODING);
	         String[] urls = strUrl.toString().split(RssReader.NL);
	   	     rssData2 =reader.writeNews(urls[0]);
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 
		 // 배열의 사이즈가 확정되었으니 생성
		 arraySize= rssData1.size()+rssData2.size();
		 titles=new String[arraySize];
		 links=new String[arraySize];

	}
	
	
	//첫번째 탭
	public void rss(){
       
	    try {
        	StringBuffer buffer =new StringBuffer();    		
        	
        	
    		for(RssDTO dto : rssData1){
    			  buffer.append(j + "번 \n");
    				buffer.append( "제목 :  " + dto.getTitle() + "\n");
    				//buffer.append("링크 :  " + dto.getLink() +"\n");
    				buffer.append("날짜 : " + dto.getPubDate() +"\n");
    				buffer.append("내용 : " + dto.getDescription() +"\n\n");
    				buffer.append("----------------------------------------------------------");
    				buffer.append("\n\n");
    				
    				titles[i]= j + " . "  + dto.getTitle();
    				links[i]=dto.getLink();
    				i++;
    				j++;
    		}
        	
    		//textPane.setText(buffer.toString());
    		tab_1_textArea.setText(buffer.toString());
	    	//HyperLink.linkify(text, URL, toolTip);
    		//textArea1.setListData(rssData.toArray());
	    	//textArea1.add(HyperLink.linkify("하이퍼 링크 입니다.", rssUrl, "하이퍼 링크 입니다."));
	      } catch (Exception e) {
	          e.printStackTrace();
	      }

	}
	
	
	
	public void rss2(){
        try {
        		StringBuffer buffer =new StringBuffer();
    			int k=1;
        		for(RssDTO dto : rssData2){
        			     buffer.append(k + "번 \n");
        				buffer.append("제목 :  " + dto.getTitle() + "\n");
        				buffer.append("링크 :  " + dto.getLink() +"\n");
        				buffer.append("날짜 : " + dto.getPubDate() +"\n");
        				buffer.append("작성자 : " + dto.getAuthor() +"\n");
        				buffer.append("내용 : " + dto.getDescription() +"\n\n");
        				buffer.append("----------------------------------------------------------");
        				buffer.append("\n\n");
        				
        				
        				titles[i]= j + " . "  + dto.getTitle();
        				links[i]=dto.getLink();
        				
        				i++;
        				j++;
        				k++;
        		}
 
        		tab_2_textArea.setText(buffer.toString());
    		
            
      } catch (Exception e) {

          System.out.println("지정된 파일을 찾을 수 없습니다. 파일을 확인하시기 바랍니다.");
      }

	}
	
	//우측 링크
	public void rightLink(){
	    
	    scrollPane_1 = new JScrollPane();
	    scrollPane_1.setBounds(803, 93, 203, 604);
	    contentPane.add(scrollPane_1);
	    
	    
		tab_1_List = new JList(titles);
		tab_1_List.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tab_1_List.setBounds(805, 93, 201, 604);	
		tab_1_List.setVisibleRowCount(10);
		
	    
	    
		//list.setBorder(new BevelBorder(FRAMEBITS));
	    tab_1_List.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
	    tab_1_List.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				if(e.getValueIsAdjusting()){
					int index =tab_1_List.getSelectedIndex();
		/*			
					System.out.println("index : " + list.getSelectedIndex());
					System.out.println("list.getSelectedValue()  : " +list.getSelectedValue());
					*/
					for(int i=0; i<arraySize; i++){
						if(index==i){
							String title=titles[i];
							String url =links[i];
							System.out.println(title + " : " +  url);
							
							YouTubeViewer youTubeViewer =new YouTubeViewer(title, url);
							Thread thread =new Thread(youTubeViewer);
							thread.start();
						}
					}
					
				}
			}
		});
		
	    scrollPane_1.setViewportView(tab_1_List);
	}
	
	
	
	
	
	
	
    public static void leftMenu(JPanel contentPane, Object classNameThis, Object classFrame){

    	
    	
		JButton btnHome = new JButton("Home");
		btnHome.setBackground(new Color(249 , 68 , 90));
		btnHome.setForeground(new Color(255, 255, 255));
		btnHome.setFont(new Font("한컴 윤고딕 230", Font.PLAIN, 13));
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MacaronicsMain macaronicsMain= MacaronicsMain.getInstance();
				if(classNameThis==macaronicsMain){
					return;
				}
				iconSetting(macaronicsMain);
				macaronicsMain.setVisible(true);
				((Window) classNameThis).dispose();
			}
			
		});
		btnHome.setBounds(25, 65, 137, 46);
		contentPane.add(btnHome);
		
		
		JButton btnNewButton = new JButton("날씨");
		btnNewButton.setFont(new Font("한컴 윤고딕 230", Font.PLAIN, 13));
		btnNewButton.setBackground(new Color(12 , 155 , 175));
		btnNewButton.setForeground(new Color(255, 255, 255));
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AgriculturaController agriculturaController =AgriculturaController.getInstance();
				
				if(classNameThis==agriculturaController){
					return;
				}
				
				iconSetting(agriculturaController);
				agriculturaController.setVisible(true);
				((Window) classNameThis).dispose();
				
				
			}
		});
		btnNewButton.setBounds(25, 135, 137, 46);
		contentPane.add(btnNewButton);
		

		JButton btnWiki = new JButton("위키 검색");
		btnWiki.setFont(new Font("한컴 윤고딕 230", Font.PLAIN, 13));
		btnWiki.setBackground(new Color(12 , 155 , 175));
		btnWiki.setForeground(new Color(255, 255, 255));
		btnWiki.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WikipediaController wiki =WikipediaController.getInstance();
				if(classNameThis==wiki){
					return;
				}
				iconSetting(wiki);
				wiki.setVisible(true);
				((Window)classNameThis).dispose();
			}
		});
		btnWiki.setBounds(25, 205, 137, 46);
		contentPane.add(btnWiki);
		
		
		
		
		JButton javaButton = new JButton("자바 강좌");
		javaButton.setFont(new Font("한컴 윤고딕 230", Font.PLAIN, 13));
		javaButton.setBackground(new Color(12 , 155 , 175));
		javaButton.setForeground(new Color(255, 255, 255));
		javaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JavaEducationController java = JavaEducationController.getInstance();
				if(classNameThis==java){
					return;
				}
				
				iconSetting(java);
				java.setVisible(true);
				((Window) classNameThis).dispose();
				
			}
		});
		javaButton.setBounds(25, 275, 137, 46);
		contentPane.add(javaButton);
		
		
		JButton btnJsp = new JButton("웹프로그래밍 강좌");
		btnJsp.setFont(new Font("한컴 윤고딕 230", Font.PLAIN, 13));
		
		btnJsp.setBackground(new Color(12 , 155 , 175));
		btnJsp.setForeground(new Color(255, 255, 255));
		btnJsp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				WebEducationController web = WebEducationController.getInstance();
				if(classNameThis==web){
					return;
				}
				iconSetting(web);
				web.setVisible(true);
				((Window)classNameThis).dispose();
				
			}
		});
		
		
		btnJsp.setBounds(25, 345, 137, 46);
		contentPane.add(btnJsp);
		
		JButton btnSpring = new JButton("스프링 강좌");
		btnSpring.setBackground(new Color(12 , 155 , 175));
		btnSpring.setFont(new Font("한컴 윤고딕 230", Font.PLAIN, 13));
		btnSpring.setForeground(new Color(255, 255, 255));
		btnSpring.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SpringEducationController spring= SpringEducationController.getInstance();
				if(classNameThis==spring){
					return;
				}
				iconSetting(spring);
				spring.setVisible(true);
				((Window)classNameThis).dispose();
			}
		});
		btnSpring.setBounds(25, 415, 137, 46);
		contentPane.add(btnSpring);
		
		JButton btnScheduling = new JButton("스케쥴러");
		btnScheduling.setBackground(new Color(12 , 155 , 175));
		btnScheduling.setFont(new Font("한컴 윤고딕 230", Font.PLAIN, 13));
		btnScheduling.setForeground(new Color(255, 255, 255));
		btnScheduling.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemoCalendarController memo = MemoCalendarController.getInstance();
				if(classNameThis==memo){
					return;
				}
				iconSetting(memo);
				memo.setVisible(true);
				((Window)classNameThis).dispose();
				
			}
		});
		btnScheduling.setBounds(25, 485, 137, 46);
		contentPane.add(btnScheduling);
		
		

		
		
		JButton btnNews = new JButton("오늘의 뉴스");
		btnNews.setBackground(new Color(12 , 155 , 175));
		btnNews.setFont(new Font("한컴 윤고딕 230", Font.PLAIN, 13));
		btnNews.setForeground(new Color(255, 255, 255));
		btnNews.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RssConstroller rss = RssConstroller.getInstance();
				if(classNameThis==rss){
					return;
				}
				iconSetting(rss);
				rss.setVisible(true);
			   ((Window)classNameThis).dispose();
			}
		});
		btnNews.setBounds(25, 555, 137, 46);
		contentPane.add(btnNews);
		
		
		JButton btnCopyright = new JButton("Copyright");
		btnCopyright.setBackground(new Color(12 , 155 , 175));
		btnCopyright.setFont(new Font("한컴 윤고딕 230", Font.PLAIN, 13));
		btnCopyright.setForeground(new Color(255, 255, 255));
		btnCopyright.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CopyRightController copy = CopyRightController.getInstance();
				if(classNameThis==copy){
					return;
				}
				iconSetting(copy);
				copy.setVisible(true);
				((Window)classNameThis).dispose();
			}
		});
		btnCopyright.setBounds(25, 625, 137, 46);
		contentPane.add(btnCopyright);
	}
	
	
    public static void iconSetting(Object frame){
		//아이콘 설정
		((Frame) frame).setTitle("Macaronics 교육 및 소식");
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("icon.png");
		((Frame) frame).setIconImage(img);
		
		
    }
    
    

    
    
}



