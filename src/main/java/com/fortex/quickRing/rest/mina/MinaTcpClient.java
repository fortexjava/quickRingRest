/**<p>Description</p>
 * @author Ivan Huo
 */
package com.fortex.quickRing.rest.mina;

import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.json.JSONObject;

/**
 * @author Administrator
 *
 */
public class MinaTcpClient  {
	private String ipAddress;
	private int port;
	private ConnectFuture cf;
	private NioSocketConnector connector;
	boolean isStarted;
	private String returnMsg;

	public MinaTcpClient(String ipAddress, int port) {
		this.ipAddress = ipAddress;
		this.port = port;
	}
	
	public void setReturnMsg(String cont){
		returnMsg=cont;
	}
	
	

	public String getReturnMsg() {
		return returnMsg;
	}

	public void start() {
		if (!isStarted) {
			connector = new NioSocketConnector();
			// 创建接受数据的过滤器
			DefaultIoFilterChainBuilder chain = connector.getFilterChain();

			// 设定这个过滤器将一行一行(/r/n)的读取数据
			chain.addLast("myChin", new ProtocolCodecFilter(new TextLineCodecFactory()));

			// 客户端的消息处理器：一个SamplMinaServerHander对象
			connector.setHandler(new MinaClientHandler(this));

			// set connect timeout
			connector.setConnectTimeout(30);
			 
			// 连接到服务器：
			cf = connector.connect(new InetSocketAddress(ipAddress, port));
			// Wait for the connection attempt to be finished.
			cf.awaitUninterruptibly();			 
			
		}
	}

	public void sendData(String data) {
		if(!isStarted)
			start();		 
		cf.getSession().write(data);
		cf.getSession().getCloseFuture().awaitUninterruptibly();
	}

	public void stop() {		 		
		connector.dispose();
	}
	
	/**
	public static void main(String args[]) throws InterruptedException {		

		MinaTcpClient client = new MinaTcpClient("192.1.2.111", 8888);
		
		
		
		JSONObject obj = new JSONObject();
		obj.put("requestType", "status");
		client.sendData(obj.toString());			 				
		
		System.out.println(client.getReturnMsg());
		JSONObject json= new JSONObject(client.getReturnMsg());
		System.out.println(json.get("status"));
		
		obj = new JSONObject();
		obj.put("requestType", "logonUsers");
		client.sendData(obj.toString());
		json = new JSONObject(client.getReturnMsg());
		String[] names= json.get("userNames").toString().replaceAll("\\[", "").replaceAll("\\]","").split(",");	
		System.out.println(names[0]);
	}
	**/
}
