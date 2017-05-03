/**<p>Description</p>
 * @author Ivan Huo
 */
package com.fortex.quickRing.rest.pojo;

import org.json.JSONArray;

/**
 * @author Ivan Huo
 *
 */
public class OnlineUsers {
	private JSONArray userNames;

	public JSONArray getUserNames() {
		return userNames;
	}

	public void setUserNames(JSONArray userNames) {
		this.userNames = userNames;
	}

//	public String[] getUserNames() {
//		return userNames;
//	}
//
//	public void setUserNames(String[] userNames) {
//		this.userNames = userNames;
//	}
	
	
}
