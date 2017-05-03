/**<p>Description</p>
 * @author Ivan Huo
 */
package com.fortex.quickRing.rest.pojo;

/**
 * @author Administrator
 *
 */
public class TradeAcceptor {
	private String senderCompId;
	private String targetCompId;
	private String socketAcceptPort;
	
	public String getSenderCompId() {
		return senderCompId;
	}
	public void setSenderCompId(String senderCompId) {
		this.senderCompId = senderCompId;
	}
	public String getTargetCompId() {
		return targetCompId;
	}
	public void setTargetCompId(String targetCompId) {
		this.targetCompId = targetCompId;
	}
	public String getSocketAcceptPort() {
		return socketAcceptPort;
	}
	public void setSocketAcceptPort(String socketAcceptPort) {
		this.socketAcceptPort = socketAcceptPort;
	}
	
	
}
