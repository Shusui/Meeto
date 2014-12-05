package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class Config {
	InputStream in = null;
	Properties properties;
	
	public String getRmiAddress(){
		return properties.getProperty("rmiaddress");
	}
	
	public Config() {
		try {
			properties = new Properties();
			in = new FileInputStream("/../app.properties");
			properties.load(in);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
	 