/**<p>Description</p>
 * @author Ivan Huo
 */
package com.fortex.quickRing.rest.pojo;

/**
 * @author Administrator
 *
 */
public class QuoteInitiator {
	private String fqsUserName;
	private String fqsPassword;
	private String socketConnectHost;
	private String socketConnectPort;
	private String senderCompId;
	private String targetCompId;
	
	public String getFqsUserName() {
		return fqsUserName;
	}
	public void setFqsUserName(String fqsUserName) {
		this.fqsUserName = fqsUserName;
	}
	public String getFqsPassword() {
		return fqsPassword;
	}
	public void setFqsPassword(String fqsPassword) {
		this.fqsPassword = fqsPassword;
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
