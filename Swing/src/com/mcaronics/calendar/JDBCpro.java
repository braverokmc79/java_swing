package com.mcaronics.calendar;


/*
 * Swing 과 JDBC를 이용하여 
 * 달력을 만들고 달력에 메모장 기능을 추가하라.
 * 메모는 추가 삭제가 가능하고 메모가 있는날은
 * 달력에 표시가 되어야 한다. 
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mcaronics.dto.CalendarDTO;
 
public class JDBCpro extends JPanel implements ActionListener
{
       String [] days = {"일","월","화","수","목","금","토"};
       int year ,month,todays,memoday=0;
       
       Integer day;
       
       Font f;
      
       Calendar today;
       Calendar cal;
  
       JButton btnBefore2,btnAfter2; //befor2 작년 // after2 내년
       JButton btnBefore,btnAfter;
       JButton btnAdd,btnDel;
       JButton[] calBtn = new JButton[49];
 
       JLabel time;
 
       JPanel panSouth;
       JPanel panNorth;
       JPanel panCenter;
       
       JTextField txtMonth,txtYear,txtWrite;
       // 글자를 넣을수있는 텍스트 필드 년 월 메모부분
       BorderLayout bLayout= new BorderLayout();     
     
  
       File file=null;
       /*
       * 디 비 만 들 기 
       *create database java;
       *use java;
       *create table java(
       *memo text,
       *year varchar(10),
       *month varchar(10),
       *day varchar(10)
       *);
       */
       String sql;
       
       public JDBCpro(){
    	   
    	    file=new File("./data/calendar.dat");
    	   
             today = Calendar.getInstance(); //디폴트의 타임 존 및 로케일을 사용해 달력을 가져옵니다.
             cal = new GregorianCalendar();
             /*
              * GregorianCalendar 는,Calendar 의 구상 서브 클래스이며,
              * 세계의 대부분의 지역에서 사용되는 표준적인 달력 시스템을 제공합니다. 
              */
             year = today.get(Calendar.YEAR);
             month = today.get(Calendar.MONTH)+1;//1월의 값이 0 
 
             panNorth = new JPanel();
             btnBefore2 = new JButton("↓");
             btnBefore2.setBackground(new Color(166, 188, 13));
             panNorth.add(btnBefore2);  
             
             btnBefore = new JButton("←");
             btnBefore.setBackground(new Color(255, 127, 0));
             panNorth.add(btnBefore);            
             
             panNorth.add(txtYear = new JTextField(year+"년"));
             panNorth.add(txtMonth = new JTextField( month+"월"));
           
             f=new Font("Sherif",Font.BOLD,18); //년가 월을 표시하는 텍스트 필드의 글자 속성
             txtYear.setFont(f);
             txtMonth.setFont(f);
             txtMonth.setBackground(new Color(111, 143, 234));
             txtYear.setBackground(new Color(111, 143, 234));
             
             txtYear.setEnabled(false); // 년과 월을 선택 비활성화하여 숫자 수정을 불가피한다.
             txtMonth.setEnabled(false);
             
             btnAfter = new JButton("→ ");
             btnAfter.setBackground(new Color(255, 127, 0));		 
             panNorth.add(btnAfter);
             
             btnAfter2 = new JButton("↑ ");
             btnAfter2.setBackground(new Color(166, 188, 13) );
             panNorth.add(btnAfter2);
            
             
             panNorth.setBounds(74, 28, 540, 109);
             add(panNorth);

         	      	
    		
             
             
             /*
             * jdbcpro라는 큰놈 위에 레이아웃을 동,서,남,북으로 나눠서
             * 패널을 하나 하나 올려 놓는 형식이다.
             * 메인보드 위에 부품이 하나 하나 조립되듯..... 
             */

             //이놈은 달력에 날에 해당하는 부분

             panCenter = new JPanel(new GridLayout(7,7));//격자나,눈금형태의 배치관리자
             f=new Font("Sherif",Font.BOLD,12);
            
             gridInit();
             calSet();
             hideInit();
             
         	
            JPanel panel0 = new JPanel(new BorderLayout());
            panel0.setBounds(111, 102, 481, 372);
     		JPanel panel_1 = new JPanel();
     		JButton imsiButton=new JButton("스케쥴러");
     		imsiButton.setBackground(new Color(233, 233, 233));
     		panel_1.add(imsiButton);
     		panel_1.setBounds(120, 81, 472, 29);
     		
     		panel0.add(panel_1, "North");
     		panel0.add(panCenter, "Center"); 		
         	
         //	panCenter.setBounds(111, 102, 481, 372);
         	
         	
    		add(panel0);

             
       /*      JPanel paneWest = new JPanel(new GridLayout(7,7));
             JPanel paneEast = new JPanel(new GridLayout(7,7));
             
             add(paneWest, "West");
             add(paneWest, "East");*/
             
             
             panSouth = new JPanel(); 
             panSouth.add(btnAdd = new JButton("메모추가 및 수정"));
             panSouth.add(btnDel = new JButton("메모삭제"));
             panSouth.add(txtWrite = new JTextField());
             
             txtWrite.setPreferredSize(new Dimension(430,100));
             //메모를 입력받을 텍스트 박스를 가로 600 세로 110 생성
             
             
     		
     		panSouth.setBounds(131, 487, 483, 108);
    		     
             add(panSouth);

             //버튼에 대한 행동들을 정의한다.
             btnBefore.addActionListener(this);
             btnAfter.addActionListener(this);
             btnBefore2.addActionListener(this);
             btnAfter2.addActionListener(this);
             btnAdd.addActionListener(this);	//메모추가
             btnDel.addActionListener(this);	//메모삭제
 

        //     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //닫는기능
        //     setTitle("스케쥴러");
             setBounds(200,200,450,400);	//(x,y,가로,세로) 프레임창의 위치
          //   setVisible(true);   
       

       }//end constuctor

       
       public void calSet(){

             cal.set(Calendar.YEAR,year);
             cal.set(Calendar.MONTH,(month-1));
             cal.set(Calendar.DATE,1);
             int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

             /*
              * get 및 set 를 위한 필드치로, 요일을 나타냅니다.
              * 이 필드의 값은,SUNDAY,MONDAY,TUESDAY,WEDNESDAY
              * ,THURSDAY,FRIDAY, 및 SATURDAY 가 됩니다. 
              * get()메소드의 의해 요일이 숫자로 반환
              */

             int j=0;
             int hopping=0;
             calBtn[0].setForeground(new Color(255,0,0));//일요일 "일"  RGB의 색 넣는다.
             calBtn[6].setForeground(new Color(0,0,255));//토요일 "토"

             for(int i=cal.getFirstDayOfWeek();i<dayOfWeek;i++){  j++;  }
             /*
              * 일요일부터 그달의 첫시작 요일까지 빈칸으로 셋팅하기 위해 
              */
             hopping=j;

             for(int kk=0;kk<hopping;kk++){
                    calBtn[kk+7].setText("");
             }
             for(int i=cal.getMinimum(Calendar.DAY_OF_MONTH);
                           i<=cal.getMaximum(Calendar.DAY_OF_MONTH);i++){
                 cal.set(Calendar.DATE,i);
                    if(cal.get(Calendar.MONTH) !=month-1){
                           break;
                    }
                    todays=i;
                    checkday();
                    if(memoday==1){	//memo가 저장된날은  짙은핑크색으로
                           calBtn[i+6+hopping].setForeground(new Color(255,0,255));                         
                    }
                    else{
                           calBtn[i+6+hopping].setForeground(new Color(0,0,0));
                           if((i+hopping-1)%7==0){//일요일
                                 calBtn[i+6+hopping].setForeground(new Color(255,0,0));
                           }
                           if((i+hopping)%7==0){//토요일
                                 calBtn[i+6+hopping].setForeground(new Color(0,0,255));
                           }
                    }

                    /*
                     * 요일을 찍은 다음부터 계산해야 하니 요일을 찍은 버튼의 갯수를 더하고
                     * 인덱스가 0부터 시작이니 -1을 해준 값으로 연산을 해주고
                     * 버튼의 색깔을 변경해준다. 
                     */
                    calBtn[i+6+hopping].setText((i)+"");
             }//for
 
       }//end Calset()
       
       public void actionPerformed(ActionEvent cook){   //액션 누르는걸cook 눌렀을때
    	   
             if(cook.getSource() == btnBefore){	// 이전달로 가기위한 소스부
                    this.panCenter.removeAll();
                    calInput(-1);				//달을 하나 빼준다
                    gridInit();
                    panelInit();               
                    calSet();
                    hideInit();
                    this.txtYear.setText(year+"년");
                    this.txtMonth.setText(month+"월");                   
             }                   
             else if(cook.getSource() == btnAfter){	// 다음 달로 가기위한 소스부
                    this.panCenter.removeAll();
                    calInput(1);				//달을 하나 더해준다.
                    gridInit();
                    panelInit();
                    calSet();
                    hideInit();
                    this.txtYear.setText(year+"년");
                    this.txtMonth.setText(month+"월");                                       
             }
             else if(cook.getSource() == btnBefore2){	// 전년 으로 가기위한 소스부
                 this.panCenter.removeAll();
                 calInput(-12);				//12개월을 빼준다.
                 gridInit();
                 panelInit();
                 calSet();
                 hideInit();
                 this.txtYear.setText(year+"년");
                 this.txtMonth.setText(month+"월");                                       
          }
             else if(cook.getSource() == btnAfter2){	// 내년으 로 가기위한 소스부
                 this.panCenter.removeAll();
                 calInput(12);				//12개월을  더해준다.
                 gridInit();
                 panelInit();
                 calSet();
                 hideInit();
                 this.txtYear.setText(year+"년");
                 this.txtMonth.setText(month+"월");                                       
          }
             else if(cook.getSource() == btnAdd){
                    //dbConnect();
                    add();
                    calSet();
                    txtWrite.setText("");
                    
             }
             else if(cook.getSource() == btnDel){
                   // dbConnect();
                    del();
                    calSet();
                    txtWrite.setText("");
             }
 
             else if(Integer.parseInt(cook.getActionCommand()) >= 1 && 
                    Integer.parseInt(cook.getActionCommand()) <=31){
                    day = Integer.parseInt(cook.getActionCommand());
                    //버튼의 밸류 즉 1,2,3.... 문자를 정수형으로 변환하여 클릭한 날짜를 바꿔준다.
                    System.out.println(day);
                   // dbConnect();               
                    searchmemo();
                    calSet();
             }      
       }//end actionperformed()

       public void hideInit(){
             for(int i = 0 ; i < calBtn.length;i++){
                    if((calBtn[i].getText()).equals(""))
                    	
                         calBtn[i].setEnabled(false);
                    //일이 찍히지 않은 나머지 버튼을 비활성화 시킨다. 
             }//end for
       }//end hideInit()
