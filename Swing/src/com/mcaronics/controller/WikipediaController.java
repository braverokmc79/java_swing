package com.mcaronics.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Vector;
import java.util.regex.PatternSyntaxException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mcaronics.MacaronicsMain;
import com.mcaronics.common.PDFWebViewer;
import com.mcaronics.dto.WikipediaDTO;

public class WikipediaController extends JFrame {

	static WikipediaController frame;
	
	Document doc;
	private JPanel contentPane;

	Vector<Object> items;
	private JTable agTable;

	//private MacaronicsMain main;

	private JScrollPane scrollPane;
	private JTextArea textArea;
	
	private WikipediaDTO dto;
	private JTextField txtSearch;
	private String txtS;
	private boolean EN_AND_KO=false;
	
	
	
	private static WikipediaController wikipediaController ;

	public static WikipediaController getInstance(){
		if(wikipediaController==null){
			wikipediaController=new WikipediaController();
		}
		return wikipediaController;
	}
	
	private WikipediaController() {
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		contentPane.setBackground(new Color(51, 51, 51));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(179, 72, 827, 625);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		//가로 스크롤 차단/
		textArea.getScrollableTracksViewportWidth();
		textArea.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		textArea.setLineWrap(true);        //활성화, 자동 줄 바꿈 기능 
		textArea.setWrapStyleWord(true);            // 끊임없이 글을 기능 활성화 단행하다
		
		scrollPane.setViewportView(textArea);

		
		//좌측 메뉴	
		MacaronicsMain.leftMenu(contentPane, WikipediaController.this, WikipediaController.this);
			
		
		
		JLabel lblNewLabel = new JLabel("위키백과 요약 검색");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("한컴 윤고딕 230", Font.PLAIN, 30));
		lblNewLabel.setBounds(594, 10, 298, 52);
		contentPane.add(lblNewLabel);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(205, 21, 200, 45);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnSearch = new JButton("검색");
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setBackground(Color.ORANGE);
		btnSearch.addActionListener(new ActionListener() {
			@SuppressWarnings("null")
			public void actionPerformed(ActionEvent e) {
				
				String txt=txtSearch.getText();
				if(txt!=null && txt.trim().length() >0){
					System.out.println("txt : " + txt);			
					byte[] byteText =txt.getBytes(Charset.forName("utf-8"));
					
					try {
						System.out.println(new String(byteText, "utf-8"));
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					txtS=txt;
					
					test();
					textArea.setText(dto.getContent());
					if(EN_AND_KO){
						//검색 단어가 영어이면 번역
						int confirm =JOptionPane.showConfirmDialog(WikipediaController.this, 
								"네이버 번역기 창으로 보시겠습니까?");
						
						if(confirm==0){
							System.out.println("네이버 이동");
							String txt0 ="";
							if(textArea.getText().length()>200){
								txt0=textArea.getText().substring(0, 200);	
							}else{
								txt0=textArea.getText();
							}
							
							String txt1=txt0.replaceAll(" ", "%20");
							String txt2=txt1.replaceAll("\n", "%20");
							String url ="http://translate.naver.com/#/en/ko/"+txt2;
							//System.out.println(url);
							new PDFWebViewer().openURL(url);
						}else{
							System.out.println("그냥 본다");
						}
						
					}
					
				}else{
					
					JOptionPane.showMessageDialog(WikipediaController.this, "검색할 단어를 입력 해주세요");
					txtSearch.setFocusable(true);
				}		
			}
		});
		btnSearch.setBounds(435, 21, 103, 45);
		contentPane.add(btnSearch);
	}


	
	public String test() {

		String result = "";

		try {
			
			if(txtS==null){
				//doc = Jsoup.connect("https://ko.wikipedia.org/").get();
				//System.out.println(" 검색 단어 없음");
			}else{
				System.out.println(" 검색 " +txtS);
					
					//한글인지 검색
					try {
						//String nickname = "닉Name좋아요123";
						String nickname = txtS;
	
						if(nickname.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")) {
						// 한글이 포함된 문자열
							doc = Jsoup.connect("https://ko.wikipedia.org/wiki/"+txtS).get();
							EN_AND_KO=false;
						} else {
						// 한글이 포함되지 않은 문자열
						
							doc = Jsoup.connect("https://wikipedia.org/wiki/"+txtS).get();
							EN_AND_KO=true;
						}
					
					} catch (PatternSyntaxException e) {
						// 정규식에 오류가 있는 경우에 대한 처리
						System.err.println("An Exception Occured");
						e.printStackTrace();
					}
		
				
				Elements links = doc.select("p");
				for (Element link : links) {
					result += link.attr("p");
					result += link.text();
					result = result + "&&";
				}
				parSing(result);
			}
			
		}catch (IOException e) {
			textArea.setText("찾는 단언가 없습니다.");
			e.printStackTrace();
		}

		return result;

	}

	private void parSing(String result) {
		items = new Vector<Object>();

		String str =result.replaceAll("&&",  "\n\n");

		dto =new WikipediaDTO();
		dto.setContent(str);
	}



																																			
/*	public static void main(String[] args) {
		
		WikipediaController weatherController =new WikipediaController(null);
		String str =weatherController.test();
		
		System.out.println(str);
		
	
        
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new WikipediaController(null);
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }*/
	
	
	
}
