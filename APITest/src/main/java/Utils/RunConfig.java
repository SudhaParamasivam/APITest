package Utils;

import java.io.File;

public class RunConfig {
	public static final String MOZILLA_DRIVER_EXE = System.getProperty("user.dir")+ File.separator+"driver"+ File.separator+"geckodriver.exe";
public static final String CHROME_DRIVER_EXE = System.getProperty("user.dir")+ File.separator+"driver"+File.separator+"chromedriver.exe";


public static String Test_ApplicationURL="https://www.accuweather.com/";
public static String Write_Sheet="TestData";
}
