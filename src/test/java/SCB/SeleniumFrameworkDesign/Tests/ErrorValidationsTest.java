package SCB.SeleniumFrameworkDesign.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import scbseleniumframework.pageobjects.CartPage;
import scbseleniumframework.pageobjects.ProductCatalog;
import scbseleniumframeworkdesign.TestComponenets.BaseTest;
import scbseleniumframeworkdesign.TestComponenets.Retry;

public class ErrorValidationsTest extends BaseTest
{
	   @Test(groups= {"ErrorHandling"})
	   public void loginErrorValidation() throws IOException, InterruptedException
	   {
       String productName="ZARA COAT 3";
   	   String countryName="India";
       landpage.loginPage("aks96@gmail.com", "Akshay@55572");
		
		  if(landpage.getErrorMessage().equals("Incorrect email or password.")) {
		  Assert.assertTrue(false); 
		  }
		 
    	   //Assert.assertEquals("Incorrect email or password.", landpage.getErrorMessage());
       
    }
	   @Test
	   public void productErrorValidation() throws InterruptedException
	   {
		   String productName="ZARA COAT 3";
	   	   String countryName="India";
	       ProductCatalog productCatalog=landpage.loginPage("avs@gmail.com", "Akshay5572");
	       List<WebElement>products=productCatalog.getProductList();
	       productCatalog.addProductToCart(productName);
	       CartPage cartpage= productCatalog.goToCartPage();
	       Boolean match=cartpage.verifyProductDisplay(productName);
	       Assert.assertTrue(match);
	   }
}
	
//  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[class*='action__submit']")));


