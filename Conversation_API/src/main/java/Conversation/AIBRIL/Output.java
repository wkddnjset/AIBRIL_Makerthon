package Conversation.AIBRIL;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.InputData;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/*
 * {
"url" : "https://gateway.aibril-watson.kr/conversation/api",
"username" : "3261ef29-4d9e-4220-ac52-72728ec18d68",
"password" : "xz5IcLm6yDr1" 
}
 */
public class Output 
{
    public static void main( String[] args )
    {
    	ConversationService service = new ConversationService(ConversationService.VERSION_DATE_2017_05_26);
    	service.setUsernameAndPassword("3261ef29-4d9e-4220-ac52-72728ec18d68", "xz5IcLm6yDr1");
    	
    	//String workspaceId = "76245395-689c-406a-a712-52960cbf2500";
    	InputData input = new InputData.Builder("ㅎㅇ").build();
    	MessageOptions options = new MessageOptions.Builder("76245395-689c-406a-a712-52960cbf2500").input(input).build();
    	MessageResponse response = service.message(options).execute();
    	
    	//System.out.println(options);
    	//System.out.println(input);
    	//System.out.println(service);
    	String Output = response.getOutput().getText().toString();
    	String Entity = response.getEntities().toString();
    	String Intent = response.getIntents().toString();
    	String context = response.getContext().toString();
    	
    	//System.out.println(response);
    	//System.out.println(context);
    	System.out.println(Output);
    	//System.out.println(Entity);
    	//System.out.println(Intent);
    	boolean a = Output.equals("[굿모닝]");
    	System.out.println(a);


    }
}
