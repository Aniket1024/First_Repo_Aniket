package UtilityPackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperty 
{
     public static String getPropertyData(String propToBeRead) throws IOException
     {
    	 String filePath= System.getProperty("user.dir") + "\\src\\test\\resources\\Config.properties";
    	
    	 
    	 FileInputStream fis = new FileInputStream(filePath);
    	 Properties prop= new Properties();
    	 prop.load(fis);
    	 String data=prop.getProperty(propToBeRead);
    	
    	 return data;
     }
     
    
    
}
