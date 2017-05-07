package com.mcaronics.rss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.swt.internal.ole.win32.IEnum;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mcaronics.dto.RssDTO;

public class RssReader {

    private static RssReader instance = null;
    public static String ENCODING = "UTF-8";
    public static String NL = System.getProperty("line.separator");
  
    public RssReader() {
    }
  
    public static RssReader getInstance() {
        if(instance == null) {
            instance = new RssReader();  
        }
        return instance;
    }
  
    
    
    public List<RssDTO> writeNew(String rssUrl) {
    	List<RssDTO> items =new ArrayList<RssDTO>();
    	 URL u =null;
    	 Document doc=null;
    	 NodeList nodes=null;
    	 NodeList nodeChannel=null;
    	 
    	try {

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//           URL u = new URL("http://mutasyon.net/cs/blogs/hakkiocal/rss.aspx");
//            URL u = new URL("http://rss.joins.com/joins_news_list.xml");
//            URL u = new URL("http://feeds.feedburner.com/afriken");
 //           URL u = new URL("http://blog.rss.naver.com/jeffyang.xml");
             u = new URL(rssUrl);
           
            
            
            doc = builder.parse(u.openStream());
          
            nodes = doc.getElementsByTagName("item");
            nodeChannel = doc.getElementsByTagName("channel");
          
            for(int i=0;i<nodes.getLength();i++) {
                
                Element element = (Element)nodes.item(i);
              
                RssDTO rss =new RssDTO();
                rss.setTitle(getElementValue(element,"title"));
                rss.setLink(getElementValue(element,"link"));
                rss.setPubDate(getElementValue(element,"pubDate"));
                rss.setDescription(getElementValue(element,"description"));
                
                items.add(rss);
                
//                System.out.println("Title: " + getElementValue(element,"title"));
//                System.out.println("Category: " + getElementValue(element,"category"));
//                System.out.println("Link: " + getElementValue(element,"link"));
//                System.out.println("Publish Date: " + getElementValue(element,"pubDate"));
//                System.out.println("author: " + getElementValue(element,"author"));
//                System.out.println("comment: " + getElementValue(element,"comments"));
//                System.out.println("guid: " + getElementValue(element,"guid"));
//                System.out.println("language: " + getElementValue(element,"language"));
//                System.out.println("description: " + getElementValue(element,"description"));
//                System.out.println();
            }//for  
            
            
            
            
        }//try
        catch(Exception ex) {
        	ex.printStackTrace();  
        }finally{
        	//if(nodeChannel!=null)u.c
        }
      
        return items;
    }
  
    
    
    
    

