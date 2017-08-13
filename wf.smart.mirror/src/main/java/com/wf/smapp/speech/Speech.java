package com.wf.smapp.speech;


import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.InputData;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageInput;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.wf.smapp.crawling.Naver;
import com.wf.smapp.websocket.CustomFrameHandler;

@Component
public class Speech {
	private static final Logger logger = LoggerFactory.getLogger(Speech.class);
	
//	private static Speech instance;
	
	private String area;
	
	private String speech;
	
	@Value("#{config['watson.user']}")
    private String user;
	
	@Value("#{config['watson.password']}")
    private String password;
	
	@Value("#{config['watson.workspace']}")
	private String workspace;
	
	private MessageResponse response;
	
	private ConversationService service;
	
	
	@PostConstruct
	public void init(){
    	service = new ConversationService(ConversationService.VERSION_DATE_2017_05_26);
    	service.setUsernameAndPassword(user, password);
    	response = new MessageResponse();
	}
	/**
	 * @return the area
	 */
	public synchronized String getArea() {
		return area;
	}

	/**
	 * @param area the area to set
	 */
	public synchronized void setArea(String area) {
		this.area = area;
	}

	
	public synchronized String getSpeech(){
		logger.info("getSpeech: {}",speech );
		return this.speech ; 
	}
	
	public synchronized void setSpeech(String speech){
		logger.info("user: {}",user );
		logger.info("password: {}",password );
		logger.info("workspace: {}",workspace );
		logger.info("speech: {}",speech );
		this.speech = speech;
		
		
		InputData input = new InputData.Builder(speech).build();
		MessageOptions options = null;
		logger.info("pre message");
		try {
			if (response.getContext() == null){
				logger.info("first message");
				options = new MessageOptions.Builder(workspace)
				.input(input)
				.build();
			}else{
				logger.info("second message");
				options = new MessageOptions.Builder(workspace)
				.input(input)
				.context(response.getContext())
				.build();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
    	response = service.message(options).execute();
    	
    	for(String msg :response.getOutput().getText()){
    		String tmp = msg.trim();
    		System.out.println(">>>>>>>>>"+tmp);
    		if (tmp.equals("강남")){
    			String naver = Naver.getWeatcher(tmp);
    			logger.info("response: {}",naver );
    	    	CustomFrameHandler.sendMessage(naver);
    		}else if(tmp.equals("category")){
    	    	CustomFrameHandler.sendMessage("양식,일식 중 하나 골라봐.");
    		}else if(tmp.equals("일식")){
    			CustomFrameHandler.sendMessage("마코토,토끼정 강남");
    		}else if(tmp.equals("양식")){
    	    	CustomFrameHandler.sendMessage("바비레드,마녀주방,에이와이,킹콩 스테이크,쉐이크쉑 강남");
    		}else{
    			logger.info("response: {}",response.getOutput().getText().toString() );
    	    	CustomFrameHandler.sendMessage(response.getOutput().getText().toString());
    		}
    	}

    	System.out.println(response);

    	
		
	}

	
}
