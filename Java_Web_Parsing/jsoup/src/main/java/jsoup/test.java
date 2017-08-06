package jsoup;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;

// 참고 - http://blog.acronym.co.kr/337
// select 명령어 - https://jsoup.org/apidocs/org/jsoup/select/Selector.html
// https://jsoup.org/
public class test {
	public static void main(String[] args) throws IOException{
		Document doc = Jsoup.connect("http://www.naver.com").get();
		Elements titles = doc.select(".ah_item a span.ah_k");
		// List 출력방법
		System.out.print("titles의 첫 번째 요소 : ");
		System.out.println(titles.get(0));
		System.out.print("titles의 길이 : ");
		System.out.println(titles.size());
		System.out.print("titles의 데이터타입 : ");
		System.out.println(titles.getClass().getName());
		/* Elements는 List형식임..
		 * 이걸 각 요소마다 text로 바꿔서 배열 text_array에 저장
		 */
		String[] text_array = new String[titles.size()];
		for(int i=0; i<titles.size(); i++) {
			String text = titles.get(i).text();
			text_array[i] = text;
		}
		
		// 배열로 잘 저장되었는지 확인
		System.out.println("text_array 전부 출력 : ");
		for (int i=0; i<titles.size(); i++) {
			System.out.println(text_array[i]);
		}
		// List 출력방법
		System.out.print("text_array의 데이터타입 : ");
		System.out.println(text_array.getClass().getName());
		System.out.print("text_array의 길이 : ");
		System.out.println(text_array.length);
		
	}
}
// OUTPUT
/* 
		titles의 첫 번째 요소 : <span class="ah_k">입추</span>
		titles의 길이 : 40
		titles의 데이터타입 : org.jsoup.select.Elements
		text_array 전부 출력 : 
		입추
		아가씨
		첼시 아스날
		대구날씨
		구해줘
		말복
		신의탑
		오버워치 월드컵
		런닝맨
		블랙핑크
		아이폰8
		부산날씨
		효리네 민박
		사이버경찰청 원서접수
		김희선
		복면가왕
		서울날씨
		밤도깨비
		최준희
		비긴어게인
		입추
		아가씨
		첼시 아스날
		대구날씨
		구해줘
		말복
		신의탑
		오버워치 월드컵
		런닝맨
		블랙핑크
		아이폰8
		부산날씨
		효리네 민박
		사이버경찰청 원서접수
		김희선
		복면가왕
		서울날씨
		밤도깨비
		최준희
		비긴어게인
		text_array의 데이터타입 : [Ljava.lang.String;
		text_array의 길이 : 40
 */