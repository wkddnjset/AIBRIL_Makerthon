package jsoup;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class category {
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
            // title�� ListArray�� 
           // ����
            JSONArray title = (JSONArray) contant.get("title");
            // category�� ListArray�� ����
            JSONArray category = (JSONArray) contant.get("category");        
            // intro�� ListArray�� ����
            JSONArray intro = (JSONArray) contant.get("title");
            // location�� ListArray�� ����
            JSONArray location = (JSONArray) contant.get("title");
            
           // System.out.println(jsonObject);
            
            
            //System.out.println(category(0));
           
           // public static JSONArray getJsonArrayFromList(List<Map<String,Object>>list) {
            	
            //}
          // @SuppressWarnings("unchecked")
    
            //1.JSONArray  �� ArrayList�� �ٲٱ�
            /*ArrayList<String> listdata = new ArrayList<String>();     
            JSONArray jArray = (JSONArray)category; 
            if (jArray != null) { 
               for (int i=0;i<jArray.size();i++){ 
                listdata.add(((Object) jArray).getString(i));
               } 
            } */
            
            JSONArray yang = new JSONArray();
            JSONArray ill = new JSONArray();
            
            for(int i = 0; i<category.size();i++)
            {
            		if(category.contains("���"));
            }		
        
            System.out.println(title);
            System.out.println(category);
            //System.out.println(category.get(0));
            //System.out.println(title.get(0));
            //String arr2[]=new String[category.length];
           // for (int i = 0; i<category.length)
            JSONArray list = new JSONArray();
            for(int i1=0;i1<category.size();i1++)
            {
            		if(!list.contains(category.get(i1)))
            			list.add(category.get(i1));
            			
            }
            //System.out.println(list.get(0));
            //System.out.println(list.get(1));
            //System.out.println(list.get(2));
            //System.out.println(list.get(3));
            
            for(int j=0;j<list.size();j++)
            {
            		System.out.print(list.get(j));
            		if(j==list.size()-1)
            		{
            			break;
            		}
            		System.out.print(",");
            }
            
            System.out.println(" �� �ϳ�����!");
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}
