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
      System.out.print("������ �Է��ϼ��� : ");//�̰� ��µ�
      wordReader = new Scanner(System.in);
      word = wordReader.nextLine();//�Ʊ� ������ �� word�� ��
      Document doc = Jsoup.connect("https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query="+word+"+����").get();
      Elements titles = doc.select(".c_body .w_now2 .fl em");
      // .w_now2 .fl
      String title = titles.text();
      String title_1 = title.split("��")[0];
      String title_2 = title.split("��")[1];
      System.out.println("���� "+word+"�� �µ���"+title_1+"��"+"��, ������ "+title_2+ "�̶����, �� �Ϸ����°ž�?");

	}
}
