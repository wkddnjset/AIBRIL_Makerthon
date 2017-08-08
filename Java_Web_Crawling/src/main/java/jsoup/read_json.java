package jsoup;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.ArrayList;

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
            JSONArray category = (JSONArray) contant.get("title");        
            // intro�� ListArray�� ����
            JSONArray intro = (JSONArray) contant.get("title");
            // location�� ListArray�� ����
            JSONArray location = (JSONArray) contant.get("title");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}