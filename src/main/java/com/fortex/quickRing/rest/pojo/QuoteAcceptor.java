/**<p>Description</p>
 * @author Ivan Huo
 */
package com.fortex.quickRing.rest.pojo;

/**
 * @author Administrator
 *
 */
public class QuoteAcceptor {
	private String senderCompId;
	private String targetCompId;
	private String socketAcceptPort;
	private int pendingOrderCount;
	
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
	public int getPendingOrderCount() {
		return pendingOrderCount;
	}
	public void setPendingOrderCount(int pendingOrderCount) {
		this.pendingOrderCount = pendingOrderCount;
	}
	
	
}
