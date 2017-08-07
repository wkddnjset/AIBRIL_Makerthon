package Conversation.AIBRIL;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.InputData;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

/*
 * {
"url" : "https://gateway.aibril-watson.kr/conversation/api",
"username" : "3261ef29-4d9e-4220-ac52-72728ec18d68",
"password" : "xz5IcLm6yDr1" 
}
 */
public class App 
{
    public static void main( String[] args )
    {
    	ConversationService service = new ConversationService(ConversationService.VERSION_DATE_2017_05_26);
    	service.setUsernameAndPassword("3261ef29-4d9e-4220-ac52-72728ec18d68", "xz5IcLm6yDr1");
    	
    	//String workspaceId = "76245395-689c-406a-a712-52960cbf2500";
    	InputData input = new InputData.Builder("오늘 날씨가 뭐야").build();
    	MessageOptions options = new MessageOptions.Builder("76245395-689c-406a-a712-52960cbf2500").input(input).build();
    	MessageResponse response = service.message(options).execute();
    	
    	//System.out.println(options);
    	//System.out.println(input);
    	//System.out.println(service);
    	System.out.println(response);
    }
}
