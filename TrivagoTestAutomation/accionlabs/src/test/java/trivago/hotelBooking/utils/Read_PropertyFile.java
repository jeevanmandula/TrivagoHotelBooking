package trivago.hotelBooking.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Read_PropertyFile {
	public static Properties p;	
	public static void readproperties(String file)
	{		
		try {
			FileInputStream fis = new FileInputStream("./DataFiles/"+file+".properties");
			 p=new Properties();
			 p.load(fis);
		} catch (IOException e) {
			System.out.println("Property file not found");
		}
	}
	
    public static String ReadFile(String filename,String key)		
		{
			readproperties(filename);
			String element=p.getProperty(key);
			return element;
		}		

}
