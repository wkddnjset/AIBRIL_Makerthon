package Conversation.AIBRIL;

import java.util.HashMap;
import java.util.Map;

import com.ibm.watson.developer_cloud.conversation.v1.model.InputData;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

public class To_node {
	Map<String, Object> context = new HashMap<String, Object>();
	String workspaceId = "76245395-689c-406a-a712-52960cbf2500";
	
	// first message
	MessageRequest newMessage = new MessageRequest.Builder()
		.input(new InputData.Builder("First message").build())
		.context(context)
		.build();

	MessageResponse response = service.message(workspaceId, newMessage).execute();

	// second message
	newMessage = new MessageRequest.Builder()
		.input(new InputData.Builder("Second message").build())
		.context(response.getContext()) // output context from the first message
		.build();

	response = service.message(workspaceId, newMessage).execute();

	System.out.println(response);
}
