/**<p>Description</p>
 * @author Ivan Huo
 */
package com.fortex.quickRing.rest.pojo;

/**
 * @author Administrator
 *
 */
public class InitiatorLog {
	private String logIncomingMsg;
	private String logOutgoingMsg;
	private String logHeartBeatMsg;
	private String logAdminMsg;
	
	public String getLogIncomingMsg() {
		return logIncomingMsg;
	}
	public void setLogIncomingMsg(String logIncomingMsg) {
		this.logIncomingMsg = logIncomingMsg;
	}
	 
	public String getLogHeartBeatMsg() {
		return logHeartBeatMsg;
	}
	public void setLogHeartBeatMsg(String logHeartBeatMsg) {
		this.logHeartBeatMsg = logHeartBeatMsg;
	}
	public String getLogAdminMsg() {
		return logAdminMsg;
	}
	public void setLogAdminMsg(String logAdminMsg) {
		this.logAdminMsg = logAdminMsg;
	}
	public String getLogOutgoingMsg() {
		return logOutgoingMsg;
	}
	public void setLogOutgoingMsg(String logOutgoingMsg) {
		this.logOutgoingMsg = logOutgoingMsg;
	}
	
	
}