    public List<RssDTO> tiStroy(String rssUrl) {
    	List<RssDTO> items =new ArrayList<RssDTO>();
    	 URL u =null;
    	 Document doc=null;
    	 NodeList nodes=null;
    	 NodeList nodeChannel=null;
    	 
    	try {

             DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
             u = new URL(rssUrl);
           
            
            
            doc = builder.parse(u.openStream());
          
            nodes = doc.getElementsByTagName("channel");
            nodeChannel = doc.getElementsByTagName("item");
          
            for(int i=0;i<nodes.getLength();i++) {
                
                Element element = (Element)nodes.item(i);
              
//                RssDTO rss =new RssDTO();
//                rss.setTitle(getElementValue(element,"title"));
//                rss.setLink(getElementValue(element,"link"));
//                rss.setPubDate(getElementValue(element,"pubDate"));
//                rss.setDescription(getElementValue(element,"description"));
//                
//                items.add(rss);
                
                System.out.println("Title: " + getElementValue(element,"title"));
                System.out.println("Category: " + getElementValue(element,"category"));
                System.out.println("Link: " + getElementValue(element,"link"));
                System.out.println("Publish Date: " + getElementValue(element,"pubDate"));
                System.out.println("author: " + getElementValue(element,"author"));
                System.out.println("comment: " + getElementValue(element,"comments"));
                System.out.println("guid: " + getElementValue(element,"guid"));
                System.out.println("language: " + getElementValue(element,"language"));
                System.out.println("description: " + getElementValue(element,"description"));
                System.out.println();
            }//for  
            
            
            
            
        }//try
        catch(Exception ex) {
        	ex.printStackTrace();  
        }finally{
        	//if(nodeChannel!=null)u.c
        }
      
        return items;
    }
    
    
    public List<RssDTO> writeNews(String url) {
       
    	List<RssDTO> items =new ArrayList<RssDTO>();
	
    	try {
          
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            URL u = new URL(url);
          
            Document doc = builder.parse(u.openStream());
          
            NodeList nodes = doc.getElementsByTagName("item");
          
            for(int i=0;i<nodes.getLength();i++) {
              
                Element element = (Element)nodes.item(i);
              
                RssDTO rss =new RssDTO();
                rss.setTitle(getElementValue(element,"title"));
                rss.setCategory(getElementValue(element,"category")+"");
                rss.setLink(getElementValue(element,"link"));
                rss.setAuthor(getElementValue(element,"author")+"");
                rss.setComments(getElementValue(element,"comments")+"");
                rss.setPubDate(getElementValue(element,"pubDate"));
                rss.setDescription(getElementValue(element,"description"));
                
                items.add(rss);
                
//                System.out.println("Title: " + getElementValue(element,"title"));
//                System.out.println("Category: " + getElementValue(element,"category"));
//                System.out.println("Link: " + getElementValue(element,"link"));
//                System.out.println("Publish Date: " + getElementValue(element,"pubDate"));
//                System.out.println("author: " + getElementValue(element,"author"));
//                System.out.println("comment: " + getElementValue(element,"comments"));
//                System.out.println("guid: " + getElementValue(element,"guid"));
//                System.out.println("language: " + getElementValue(element,"language"));
//                System.out.println("description: " + getElementValue(element,"description"));
//                System.out.println();
            }//for  
            
            
            
        }//try
        catch(Exception ex) {
            ex.printStackTrace();  
        }
      
    	return items;
    }
      
      
    private String getCharacterDataFromElement(Element e) {
        try {
            Node child = e.getFirstChild();
            if(child instanceof CharacterData) {
                CharacterData cd = (CharacterData) child;
                return cd.getData();
            }
        }
        catch(Exception ex) {
          
        }
        return "";          
    } //private String getCharacterDataFromElement
  
    protected float getFloat(String value) {
        if(value != null && !value.equals("")) {
            return Float.parseFloat(value);  
        }
        return 0;
    }
  
    protected String getElementValue(Element parent,String label) {
        return getCharacterDataFromElement((Element)parent.getElementsByTagName(label).item(0));  
    }
  
    /**
     * 파일 읽기
     * @param ctx
     * @param fileName
     * @return
     * @throws FileNotFoundException
     */
    public static StringBuffer readFile(String fileName, String fEncoding) throws FileNotFoundException {
        StringBuffer result = new StringBuffer();
//        Scanner scanner = new Scanner(new File(fileName), fEncoding);
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            try {
                String line = null;
              
                while ((line=in.readLine()) != null) {
                    result.append(line+NL);
                }
            } finally {
                in.close();
            }
//            while (scanner.hasNextLine()) {
//                result.append(scanner.nextLine()+NL);
//            }
        }catch(IOException ex ) {
            ex.printStackTrace();
        }
      
        return result;
    }
  
    public static StringBuffer readFileScanner(String fileName, String fEncoding) throws FileNotFoundException {
        StringBuffer result = new StringBuffer();
        Scanner scanner = new Scanner(new File(fileName), fEncoding);
        try {
            while (scanner.hasNextLine()) {
                result.append(scanner.nextLine()+NL);
            }
        } finally {
                scanner.close();
        }
      
        return result;
    }
  
  /*  public static void main(String[] args) {
        try {
//            StringBuffer strUrl = readFile(args[0],  ENCODING);
        	
       	String rssTxt ="./data/rss.txt";
            StringBuffer strUrl = readFileScanner(rssTxt,  ENCODING);
            if(strUrl != null ){
                String[] urls = strUrl.toString().split(NL);
                for(int i=0; i < urls.length;i++) {
                    RssReader reader = RssReader.getInstance();
                    reader.writeNews(urls[i]);  
                }
            }

     //       RssReader reader = RssReader.getInstance();
       //     reader.writeNews();  
                      
        } catch (Exception e) {
            // TODO Auto-generated catch block
//            e.printStackTrace();
            System.out.println("지정된 파일을 찾을 수 없습니다. 파일을 확인하시기 바랍니다.");
        }

    }*/
}