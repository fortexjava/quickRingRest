/**<p>Description</p>
 * @author Ivan Huo
 */
package com.fortex.quickRing.rest.pojo;

/**
 * @author Administrator
 *
 */
public class TradeInitiator {
	private String ftsUserName;
	private String ftsPassword;
	private String socketConnectHost;
	private String socketConnectPort;
	private String senderCompId;
	private String targetCompId;
	
	public String getFtsUserName() {
		return ftsUserName;
	}
	public void setFqsUserName(String ftsUserName) {
		this.ftsUserName = ftsUserName;
	}
	public String getFtsPassword() {
		return ftsPassword;
	}
	public void setFqsPassword(String ftsPassword) {
		this.ftsPassword = ftsPassword;
	}
	public String getSocketConnectHost() {
		return socketConnectHost;
	}
	public void setSocketConnectHost(String socketConnectHost) {
		this.socketConnectHost = socketConnectHost;
	}
	public String getSocketConnectPort() {
		return socketConnectPort;
	}
	public void setSocketConnectPort(String socketConnectPort) {
		this.socketConnectPort = socketConnectPort;
	}
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
	
	
}
