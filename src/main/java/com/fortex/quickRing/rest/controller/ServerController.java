/**<p>Description</p>
 * @author Ivan Huo
 */
package com.fortex.quickRing.rest.controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fortex.quickRing.rest.mina.MinaTcpClient;
import com.fortex.quickRing.rest.pojo.Account;
import com.fortex.quickRing.rest.pojo.DataBase;
import com.fortex.quickRing.rest.pojo.OnlineUsers;
import com.fortex.quickRing.rest.pojo.RestObj;
import com.fortex.quickRing.rest.pojo.Status;

/**
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/server")
public class ServerController extends BaseController {
	private static Logger logger = Logger.getLogger(ServerController.class);
	@Autowired
	private Environment env;

//	@Autowired
//	@Qualifier("xRingJdbcTemplate")
//	protected JdbcTemplate xRingJdbcTemplate;

	@RequestMapping("/dbQuoteLoadSetting")
	public RestObj dbLoadQuoteSetting() {
		RestObj obj = new RestObj();
		String path = env.getProperty("server.dbQuoteFile");
		Properties prop = new Properties();
		InputStream in = null;
		try {
			setDataInfoToObject(obj,path, in, prop);
			logger.info("Load quote database setting successfully");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			obj.setError("Error quote loading database file");
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					logger.error(e);
				}
		}
		return obj;
	}

	@RequestMapping("/dbTradeLoadSetting")
	public RestObj dbLoadTradeSetting() {
		RestObj obj = new RestObj();
		String path = env.getProperty("server.dbTradeFile");
		Properties prop = new Properties();
		InputStream in = null;
		try {
			setDataInfoToObject(obj,path, in, prop);
			logger.info("Load trade database setting successfully");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			obj.setError("Error trade loading database file");
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					logger.error(e);
				}
		}
		return obj;
	}

	private void setDataInfoToObject(RestObj obj,String path, InputStream in, Properties prop) throws IOException {
		in = new BufferedInputStream(new FileInputStream(path));
		prop.load(in);
		DataBase db = new DataBase();
		db.setPassword(prop.getProperty("password"));
		db.setUrl(prop.getProperty("url"));
		db.setUsername(prop.getProperty("username"));
		obj.setData(db);
	}

	@RequestMapping(value = "dbQuoteSaveSetting", method = { RequestMethod.POST })
	public RestObj dbQuoteSaveSetting(@RequestBody DataBase db) {
		RestObj obj = new RestObj();
		String path = env.getProperty("server.dbQuoteFile");
		try {
			saveDbSetting(path, db);
			logger.info("Save quote database setting successfully");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			obj.setError("Error saving quote database file");
		}
		return obj;
	}

	@RequestMapping(value = "dbTradeSaveSetting", method = { RequestMethod.POST })
	public RestObj dbTradeSaveSetting(@RequestBody DataBase db) {
		RestObj obj = new RestObj();
		String path = env.getProperty("server.dbTradeFile");
		try {
			saveDbSetting(path, db);
			logger.info("Save trade database setting successfully");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			obj.setError("Error saving trade database file");
		}
		return obj;
	}

	private void saveDbSetting(String path, DataBase db) throws IOException {
		List<String> list = new ArrayList<>();
		list = Files.lines(Paths.get(path)).collect(Collectors.toList());
		int i = 0;
		for (String cont : list) {
			if (cont.indexOf("username") == 0) {
				cont = "username=" + db.getUsername();
				list.set(i, cont);
			}
			if (cont.indexOf("password") == 0) {
				cont = "password=" + db.getPassword();
				list.set(i, cont);
			}
			if (cont.indexOf("url") == 0) {
				cont = "url=" + db.getUrl();
				list.set(i, cont);
			}
			i++;
		}
		Files.write(Paths.get(path), list, Charset.defaultCharset(), StandardOpenOption.WRITE);
	}

	@RequestMapping("/startTrade")
	public RestObj startTrade() {
		RestObj obj = new RestObj();
		try {
			
			String cmd = env.getProperty("server.startTradeScript");
			Process process = Runtime.getRuntime().exec(cmd);
			process.getOutputStream().close();
			boolean success = process.isAlive();
			if (!success) {
				obj.setError("Error starting trade xring");
			}
			logger.info("Start trade xring successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
			obj.setError("Error starting trade xring");
		}
		return obj;
	}

	@RequestMapping("/stopTrade")
	public RestObj stopTrade() {
		RestObj obj = new RestObj();
		try {
			
			String cmd = env.getProperty("server.stopTradeScript");
			System.out.println(cmd);
			Process process = Runtime.getRuntime().exec(new String[] { "/bin/sh", "-c", cmd });
			process.getOutputStream().close();
			boolean success = process.isAlive();
			if (!success) {
				obj.setError("Error stopping trade xring");
			}
			logger.info("Stop trade xring successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
			obj.setError("Error stopping trade xring");
		}
		return obj;
	}

	@RequestMapping("/startQuote")
	public RestObj startQuote() {
		RestObj obj = new RestObj();
		try {
			String cmd = env.getProperty("server.startQuoteScript");
			Process process = Runtime.getRuntime().exec(cmd);
			process.getOutputStream().close();
			boolean success = process.isAlive();
			// System.out.println("return value:" + success);
			if (!success) {
				obj.setError("Error starting quote xring");
			}
			logger.info("Start quote xring successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
			obj.setError("Error starting quote xring");
		}
		return obj;
	}

	@RequestMapping("/stopQuote")
	public RestObj stopQuote() {
		RestObj obj = new RestObj();
		try {
			String cmd = env.getProperty("server.stopQuoteScript");
			Process process = Runtime.getRuntime().exec(new String[] { "/bin/sh", "-c", cmd });
			process.getOutputStream().close();
			boolean success = process.isAlive();
			if (!success) {
				obj.setError("Error stopping quote xring");
			}
			logger.info("Stop quote xring successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
			obj.setError("Error stopping quote xring");
		}
		return obj;
	}

	@RequestMapping("/serverStatus")
	public RestObj serverStatus() {
		RestObj obj = new RestObj();
		try {
			String xRingAddress = env.getProperty("server.xringAddress");
			String xRingPort = env.getProperty("server.xringPort");
			MinaTcpClient client = new MinaTcpClient(xRingAddress, Integer.valueOf(xRingPort));
			JSONObject data = new JSONObject();
			data.put("requestType", "status");
			client.sendData(data.toString());
			JSONObject json = new JSONObject(client.getReturnMsg());
			Status status = new Status();
			status.setStatus(json.getString("status"));
			obj.setData(status);
			logger.info("Load server status");
		} catch (Exception e) {
			logger.error(e);
			obj.setError("Error loading server status");
		}
		return obj;
	}

	@RequestMapping("/onlineUsers")
	public RestObj onlineUsers() {
		RestObj obj = new RestObj();
		try {
			String xRingAddress = env.getProperty("server.xringAddress");
			String xRingPort = env.getProperty("server.xringPort");
			MinaTcpClient client = new MinaTcpClient(xRingAddress, Integer.valueOf(xRingPort));
			JSONObject data = new JSONObject();
			data.put("requestType", "logonUsers");
			client.sendData(data.toString());
			JSONObject json = new JSONObject(client.getReturnMsg());
			
			//String[] names = json.get("userNames").toString().replaceAll("\\[", "").replaceAll("\\]", "").split(",");
//			for(int i=0;i<names.length;i++){
//				names[i]=names[i].replaceAll("\"", "");
//			}
			JSONArray usernames= (JSONArray)json.get("userNames");
			OnlineUsers onlineUsers = new OnlineUsers();
			onlineUsers.setUserNames(usernames);
			obj.setData(onlineUsers);
			logger.info("Load online users");
		} catch (Exception e) {
			logger.error(e);
			obj.setError("Error loading online users");
		}
		return obj;
	}

	@RequestMapping("/kickUser")
	public RestObj kickUser(@RequestBody Account account) {
		RestObj obj = new RestObj();
		try {
			String xRingAddress = env.getProperty("server.xringAddress");
			String xRingPort = env.getProperty("server.xringPort");
			MinaTcpClient client = new MinaTcpClient(xRingAddress, Integer.valueOf(xRingPort));
			JSONObject data = new JSONObject();
			data.put("requestType", "disconnect");
			data.put("account", account.getAccount());
			client.sendData(data.toString());
			JSONObject json = new JSONObject(client.getReturnMsg());
			Status status = new Status();
			status.setStatus(json.getString("status"));
			obj.setData(status);
			logger.info("Kick user" + account.getAccount()+ "success");
		} catch (Exception e) {
			logger.error(e);
			obj.setError("Error kick user" + account.getAccount());
		}
		return obj;
	}

//	@RequestMapping("/blockAccounts")
//	public RestObj blockAccounts() {
//		RestObj obj = new RestObj();
//		try {
//			String sql = "select account from xRing_BlockAccount order by id desc";
//			List<String> blockList = xRingJdbcTemplate.queryForList(sql, String.class);
//			BlockAccounts block = new BlockAccounts();
//			block.setBlockList(blockList);
//			obj.setData(block);
//			logger.info("Block account list loaded");
//		} catch (Exception e) {
//			logger.error(e);
//			obj.setError("Error load blockaccounts");
//		}
//		return obj;
//	}
//	
//	@RequestMapping("/blockAccountAdd")
//	public RestObj blockAccountAdd(@RequestBody Account account) {
//		RestObj obj = new RestObj();
//		try {
//			String sql = "insert into xRing_BlockAccount values(?)";				
//			xRingJdbcTemplate.update(sql, new Object[]{ account.getAccount() });		
//			logger.info("Block account added (" + account.getAccount() + ")");
//		}
//		catch(org.springframework.dao.DuplicateKeyException e){
//			logger.error(e);
//			obj.setError("Add duplicated block account is not allowed:account(" + account.getAccount() + ")");
//		}
//		catch (Exception e) {
//			logger.error(e);
//			obj.setError("Error add block accounts");
//		}
//		return obj;
//	}
//	
//	@RequestMapping("/blockAccountRemove")
//	public RestObj blockAccountRemove(@RequestBody Account account) {
//		RestObj obj = new RestObj();
//		try {
//			String sql = "delete from xRing_BlockAccount where account =?";			
//			xRingJdbcTemplate.update(sql, new Object[]{ account.getAccount() });			
//			logger.info("Block account removed (" + account.getAccount() + ")");
//		} catch (Exception e) {
//			logger.error(e);
//			obj.setError("Error delete blockaccount");
//		}
//		return obj;
//	}

}
