package jsoup;
import java.util.Scanner;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;


public class Naver_Search {
	public static void main(String[] args) throws IOException{
		String word = null;
		Scanner wordReader = new Scanner(System.in);
		System.out.print("지역을 입력하세요 : ");
		word = wordReader.nextLine();
		
		Document doc = Jsoup.connect("https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query="+word+"+맛집").get();
		Elements titles = doc.select("#sp_local_1 .info_area a");
		String title = titles.attr("href");
		Elements locations = doc.select("#sp_local_1 .ad_txt:eq(1)");
		String location = locations.attr("title");
		
		System.out.print("식당이름 : ");
		System.out.println(title);
		System.out.print("식당위치 : ");
		System.out.println(location);
		//System.out.print("text_array의 데이터타입 : ");
		//System.out.println(text_array.getClass().getName());
		//System.out.print("text_array의 길이 : ");
		//System.out.println(text_array.length);
		
	}

}
