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


	static WikipediaController frame;
	
	
	private JPanel contentPane;

	Vector<Object> items;
	private JTable agTable;
	
	private JButton button;
	private JButton btnNewButton_1;

	private MacaronicsMain main;

	private JScrollPane scrollPane;
	private JTextArea textArea;
	
	private WikipediaDTO dto;
	private JTextField txtSearch;
	private String txtS;
	
	
	public WikipediaController(Object main) {
		this.main = (MacaronicsMain) main;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		contentPane.setBackground(new Color(51, 51, 51));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(142, 72, 864, 625);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.getScrollableTracksViewportWidth();
		textArea.setFont(new Font("휴먼모음T", Font.PLAIN, 20));
		textArea.setLineWrap(true);        //활성화, 자동 줄 바꿈 기능 
		textArea.setWrapStyleWord(true);            // 끊임없이 글을 기능 활성화 단행하다
		
		scrollPane.setViewportView(textArea);

		// 정보 가져오기
		tableShow();
		

		button = new JButton("농업 기상 관측");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		button.setBounds(12, 145, 118, 23);
		contentPane.add(button);

		JLabel lblNewLabel = new JLabel("위키백과 요약 검색");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 32));
		lblNewLabel.setBounds(528, 14, 298, 52);
		contentPane.add(lblNewLabel);

		btnNewButton_1 = new JButton("Home");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MacaronicsMain main = new MacaronicsMain(WikipediaController.this);
				main.setVisible(true);
				WikipediaController.this.dispose();

			}
		});
		btnNewButton_1.setBounds(12, 81, 118, 23);
		contentPane.add(btnNewButton_1);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(142, 33, 200, 29);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnSearch = new JButton("검색");
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
		btnSearch.setBounds(367, 32, 103, 30);
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
				System.out.println(" 검색 단어 없음");
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

		String str =result.replaceAll("&&",  "\n");

		dto =new WikipediaDTO();
		dto.setContent(str);
	}



																																			
	public static void main(String[] args) {
		
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
    }
}
