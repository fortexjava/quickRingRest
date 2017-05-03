package com.fortex.quickRing.rest;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.Ssl;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.core.env.Environment;


/**
 * Hello world!
 *
 */
@SpringBootApplication
@ServletComponentScan
public class App  implements EmbeddedServletContainerCustomizer
{	
	@Autowired
	private Environment env;
	
    public static void main( String[] args )
    {	
    	
    	SpringApplication.run(App.class, args);
    }
    
    @Override
	public void customize(ConfigurableEmbeddedServletContainer container)
	{
		Ssl ssl = new Ssl();
		ssl.setKeyStore(env.getProperty("server.sslpath"));
		ssl.setKeyStorePassword(env.getProperty("server.sslpwd"));
		container.setSsl(ssl);
	}
}
