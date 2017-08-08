package jsoup;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Save_to_json {
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
             String cate_val = cont_category.text();
             String intro = cont_intro.text();
             String location = cont_location.text();
             
             title_arr[i-1] = title;
             intro_arr[i-1] = intro;
             location_arr[i-1] = location;   
             //System.out.print("cate_val : ");
             //System.out.println(cate_val);
             if(cate_val.equals("햄버거") || cate_val.equals("이탈리아음식") || cate_val.equals("바(BAR)") || cate_val.equals("패밀리레스토랑") || cate_val.equals("치킨,닭강정") || cate_val.equals("피자")) {
            	 String category = "양식";
            	 category_arr[i-1] =  category;
            	 //System.out.print("if : ");
            	 //System.out.println(category);
             }
             else if(cate_val.equals("해산물뷔페") || cate_val.equals("일식당") || cate_val.equals("초밥,롤") || cate_val.equals("생선회")){
            	 String category = "일식";
            	 category_arr[i-1] =  category;
             }
             else if(cate_val.equals("중식") || cate_val.equals("중식당")){
            	 String category = "중식";
            	 category_arr[i-1] =  category;
             }
             else if(cate_val.equals("한식뷔페") || cate_val.equals("육류,고기요리") || cate_val.equals("분식") || cate_val.equals("죽") || cate_val.equals("닭발") || cate_val.equals("주꾸미요리") || cate_val.equals("곱창,막창,양") || cate_val.equals("칼국수, 만두") || cate_val.equals("종합분식") || cate_val.equals("족발,보쌈") || cate_val.equals("백숙,삼계탕") || cate_val.equals("쌈밥") || cate_val.equals("종합분식") || cate_val.equals("떡볶이") || cate_val.equals("게요리") || cate_val.equals("막국수") || cate_val.equals("닭요리") || cate_val.equals("아귀찜,해물찜")){
            	 String category = "한식";
            	 category_arr[i-1] =  category;
             }
             else if(cate_val.equals("카페") || cate_val.equals("카페,디저트") || cate_val.equals("빙수")){
            	 String category = "디저트";
            	 category_arr[i-1] =  category;
             }
             else {
            	 String category = cont_category.text();
            	 category_arr[i-1] =  category;
            	 //System.out.print("else : ");
            	 //System.out.println(category);
             }
             
          }
          JSONObject all_data = new JSONObject();
          JSONObject restorant_data = new JSONObject();
          all_data.put("contant", restorant_data);
          all_data.put("region",word);
          
          JSONArray restorant_titl = new JSONArray();
          JSONArray restorant_cate = new JSONArray();
          JSONArray restorant_intr = new JSONArray();
          JSONArray restorant_loca = new JSONArray();
          for(int i=1; i<8; i++) {
        	  restorant_titl.add(title_arr[i-1]);
              restorant_cate.add(category_arr[i-1]);
              restorant_intr.add(intro_arr[i-1]);
              restorant_loca.add(location_arr[i-1]);
           }
          restorant_data.put("title", restorant_titl);
          restorant_data.put("category", restorant_cate);
          restorant_data.put("intro", restorant_intr);
          restorant_data.put("location", restorant_loca);
          
          try (FileWriter file = new FileWriter("C:\\test.json")) {

              file.write(all_data.toJSONString());
              file.flush();

          } catch (IOException e) {
              e.printStackTrace();
          }
          
          System.out.print(all_data);
          /*
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
          */
      } catch(ArrayIndexOutOfBoundsException e) {
    	  System.out.println("다시");
      }catch(StringIndexOutOfBoundsException e) {
    	  System.out.println("다시");
      }
      
   
   }

}