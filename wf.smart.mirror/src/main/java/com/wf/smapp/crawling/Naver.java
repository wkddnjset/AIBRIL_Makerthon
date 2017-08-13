package com.wf.smapp.crawling;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Naver {

	
	public static String getWeatcher(String word){
		Document doc = null;
		try {
			doc = Jsoup.connect("https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query="+word+"+����").get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Elements titles = doc.select(".c_body .w_now2 .fl em");
		String title = titles.text();
		String title_1 = title.split("��")[0];
		String title_2 = title.split("��")[1];

		String weather = new String("���� "+word+"�� �µ���"+title_1+"��"+"��, ������ "+title_2+ "�ε�, �� �Ϸ����°ž�?");

		System.out.println(weather);
		return weather;
	}
	
	public static String getFavoiteFood(String word){
		String food;
		
		if (word.equals("�Ͻ�")){
			food = new String("�ٺ񷹵�,�����ֹ�,���̿���,ŷ�� ������ũ,����ũ�� ����");
		}else if(word.equals("���")){
			food = new String("������,�䳢�� ����");
		}else{
			food = new String("����");
		}
		
		
		return food;
	}

}