//     public void separate(){
 
       public void gridInit(){
         //jPanel3에 버튼 붙이기

    	   
         for(int i = 0 ; i < days.length;i++)       	    
               panCenter.add(calBtn[i] = new JButton(days[i]));                   
               
              
           for(int i = days.length ; i < 49;i++){                
                    panCenter.add(calBtn[i] = new JButton(""));                   
                    calBtn[i].addActionListener(this);
                    calBtn[i].setBackground(new Color(255, 255, 255));
             }              
       }//end gridInit()
       
       public void panelInit(){
         GridLayout gridLayout1 = new GridLayout(7, 7);
         panCenter.setLayout(gridLayout1);   
       }//end panelInit()
       public void calInput(int gap){
                         
              if (gap==-1 || gap ==1)
              {
            	  month+=(gap);
            	  if (month<=0)
            	  {
                      month = 12;
                      year  =year- 1;
            	  }
            	  else if (month>=13)
            	  {
                      month = 1;
                      year =year+ 1;
            	  }
              }
              else if(gap == 12){	year++;	}
              else if(gap == -12){	year--;	}
         
              
       }//end calInput()


       
       public void add(){
    	   BufferedWriter bw=null;
             try{
                   if(day==null){
                	   JOptionPane.showMessageDialog(this, "메모할 날짜를 선택해 주세요!");
                	   return;
                   }
            	   String temp = txtWrite.getText();
                    if(temp.equals("")){
                           JOptionPane.showMessageDialog(null,"내용이 없습니다.");
                           return;
                    } 
                    File file=new File("./data/calendar.dat");
                    bw=new BufferedWriter(new FileWriter(file , true));
                   
                    String str ="Ⅷ"+year+new DecimalFormat("00").format(month)+new DecimalFormat("00").format(day)+temp;
                    
                    bw.write(str);
                    
                    JOptionPane.showMessageDialog(this, "메모가 추가 되었습니다.");
             }catch(Exception e){
                    e.printStackTrace();
             }finally {
            	 
            	 try {
            		 if(bw!=null)	bw.close();
					} catch (IOException e) {	
						e.printStackTrace();
					}	
			}
       }//end add()
       
       public void del(){
    	   
    	    BufferedReader br =null;
    	    BufferedWriter bw=null;
             try{
            	 
            	 if(day==null){
            		 JOptionPane.showMessageDialog(this, "삭제할 날짜를 선택해 주세요!");
            		 return;
            	 }
            	 
            	 br=new BufferedReader(new FileReader(file));
                  	 
            	 StringBuffer buffer =new StringBuffer();
            	 String str;
            	
            	 while((str=br.readLine())!=null){
            		 buffer.append(str);
            	 }
         	 	String memo="";
         	 	String confirm=""+year+ new DecimalFormat("00").format(month)+new DecimalFormat("00").format(day);
         	 	
         	 	if(buffer.toString().contains(confirm)){
         	 		Map<String, String> map=parsing(buffer.toString());
         	 		Iterator<String> iterator=map.keySet().iterator();
         	 		
         	 		int cf=JOptionPane.showConfirmDialog(this,"정말 삭제하시겠습니까?");
         	 		System.out.println(cf);
         	 		if(cf==0){
              	 	    StringBuffer deleteBuffer=new StringBuffer();
             	 		//데이터가 존재하면
             	 		while(iterator.hasNext()){
             	 			String key=(String)iterator.next();
             	 			if(!key.equals(confirm)){
             	 				deleteBuffer.append("Ⅷ"+key+map.get(key));
             	 				System.out.println(deleteBuffer.toString());
             	 			}
             	 		}
             	 		
             	 		 bw=new BufferedWriter(new FileWriter(file));
             	 		 bw.write(deleteBuffer.toString());
         	 			 bw.flush();
         	 		}
         	 		         	 		
         	 	}else {
         	 		JOptionPane.showMessageDialog(this, "삭제할 데이터가 없습니다.");
         	 	}
         
             }
             catch(Exception e){
                  e.printStackTrace();
             }finally{
            	 try{
            		 if(br!=null)br.close();
            		 if(bw!=null)br.close();
            	 }catch (Exception e) {
					e.printStackTrace();
				}
             }
       }//end del();
       
       public void searchmemo(){
    	   	
    	   	BufferedReader br =null;   
             try{
            	 	
            	 	br =new BufferedReader(new FileReader(file));
            	 	
            	 	String str;
            	 	StringBuilder builder =new StringBuilder();
            	 	while((str=br.readLine())!=null){
            	 		builder.append(str);
            	 	}
            	 	
            	 //	System.out.println(" bulider " + builder.toString());
            	 	String memo="";
            	 	String confirm=""+year+ new DecimalFormat("00").format(month)+new DecimalFormat("00").format(day);
            	 	
            	 //	System.out.println( " confirm "+ " : " +confirm);
            	 	
            	 	if(builder.toString().contains("Ⅷ"+confirm)){
            	 		
            	 		//System.out.println(" 검색 된 데이터가 존재 ");
            	 		
                	 	Map<String, String> map=parsing(builder.toString());
                	 	Iterator<String> iterator =map.keySet().iterator();
                	 	while(iterator.hasNext()){
                	 		String key=(String)iterator.next();
                	 	//	System.out.println("key : " + key);
                	 		//System.out.println("value : " + map.get(key));
                	 		
                	 		if(key.equals(confirm)){
                	 			memo+=map.get(key)+"\n";		
                	 		}
                	 	}
                	 	
            	 	}
            	 	
            	 	txtWrite.setText(memo);
             }catch(Exception e){
                    e.printStackTrace();
             }finally {
           	  try{
           		if(br!=null) br.close();  
           	  }catch (Exception e) {

           	  }
             }
             
             
       }//end searchmemo()

       public void checkday(){
  	        BufferedReader br =null;
              try{
            	  
            	  br=new BufferedReader(new FileReader(file));
            	  String str;
            	  StringBuffer buffer=new StringBuffer();
            	  while((str=br.readLine())!=null){
            		  buffer.append(str);
            		  
            	  }
            	  String confirm=""+year+ new DecimalFormat("00").format(month)+new DecimalFormat("00").format(todays);
            	  if(buffer.toString().contains("Ⅷ"+confirm)){
                            memoday=1;
                  }else memoday=0;
                  
              }catch(Exception e){
                     e.printStackTrace();
              }finally {
            	  try{
            		if(br!=null) br.close();  
            	  }catch (Exception e) {

            	  }
              }
              
 }//end checkday()

       
       public Map<String, String> parsing(String str){
    	   Map<String, String> map =new HashMap<String, String>();
    	   
    	   StringTokenizer st =new StringTokenizer(str, "Ⅷ"); 
    	   int countToken =st.countTokens();
    	   
    	   for(int i=0; i< countToken; i++){
    		   String token=st.nextToken();
    		  // System.out.println(" 토큰 : " + token);
    		  	String first =token.substring(0, 8);
    		  	String memo=token.split(first)[1];
    		  	//System.out.println(first + " : " + memo);
    		  	map.put(first, memo);
    	   }
    	   return map;
       }
       
       
 /*      public static void main(String[] args){
             JDBCpro jdbc = new JDBCpro();
       
       }*/
}
