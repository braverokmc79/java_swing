package com.mcaronics.controller;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mcaronics.MacaronicsMain;
import com.mcaronics.dto.WikipediaDTO;
import javax.swing.JTextField;

public class WikipediaController extends JFrame {

	private static WikipediaController wikipediaController ;

	public static WikipediaController getInstance(){
		if(wikipediaController==null){
			wikipediaController=new WikipediaController();
		}
		return wikipediaController;
	}

	static WikipediaController frame;
	
	
	private JPanel contentPane;

	Vector<Object> items;
	private JTable agTable;

	//private MacaronicsMain main;

	private JScrollPane scrollPane;
	private JTextArea textArea;
	
	private WikipediaDTO dto;
	private JTextField txtSearch;
	private String txtS;
	
	
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
		textArea.getScrollableTracksViewportWidth();
		textArea.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		textArea.setLineWrap(true);        //활성화, 자동 줄 바꿈 기능 
		textArea.setWrapStyleWord(true);            // 끊임없이 글을 기능 활성화 단행하다
		
		scrollPane.setViewportView(textArea);

		// 정보 가져오기
		tableShow();

		
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
				}else{
					
					JOptionPane.showMessageDialog(WikipediaController.this, "검색할 단어를 입력 해주세요");
					txtSearch.setFocusable(true);
				}
				
			}
		});
		btnSearch.setBounds(435, 21, 103, 45);
		contentPane.add(btnSearch);
		
		
	}

	
	public void tableShow(){
		// 정보 가져오기
		test();
		
		String txt=textArea.getText();
		if(txt!=null && txt.trim().length() >0){
		
			Vector<Object> ii=new Vector<>();
			Vector<Object> row=new Vector<>();
			row.add(txt);
			ii.add(row);
			
			
			
			Vector<Object> col = new Vector<>();
			if(txtSearch.getText()!=null && txtSearch.getText().length() > 0){
				col.add(txtSearch.getText());
				
			}else{
				col.add("Blank");
			}
					
			DefaultTableModel model=new DefaultTableModel(ii, col);
			agTable = new JTable(0,1);
			agTable.setRowHeight(700);
			agTable.setAutoResizeMode(JTable.ABORT);
			agTable.setModel(model);	
		}	
	}
	
	
	public String test() {
		Document doc;

		String result = "";

		try {
			
			if(txtS==null){
				//doc = Jsoup.connect("https://ko.wikipedia.org/").get();
				//System.out.println(" 검색 단어 없음");
			}else{
				System.out.println(" 검색 ");
				doc = Jsoup.connect("https://ko.wikipedia.org/wiki/"+txtS).get();
				
				Elements links = doc.select("p");
				for (Element link : links) {
					result += link.attr("p");
					result += link.text();
					result = result + "&&";
				}
				parSing(result);
			}
			
			
			// doc = Jsoup.connect("https://ko.wikipedia.org/wiki/송승헌").get();
		//	result = doc.title() + "\n\n";
			

		}catch (IOException e) {
			e.printStackTrace();
		}

		return result;

	}

	private void parSing(String result) {
		items = new Vector<>();

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
