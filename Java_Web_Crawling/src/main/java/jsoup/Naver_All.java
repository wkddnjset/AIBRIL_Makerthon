package jsoup;
import java.util.Scanner;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;


public class Naver_All {
   public static void main(String[] args) throws IOException{
      String word = null;
      Scanner wordReader = new Scanner(System.in);
      System.out.print("지역을 입력하세요 : ");
      word = wordReader.nextLine();
      Document doc = Jsoup.connect("https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query="+word+"+맛집").get();
      
      try {
    	  String[] title_arr = new String[7];
          String[] category_arr = new String[7];
          String[] intro_arr = new String[7];
          String[] location_arr = new String[7];
          for(int i= 1; i<8; i++) {
             Elements titles = doc.select("#sp_local_"+i+" .info_area a");
             //String title = titles.attr("title");
             //Elements locations = doc.select("#sp_local_"+number_of_array+" .ad_txt:eq(1)");
             //String location = locations.attr("title");
             
             String cont_id = titles.attr("href").substring(45);
             Document cont = Jsoup.connect("https://store.naver.com/restaurants/detail?id="+cont_id).get();
             
             Elements cont_titles = cont.select(".ct_box_area .biz_name_area strong.name");
             Elements cont_category = cont.select(".ct_box_area .biz_name_area .category");
             Elements cont_intro = cont.select(".biz_name_area .txt:eq(0)");
             Elements cont_location = cont.select(".txt .addr:eq(1)");
             
             String title = cont_titles.text();
             String category = cont_category.text();
             String intro = cont_intro.text();
             String location = cont_location.text();
             
             title_arr[i-1] = title;
             category_arr[i-1] = category;
             intro_arr[i-1] = intro;
             location_arr[i-1] = location;   
          
          }
          
          for(int i=1; i<8; i++) {
             System.out.print("title : ");
             System.out.println(title_arr[i-1]);
             System.out.print("category : ");
             System.out.println(category_arr[i-1]);
             System.out.print("intro : ");
             System.out.println(intro_arr[i-1]);
             System.out.print("location : ");
             System.out.println(location_arr[i-1]);
          }
      } catch(ArrayIndexOutOfBoundsException e) {
    	  System.out.println("다시");
      }catch(StringIndexOutOfBoundsException e) {
    	  System.out.println("다시");
      }
      
   
   }

}