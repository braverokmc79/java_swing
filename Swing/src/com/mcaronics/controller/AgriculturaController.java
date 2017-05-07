package com.mcaronics.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mcaronics.MacaronicsMain;
import com.mcaronics.common.PDFWebViewer;
import com.mcaronics.common.PrintComponent;

public class AgriculturaController extends JFrame {

	
	private static AgriculturaController agriculturaController;

	public static AgriculturaController getInstance(){
		if(agriculturaController==null){
			agriculturaController=new AgriculturaController();
		}
		return agriculturaController;
	}
	
	
	private JPanel contentPane;

	Vector<Object> items;
	private JTable agTable;
	private JButton button;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

	MacaronicsMain main;
	private JButton btnNewButton_2;

	JScrollPane scrollPane;
	
	private AgriculturaController() {
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 51, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(179, 72, 827, 625);
		contentPane.add(scrollPane);

		// 정보 가져오기
		tableShow();
		
		//좌측 메뉴	
		MacaronicsMain.leftMenu(contentPane, AgriculturaController.this, AgriculturaController.this);
			
		
	

		JLabel lblNewLabel = new JLabel("농업기상정보 서비스");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("한컴 윤고딕 230", Font.PLAIN, 30));
		lblNewLabel.setBounds(467, 10, 266, 52);
		contentPane.add(lblNewLabel);

		btnNewButton = new JButton("수원서둔 관측");
		btnNewButton.setBackground(new Color(247, 186, 0));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.HOUR_OF_DAY, -2);

				String year = String.valueOf(cal.get(Calendar.YEAR));
				String month = String.valueOf(new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1));
				String day = String.valueOf(new DecimalFormat("00").format(cal.get(Calendar.DATE)));
				String hh = String.valueOf(new DecimalFormat("00").format(cal.get(Calendar.HOUR_OF_DAY)));
				String ydmh = year + month + day + hh;

				new PDFWebViewer().openURL(
						"http://weather.rda.go.kr/weather/observationGraph.jsp?time=" + ydmh + "&stncode=441707D001");
			}
		});
		btnNewButton.setBounds(221, 21, 118, 45);
		contentPane.add(btnNewButton);
		
		
		btnNewButton_2 = new JButton("프린트");
		btnNewButton_2.setBackground(new Color(247, 186, 0));
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//프린트 미리보기
				PrintSample print=new PrintSample(agTable);
				//print.setTable(agTable);
				print.onPrintPreview();
				//print.setVisible(true);
				
				// 정보 가져오기
				tableShow();
				
				
			}
		});
		btnNewButton_2.setBounds(857, 21, 97, 45);
		contentPane.add(btnNewButton_2);
		
		
	}

	
	public void tableShow(){
		// 정보 가져오기
		test();
		Vector<Object> col = new Vector<>();
		col.add("번호");
		col.add("지점명");
		col.add("기온");
		col.add("습도");
		col.add("풍향");
		col.add("평균풍속");
		col.add("순간 최대풍속");
		col.add("강수량");
		col.add("일조 시간");
		col.add("토양수분 현재상태");
		col.add("토양수분 작년");

		DefaultTableModel model = new DefaultTableModel(items, col);
		agTable = new JTable();
		// agTable.setBackground(Color.LIGHT_GRAY);

		agTable.setModel(model);

		scrollPane.setViewportView(agTable);
		
	}
	
	
	public String test() {
		Document doc;

		String result = "";

		try {
			doc = Jsoup.connect("http://weather.rda.go.kr/weather/observation.jsp").get();
			// doc = Jsoup.connect("https://ko.wikipedia.org/wiki/송승헌").get();
			result = doc.title() + "\n\n";

			Elements links = doc.select("td");

			for (Element link : links) {

				result += link.attr("td");

				result += link.text();

				result = result + "&&";
			}

			parSing(result);
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		return result;

	}

	private void parSing(String result) {
		items = new Vector<>();

		String data1 = result.replace("농업기상관측 - 농업기상(농진청)", ""); // 제목 제거
		String data2 = data1.replace("\n\n", ""); // 엔터 2개 제거

		StringTokenizer st = new StringTokenizer(data2, "&&");
		int countTokens = st.countTokens();

		int numIndex = 0;
		Vector<Object> row = null;

		for (int i = 0; i < countTokens; i++) {

			String token = st.nextToken();
			if (token == null) {
				token = "";
			}

			switch (i % 10) {
			case 0:
				row = new Vector<Object>();
				row.add(++numIndex);
				row.add(token); // 지점명

				break;

			case 1:
				row.add(token);// 기온
				break;
			case 2:
				row.add(token); // 습도
				break;
			case 3:
				row.add(token);// 풍향
				break;
			case 4:
				row.add(token);// 평균풍속
				break;
			case 5:
				row.add(token); // 순간 최대풍속
				break;
			case 6:
				row.add(token);// 강수량
				break;
			case 7:
				row.add(token);// 일조 시간
				break;

			case 8:
				row.add(token);// 토양수분 현재 상태
				break;

			case 9:
				row.add(token);// 토양수분 작년
				items.add(row);
				break;
			}

		}
		
		
	}


	
																																			// .
																																			// .
																																			// .
																																			// }

		
}
