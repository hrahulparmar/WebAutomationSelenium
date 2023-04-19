package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	
	public Logger logger; //logging
	
	public ResourceBundle rb;
	
	@BeforeClass(groups= {"Master","Sanity","Regression"})
	@Parameters("browser")
	public void setup(String br)
	{
		rb= ResourceBundle.getBundle("config"); //load properties file
		
		logger= LogManager.getLogger(this.getClass());// logging using current class object 
		
		ChromeOptions option= new ChromeOptions();
		option.setExperimentalOption("excludeSwitches",new String[] {"enable-automation"});
		if(br.equals("chrome"))
		{
		//WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver(option);
		logger.info("opening chrome browser");
		}else if(br.equals("edge"))
		{
			driver= new EdgeDriver();
			logger.info("opening edge browser");
		}else
		{
			driver= new ChromeDriver();
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//reading from properties file
		driver.get(rb.getString("appURL"));
		
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups= {"Master","Sanity","Regression"})
	public void tearDown()
	{
		driver.close();
	}
	public String randomString()
	{
		String generatedString= RandomStringUtils.randomAlphabetic(4);
		return generatedString;
	}
	public String randomNumber()
	{
		String generatedString2= RandomStringUtils.randomNumeric(10);
		return generatedString2;
	}
	public String randomAlphaNumberic()
	{
		String generatedString3= RandomStringUtils.randomAlphabetic(4);
		String num= RandomStringUtils.randomNumeric(3);
		return (generatedString3+"@"+num);
	}

	public String captureScreen(String tname) throws IOException{
		
		String timeStamp= new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts= (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		
		return destination ;
		
	}
	
}
