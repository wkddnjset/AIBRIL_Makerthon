package jsoup;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.List;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

public class read_json {

    public static void main(String[] args) {

        JSONParser parser = new JSONParser();

        try {

            Object all_data = parser.parse(new FileReader("c:\\test.json"));
            
            // 전체 데이터
            JSONObject jsonObject = (JSONObject) all_data;
            // region 데이터
            String region = (String) jsonObject.get("region");
            // contant 데이터
            JSONObject contant = (JSONObject) jsonObject.get("contant");
            // title를 ListArray로 저장
            JSONArray title = (JSONArray) contant.get("title");
            // category를 ListArray로 저장
            JSONArray category = (JSONArray) contant.get("category");        
            // intro를 ListArray로 저장
            JSONArray intro = (JSONArray) contant.get("intro");
            // location를 ListArray로 저장
            JSONArray location = (JSONArray) contant.get("location");
           
            System.out.println(category);
            Set<String> hashsetList = new HashSet<String>(category);

            System.out.println(hashsetList);
            System.out.println(hashsetList.getClass().getName());
            
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}