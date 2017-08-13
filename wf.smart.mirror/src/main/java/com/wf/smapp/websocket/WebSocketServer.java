/**
 * 
 */
package com.wf.smapp.websocket;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;


@Component
public class WebSocketServer {

	private static final Logger LOGGER     = LoggerFactory.getLogger(WebSocketServer.class);

	@Value("#{config['netty.server.port']}")
    private int port;
	
	@Value("#{config['boss.thread.count']}")
	private int bossCount;
	
	@Value("#{config['worker.thread.count']}")
	private int workerCount;
	
	void threadMessage(String message) {
		String threadName = Thread.currentThread().getName();
		LOGGER.info("%s: %s%n", threadName, message);
	}

	@PostConstruct
	public void start() throws Exception {
		
		LOGGER.info("WebSocketServer<<<<<<<<<<<<<<<<<start:"+ port);
		
		SocketServerBody nssb1 = new SocketServerBody("Thread1");
		ExecutorService es = Executors.newSingleThreadExecutor();
		es.execute(nssb1);

	}
	

	@PreDestroy
	public void stop() {
		LOGGER.info(">>>>>>>>stop");
	}

	class SocketServerBody implements Runnable {

		String name;

		public SocketServerBody(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			
			NioEventLoopGroup bossGroup = new NioEventLoopGroup(bossCount);
			NioEventLoopGroup workerGroup = new NioEventLoopGroup(workerCount);

			try {
				
				ServerBootstrap b = new ServerBootstrap();
				b.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.localAddress(new InetSocketAddress(port))
				.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel sc) throws Exception {
							sc.pipeline().addLast(
									new HttpRequestDecoder(),
									new HttpObjectAggregator(65536),
									new HttpResponseEncoder(),
									new WebSocketServerProtocolHandler("/websocket"),
									new CustomFrameHandler()
									);														
						}
					})
				.option(ChannelOption.SO_BACKLOG, 128)
				.childOption(ChannelOption.SO_KEEPALIVE, true);
				ChannelFuture f = b.bind().sync();			
				
				f.channel().closeFuture().sync();
				LOGGER.info("after serverChannel");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				workerGroup.shutdownGracefully();
	            bossGroup.shutdownGracefully();
			}
			LOGGER.info("==========STop SocketServerBody ");
		}
	}

}
