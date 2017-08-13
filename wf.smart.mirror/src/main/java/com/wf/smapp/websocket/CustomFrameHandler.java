/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.wf.smapp.websocket;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wf.smapp.speech.Speech;
import com.wf.smapp.util.SpringUtil;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

public class CustomFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomFrameHandler.class);
	private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	private Speech speech;
	
	public static void sendMessage(String msg){
		
		for(Channel ch:channels){
			logger.info("ch:"+ch.toString());
			
			JSONObject sendData = new JSONObject();
			sendData.put("msg",msg);
			ch.writeAndFlush(new TextWebSocketFrame(sendData.toJSONString() ));
		}
		
	}
	
	public CustomFrameHandler() {
		logger.info("CustomFrameHandler....");
		speech = (Speech)SpringUtil.ctx.getBean(Speech.class);
	}
	
	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
//		super.channelRegistered(ctx);
		logger.info("channelRegistered:"+ctx.name());
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
//		super.channelUnregistered(ctx);
		logger.info("channelUnregistered:"+ctx.name());
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
//		super.channelActive(ctx);

		Channel incoming = ctx.channel();
		logger.info("channelActive:"+incoming.remoteAddress()+" has joined!");
		channels.add(incoming);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//		super.channelInactive(ctx);
		Channel incoming = ctx.channel();
		
		logger.info("channelInactive:"+incoming.remoteAddress()+" has left!");
		channels.remove(incoming);
	}


	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//		super.exceptionCaught(ctx, cause);
		ctx.close();
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//		super.channelReadComplete(ctx);
		ctx.flush();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		if (msg instanceof TextWebSocketFrame) {
			TextWebSocketFrame frame = (TextWebSocketFrame)msg;

				JSONParser jsonParser = new JSONParser();
//				logger.info("frame.text():"+frame.text());
				JSONObject jsonData = (JSONObject) jsonParser.parse(frame.text());
				String data = (String) jsonData.get("text");
				
				if (data.length()>0){
					logger.info("channelRead:"+data);
					speech.setSpeech(data);
				}
			
			
//			logger.info("channels:"+channels.size());
			

			//ctx.writeAndFlush(new TextWebSocketFrame("빅캐�???��: " +frame.text().toUpperCase()));
			
			((TextWebSocketFrame) msg).release();
		}
	}
}
