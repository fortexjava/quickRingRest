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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fortex.quickRing.rest.pojo.AcceptorLog;
import com.fortex.quickRing.rest.pojo.InitiatorLog;
import com.fortex.quickRing.rest.pojo.QuoteAcceptor;
import com.fortex.quickRing.rest.pojo.RestObj;
import com.fortex.quickRing.rest.pojo.TradeInitiator;

/**
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/trade")
public class FtsController extends BaseController{
	private static Logger logger = Logger.getLogger(FtsController.class);
	@Autowired
	private Environment env;
	
	@RequestMapping("/initiatorLoadSetting")
	public RestObj initiatorLoadSetting(){		
		RestObj obj = new RestObj();
		String initiatorPath = env.getProperty("trade.initiatorFile");
		Properties prop = new Properties();
		InputStream in=null;
		try {
			in = new BufferedInputStream(new FileInputStream(initiatorPath));			 
			prop.load(in);
			TradeInitiator initiator=new TradeInitiator();
			initiator.setFqsPassword(prop.getProperty("FTSPassword"));
			initiator.setFqsUserName(prop.getProperty("FTSUserName"));
			initiator.setSenderCompId(prop.getProperty("SenderCompID"));
			initiator.setSocketConnectHost(prop.getProperty("SocketConnectHost"));
			initiator.setSocketConnectPort(prop.getProperty("SocketConnectPort"));
			initiator.setTargetCompId(prop.getProperty("TargetCompID"));
			obj.setData(initiator);
			logger.info("Load trade initiator setting successfully");
		}
		 catch (IOException e) {
				// TODO Auto-generated catch block
			logger.error(e);
			obj.setError("Error loading Trade initiator file");
		}finally{
			if(in!=null)
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					logger.error(e);
				}			
		}		
		return obj;
	}
		 	
	@RequestMapping(value="initiatorSaveSetting",method = { RequestMethod.POST })
	public RestObj initiatorSaveSetting(
			@RequestBody TradeInitiator init
			){
		RestObj obj = new RestObj();
		String path = env.getProperty("trade.initiatorFile");		
 		try {			 			
			List<String> list = new ArrayList<>();			
			list = Files.lines(Paths.get(path)).collect(Collectors.toList());
			
			int i=0;
			for(String cont:list){
				if(cont.indexOf("FTSPassword")==0){
					cont = "FTSPassword=" + init.getFtsPassword();
					list.set(i, cont);
				}
				if(cont.indexOf("FTSUserName")==0){
					cont = "FTSUserName=" + init.getFtsUserName();
					list.set(i, cont);
				}
				if(cont.indexOf("SenderCompID")==0){
					cont = "SenderCompID=" + init.getSenderCompId();
					list.set(i, cont);
				}
				if(cont.indexOf("SocketConnectHost")==0){
					cont = "SocketConnectHost=" + init.getSocketConnectHost();
					list.set(i, cont);
				}
				if(cont.indexOf("SocketConnectPort")==0){
					cont = "SocketConnectPort=" + init.getSocketConnectPort();
					list.set(i, cont);
				}
				if(cont.indexOf("TargetCompID")==0){
					cont = "TargetCompID=" + init.getTargetCompId();
					list.set(i, cont);
				}
				i++;
			}
			Files.write(Paths.get(path), list,
                    Charset.defaultCharset(), StandardOpenOption.WRITE);
			logger.info("Save trade initiator setting successfully");
		}
		 catch (IOException e) {
				// TODO Auto-generated catch block
			logger.error(e);
			obj.setError("Error saving Trade  initiator file");
		} 	
		return obj;
	}
	
	@RequestMapping("/acceptorLoadSetting")
	public RestObj acceptorLoadSetting(){
		RestObj obj = new RestObj();
		String path = env.getProperty("trade.acceptorFile");
		Properties prop = new Properties();
		InputStream in=null;
		try {
			in = new BufferedInputStream(new FileInputStream(path));			 
			prop.load(in);
			QuoteAcceptor acceptor=new QuoteAcceptor();				
			acceptor.setSenderCompId(prop.getProperty("SenderCompID"));
			acceptor.setTargetCompId(prop.getProperty("TargetCompID"));
			acceptor.setSocketAcceptPort(prop.getProperty("SocketAcceptPort"));
			acceptor.setPendingOrderCount(Integer.valueOf(prop.getProperty("PendingOrderCount")));
			obj.setData(acceptor);
			logger.info("Load trade acceptor setting successfully");
		}
		 catch (IOException e) {
				// TODO Auto-generated catch block
			logger.error(e);
			obj.setError("Error loading Trade  acceptor file");
		}finally{
			if(in!=null)
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					logger.error(e);
				}			
		}		
		return obj;
	}
	
	
	@RequestMapping(value="acceptorSaveSetting",method = { RequestMethod.POST })
	public RestObj acceptorSaveSetting(
			@RequestBody QuoteAcceptor acceptor
			){
		RestObj obj = new RestObj();
		String path = env.getProperty("trade.acceptorFile");		
 		try {			 			
			List<String> list = new ArrayList<>();			
			list = Files.lines(Paths.get(path)).collect(Collectors.toList());			
			int i=0;
			for(String cont:list){
				if(cont.indexOf("SenderCompID")==0){
					cont = "SenderCompID=" + acceptor.getSenderCompId();
					list.set(i, cont);
				}
				if(cont.indexOf("TargetCompID")==0){
					cont = "TargetCompID=" + acceptor.getTargetCompId();
					list.set(i, cont);
				}
				if(cont.indexOf("SocketAcceptPort")==0){
					cont = "SocketAcceptPort=" + acceptor.getSocketAcceptPort();
					list.set(i, cont);
				}
				if(cont.indexOf("PendingOrderCount")==0){
					cont = "PendingOrderCount=" + acceptor.getPendingOrderCount();
					list.set(i, cont);
				}
				i++;
			}
			Files.write(Paths.get(path), list,
                    Charset.defaultCharset(), StandardOpenOption.WRITE);
			logger.info("Save trade acceptor setting successfully");		
		}
		 catch (IOException e) {
				// TODO Auto-generated catch block
			logger.error(e);
			obj.setError("Error saving Trade acceptor file");
		} 	
		return obj;
	}
				
	
	@RequestMapping("/initiatorLoadLogSetting")
	public RestObj initiatorLoadLogSetting(){
		RestObj obj = new RestObj();
		String path = env.getProperty("trade.initiatorFile");
		Properties prop = new Properties();
		InputStream in=null;
		try {
			in = new BufferedInputStream(new FileInputStream(path));			 
			prop.load(in);
			InitiatorLog log=new InitiatorLog();
			log.setLogAdminMsg(prop.getProperty("LogAdminMsg"));
			log.setLogHeartBeatMsg(prop.getProperty("LogHeartBeatMsg"));
			log.setLogIncomingMsg(prop.getProperty("LogIncomingMsg"));
			log.setLogOutgoingMsg(prop.getProperty("LogOutgoingMsg"));
			obj.setData(log);
			logger.info("Load trade initiator log setting successfully");
		}
		 catch (IOException e) {
				// TODO Auto-generated catch block
			logger.error(e);
			obj.setError("Error loading Trade initiator log file");
		}finally{
			if(in!=null)
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					logger.error(e);
				}			
		}		
		return obj;
	}
	
	@RequestMapping(value="initiatorSaveLogSetting",method = { RequestMethod.POST })
	public RestObj initiatorSaveLogSetting(
			@RequestBody InitiatorLog log
			){
		RestObj obj = new RestObj();
		String path = env.getProperty("trade.initiatorFile");				 	
		try {			 			
			List<String> list = new ArrayList<>();			
			list = Files.lines(Paths.get(path)).collect(Collectors.toList());			
			int i=0;
			for(String cont:list){
				if(cont.indexOf("LogIncomingMsg")==0){
					cont = "LogIncomingMsg=" + log.getLogIncomingMsg();
					list.set(i, cont);
				}
				if(cont.indexOf("LogOutgoingMsg")==0){
					cont = "LogOutgoingMsg=" + log.getLogOutgoingMsg();
					list.set(i, cont);
				}
				if(cont.indexOf("LogHeartBeatMsg")==0){
					cont = "LogHeartBeatMsg=" + log.getLogHeartBeatMsg();
					list.set(i, cont);
				}
				if(cont.indexOf("LogAdminMsg")==0){
					cont = "LogAdminMsg=" + log.getLogAdminMsg();
					list.set(i, cont);
				}				
				i++;
			}
			Files.write(Paths.get(path), list,
                    Charset.defaultCharset(), StandardOpenOption.WRITE);		
			logger.info("Save trade initiator log setting successfully");
		}
		 catch (IOException e) {
				// TODO Auto-generated catch block
			logger.error(e);
			obj.setError("Error saving Trade initiator log file");
		} 	
		return obj;
	}
	
	
	@RequestMapping("/acceptorLoadLogSetting")
	public RestObj acceptorLoadLogSetting(){
		RestObj obj = new RestObj();
		String path = env.getProperty("trade.acceptorFile");
		Properties prop = new Properties();
		InputStream in=null;
		try {
			in = new BufferedInputStream(new FileInputStream(path));			 
			prop.load(in);
			AcceptorLog log=new AcceptorLog();				
			log.setLogAdminMsg(prop.getProperty("LogAdminMsg"));
			log.setLogHeartBeatMsg(prop.getProperty("LogHeartBeatMsg"));
			log.setLogIncomingMsg(prop.getProperty("LogIncomingMsg"));
			log.setLogOutgoingMsg(prop.getProperty("LogOutgoingMsg"));
			obj.setData(log);
			logger.info("Load trade acceptor log setting successfully");
		}
		 catch (IOException e) {
				// TODO Auto-generated catch block
			logger.error(e);
			obj.setError("Error loading Trade acceptor log file");
		}finally{
			if(in!=null)
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					logger.error(e);
				}			
		}		
		return obj;
	} 
	
	@RequestMapping(value="acceptorSaveLogSetting",method = { RequestMethod.POST })
	public RestObj acceptorSaveLogSetting(
			@RequestBody AcceptorLog log
			){
		RestObj obj = new RestObj();
		String path = env.getProperty("trade.acceptorFile");				 	
		try {			 			
			List<String> list = new ArrayList<>();			
			list = Files.lines(Paths.get(path)).collect(Collectors.toList());			
			int i=0;
			for(String cont:list){
				if(cont.indexOf("LogIncomingMsg")==0){
					cont = "LogIncomingMsg=" + log.getLogIncomingMsg();
					list.set(i, cont);
				}
				if(cont.indexOf("LogOutgoingMsg")==0){
					cont = "LogOutgoingMsg=" + log.getLogOutgoingMsg();
					list.set(i, cont);
				}
				if(cont.indexOf("LogHeartBeatMsg")==0){
					cont = "LogHeartBeatMsg=" + log.getLogHeartBeatMsg();
					list.set(i, cont);
				}
				if(cont.indexOf("LogAdminMsg")==0){
					cont = "LogAdminMsg=" + log.getLogAdminMsg();
					list.set(i, cont);
				}				
				i++;
			}
			Files.write(Paths.get(path), list,
                    Charset.defaultCharset(), StandardOpenOption.WRITE);	
			logger.info("Save trade acceptor log setting successfully");
		}
		 catch (IOException e) {
				// TODO Auto-generated catch block
			logger.error(e);
			obj.setError("Error saving Trade initiator log file");
		} 	
		return obj;
	}
}
