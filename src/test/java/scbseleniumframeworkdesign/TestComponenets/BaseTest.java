package scbseleniumframeworkdesign.TestComponenets;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import scbseleniumframework.pageobjects.LandingPage;

public class BaseTest 
{
	public WebDriver driver;
	public LandingPage landpage ;
   public WebDriver initializeDriver() throws IOException
   {
	   //properties class
	   Properties prop=new Properties();
	   FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\scbseleniumframeworkdesign\\resources\\GlobalData.properties");
	   prop.load(fis);
	   String browserName= System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
      if(browserName.contains("chrome"))
	   {
    	  ChromeOptions options=new ChromeOptions();
	   WebDriverManager.chromedriver().setup();
	   if(browserName.contains("headless"))
			   {
	   options.addArguments("headless");
			   }
        driver=new ChromeDriver(options);
	   }
	   else if(browserName.equals("edge"))
	   {
		   System.setProperty("webdriver.edge.driver", "C:\\Users\\hp\\Downloads\\edgedriver_win64\\msedgedriver.exe");
		    driver=new EdgeDriver();
	   }
	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
       driver.manage().window().maximize();
       return driver;
   }
   public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
   {
	   //reading json to string
	  String jsonContent= FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\scbseleniumframeworkdesign\\data\\PurchaseOrder.json"),StandardCharsets.UTF_8);
      //string to Hashmap Jackson Databind
	  ObjectMapper obj=new ObjectMapper();
	  List<HashMap<String,String>> data=obj.readValue(jsonContent,new TypeReference<List<HashMap<String,String>>>(){});
      return data;
   }
   public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
   {
 	  TakesScreenshot ts= (TakesScreenshot)driver;
 	 File src= ts.getScreenshotAs(OutputType.FILE);
 	 FileUtils.copyFile(src, new File("D:\\Akshay\\Screenshot\\failScreenshot.png"));
      return "D:\\Akshay\\Screenshot\\failScreenshot.png";
   }
   @BeforeMethod(alwaysRun=true)
   public LandingPage launchApplication() throws IOException
   {
	   driver=initializeDriver(); 
	   landpage =new LandingPage(driver);
       landpage.goTo();
       return landpage;
   }
  // @AfterMethod(alwaysRun=true)
	/*
	 * public void tearDown() { driver.close();
	 * 
	 * }
	 */
}
