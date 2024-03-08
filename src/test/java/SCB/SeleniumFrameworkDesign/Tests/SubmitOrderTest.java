package SCB.SeleniumFrameworkDesign.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import scbseleniumframework.pageobjects.CartPage;
import scbseleniumframework.pageobjects.CheckOutPage;
import scbseleniumframework.pageobjects.ConfirmPage;
import scbseleniumframework.pageobjects.LandingPage;
import scbseleniumframework.pageobjects.OrderPage;
import scbseleniumframework.pageobjects.ProductCatalog;
import scbseleniumframeworkdesign.TestComponenets.BaseTest;
//Updated in submit order.
public class SubmitOrderTest extends BaseTest
{
    String productName="ZARA COAT 3";

	   @Test(dataProvider="getData",groups= {"purchase"})
	   public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	   {
       
   	   String countryName="India";
       ProductCatalog productCatalog=landpage.loginPage(input.get("email"), input.get("password"));
       List<WebElement>products=productCatalog.getProductList();
       productCatalog.addProductToCart(input.get("product"));
       CartPage cartpage= productCatalog.goToCartPage();
       Boolean match=cartpage.verifyProductDisplay(input.get("product"));
       Assert.assertTrue(match);
       CheckOutPage checkoutpage=cartpage.goCheckOutPage();
       checkoutpage.selectCountry(countryName);
       ConfirmPage confirmpage=checkoutpage.submitted();
       String confirmMessage= confirmpage.confirmation();
      Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");
	   }
	  
	   @Test(dependsOnMethods= {"submitOrder"})
	   public void orderHistory()
	   {
	       ProductCatalog productCatalog=landpage.loginPage("aks96@gmail.com", "Akshay@5572");
	       OrderPage oderpage=productCatalog.goToOrderPage();	       
	       Assert.assertTrue(oderpage.verifyOrderDisplay(productName));
	   }
    
	   
  @DataProvider
   public Object[][] getData() throws IOException
   {
		
	  List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\scbseleniumframeworkdesign\\data\\PurchaseOrder.json");
	  return new Object[][]  {{data.get(0)},{data.get(1)}};

   }
	/*
	 * @DataProvider public Object[][] getData() { return new Object[][]
	 * {{"aks@gmail.com","Akshay@5572","ZARA COAT 3"},{"avs@gmail.com","Akshay5572",
	 * "ADIDAS"}}; }
	 */
  /*
	 * HashMap<String,String> map=new HashMap<String,String>();
	 * map.put("email","aks96@gmail.com"); map.put("password","Akshay@5572");
	 * map.put("product","ZARA COAT 3");
	 * 
	 * HashMap<String,String> map1=new HashMap<String,String>();
	 * map1.put("email","avs@gmail.com"); map1.put("password","Akshay5572");
	 * map1.put("product","ADIDAS ORIGINAL");
	 */
}
	
//  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[class*='action__submit']")));



