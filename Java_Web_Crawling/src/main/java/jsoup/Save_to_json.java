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
      System.out.print("������ �Է��ϼ��� : ");
      word = wordReader.nextLine();
      Document doc = Jsoup.connect("https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query="+word+"+����").get();
      
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
             if(cate_val.equals("�ܹ���") || cate_val.equals("��Ż��������") || cate_val.equals("��(BAR)") || cate_val.equals("�йи��������") || cate_val.equals("ġŲ,�߰���") || cate_val.equals("����")) {
            	 String category = "���";
            	 category_arr[i-1] =  category;
            	 //System.out.print("if : ");
            	 //System.out.println(category);
             }
             else if(cate_val.equals("�ػ깰����") || cate_val.equals("�ϽĴ�") || cate_val.equals("�ʹ�,��") || cate_val.equals("����ȸ")){
            	 String category = "�Ͻ�";
            	 category_arr[i-1] =  category;
             }
             else if(cate_val.equals("�߽�") || cate_val.equals("�߽Ĵ�")){
            	 String category = "�߽�";
            	 category_arr[i-1] =  category;
             }
             else if(cate_val.equals("�ѽĺ���") || cate_val.equals("����,���丮") || cate_val.equals("�н�") || cate_val.equals("��") || cate_val.equals("�߹�") || cate_val.equals("�ֲٹ̿丮") || cate_val.equals("��â,��â,��") || cate_val.equals("Į����, ����") || cate_val.equals("���պн�") || cate_val.equals("����,����") || cate_val.equals("���,�����") || cate_val.equals("�ӹ�") || cate_val.equals("���պн�") || cate_val.equals("������") || cate_val.equals("�Կ丮") || cate_val.equals("������") || cate_val.equals("�߿丮") || cate_val.equals("�Ʊ���,�ع���")){
            	 String category = "�ѽ�";
            	 category_arr[i-1] =  category;
             }
             else if(cate_val.equals("ī��") || cate_val.equals("ī��,����Ʈ") || cate_val.equals("����")){
            	 String category = "����Ʈ";
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
    	  System.out.println("�ٽ�");
      }catch(StringIndexOutOfBoundsException e) {
    	  System.out.println("�ٽ�");
      }
      
   
   }

}