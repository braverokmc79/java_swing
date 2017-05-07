package com.mcaronics.common;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebScroller {

	
	
	public static String test() {
		Document doc;

		String result = "";

		try {
			doc = Jsoup.connect("http://www.hrd.go.kr/hrdp/ma/pmmao/main1.do?upperNcsCd=20").get();
			// doc = Jsoup.connect("https://ko.wikipedia.org/wiki/송승헌").get();
			result = doc.title() + "\n\n";

			Elements links = doc.select("li");

			for (Element link : links) {

				result += link.attr("li");

				result += link.text();

				result = result + "&&";
			}

			//parSing(result);
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		return result;

	}
	
	
	public static void main(String[] args) {
		
		System.out.println(test());
	}
	
	
	
	
	
	
	
	
}
