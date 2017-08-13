package jsoup;

import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Forecast {
private static Scanner wordReader;

public static void main(String[] args) throws IOException{
      String word = null;
      System.out.print("지역을 입력하세요 : ");//이게 출력되
      wordReader = new Scanner(System.in);
      word = wordReader.nextLine();//아까 들어오는 값 word에 저
      Document doc = Jsoup.connect("https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query="+word+"+날씨").get();
      Elements titles = doc.select(".c_body .w_now2 .fl em");
      // .w_now2 .fl
      String title = titles.text();
      String title_1 = title.split("℃")[0];
      String title_2 = title.split("℃")[1];
      System.out.println("현재 "+word+"의 온도는"+title_1+"℃"+"고, 날씨는 "+title_2+ "이라던데, 뭐 하러가는거야?");

	}
}
