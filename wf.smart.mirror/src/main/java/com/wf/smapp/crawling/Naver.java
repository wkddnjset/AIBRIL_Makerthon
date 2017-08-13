package com.wf.smapp.crawling;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Naver {

	
	public static String getWeatcher(String word){
		Document doc = null;
		try {
			doc = Jsoup.connect("https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query="+word+"+날씨").get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Elements titles = doc.select(".c_body .w_now2 .fl em");
		String title = titles.text();
		String title_1 = title.split("℃")[0];
		String title_2 = title.split("℃")[1];

		String weather = new String("현재 "+word+"의 온도는"+title_1+"℃"+"고, 날씨는 "+title_2+ "인데, 뭐 하러가는거야?");

		System.out.println(weather);
		return weather;
	}
	
	public static String getFavoiteFood(String word){
		String food;
		
		if (word.equals("일식")){
			food = new String("바비레드,마녀주방,에이와이,킹콩 스테이크,쉐이크쉑 강남");
		}else if(word.equals("양식")){
			food = new String("마코토,토끼정 강남");
		}else{
			food = new String("없음");
		}
		
		
		return food;
	}

}

