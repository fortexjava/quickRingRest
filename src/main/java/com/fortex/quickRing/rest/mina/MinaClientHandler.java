/**<p>Description</p>
 * @author Ivan Huo
 */
package com.fortex.quickRing.rest.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.json.JSONObject;

/**
 * @author Administrator
 *
 */
public class MinaClientHandler  extends IoHandlerAdapter {
	private MinaTcpClient client;
	
	
	public MinaClientHandler(MinaTcpClient cl){
		client=cl;
	}
	
	
	 @Override  
	    public void sessionOpened(IoSession session) throws Exception {  
	       // System.out.println("incomming 客户端: " + session.getRemoteAddress());  
	    }  
	  
	    @Override  
	    public void exceptionCaught(IoSession session, Throwable cause)  
	            throws Exception {  
	       // System.out.println("客户端发送信息异常....");  
	    }  
	  
	    // 当客户端发送消息到达时  
	    @Override  
	    public void messageReceived(IoSession session, Object message)  
	            throws Exception {  
	  
	       // System.out.println("服务器返回的数据：" + message.toString());  
	        client.setReturnMsg(message.toString());
	        client.stop();
	    }  
	  
	    @Override  
	    public void sessionClosed(IoSession session) throws Exception {  
	      //  System.out.println("客户端与服务端断开连接.....");  
	    }  
	  
	    @Override  
	    public void sessionCreated(IoSession session) throws Exception {  
	        // TODO Auto-generated method stub  
//	        System.out  
//	                .println("one Client Connection" + session.getRemoteAddress());
	        
	    }  
}
