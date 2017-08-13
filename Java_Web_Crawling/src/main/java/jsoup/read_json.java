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
            
            // ��ü ������
            JSONObject jsonObject = (JSONObject) all_data;
            // region ������
            String region = (String) jsonObject.get("region");
            // contant ������
            JSONObject contant = (JSONObject) jsonObject.get("contant");
            // title�� ListArray�� ����
            JSONArray title = (JSONArray) contant.get("title");
            // category�� ListArray�� ����
            JSONArray category = (JSONArray) contant.get("category");        
            // intro�� ListArray�� ����
            JSONArray intro = (JSONArray) contant.get("intro");
            // location�� ListArray�� ����
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