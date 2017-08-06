package jsoup;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;

// ���� - http://blog.acronym.co.kr/337
// select ��ɾ� - https://jsoup.org/apidocs/org/jsoup/select/Selector.html
// https://jsoup.org/
public class test {
	public static void main(String[] args) throws IOException{
		Document doc = Jsoup.connect("http://www.naver.com").get();
		Elements titles = doc.select(".ah_item a span.ah_k");
		// List ��¹��
		System.out.print("titles�� ù ��° ��� : ");
		System.out.println(titles.get(0));
		System.out.print("titles�� ���� : ");
		System.out.println(titles.size());
		System.out.print("titles�� ������Ÿ�� : ");
		System.out.println(titles.getClass().getName());
		/* Elements�� List������..
		 * �̰� �� ��Ҹ��� text�� �ٲ㼭 �迭 text_array�� ����
		 */
		String[] text_array = new String[titles.size()];
		for(int i=0; i<titles.size(); i++) {
			String text = titles.get(i).text();
			text_array[i] = text;
		}
		
		// �迭�� �� ����Ǿ����� Ȯ��
		System.out.println("text_array ���� ��� : ");
		for (int i=0; i<titles.size(); i++) {
			System.out.println(text_array[i]);
		}
		// List ��¹��
		System.out.print("text_array�� ������Ÿ�� : ");
		System.out.println(text_array.getClass().getName());
		System.out.print("text_array�� ���� : ");
		System.out.println(text_array.length);
		
	}
}
// OUTPUT
/* 
		titles�� ù ��° ��� : <span class="ah_k">����</span>
		titles�� ���� : 40
		titles�� ������Ÿ�� : org.jsoup.select.Elements
		text_array ���� ��� : 
		����
		�ư���
		ÿ�� �ƽ���
		�뱸����
		������
		����
		����ž
		������ġ ������
		���׸�
		����ũ
		������8
		�λ곯��
		ȿ���� �ι�
		���̹�����û ��������
		����
		���鰡��
		���ﳯ��
		�㵵����
		������
		�������
		����
		�ư���
		ÿ�� �ƽ���
		�뱸����
		������
		����
		����ž
		������ġ ������
		���׸�
		����ũ
		������8
		�λ곯��
		ȿ���� �ι�
		���̹�����û ��������
		����
		���鰡��
		���ﳯ��
		�㵵����
		������
		�������
		text_array�� ������Ÿ�� : [Ljava.lang.String;
		text_array�� ���� : 40
 */