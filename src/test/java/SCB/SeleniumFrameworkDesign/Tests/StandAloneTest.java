package SCB.SeleniumFrameworkDesign.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import scbseleniumframework.pageobjects.LandingPage;

public class StandAloneTest 
{

	public static void main(String[] args) throws InterruptedException 
	{
       WebDriverManager.chromedriver().setup();
       WebDriver driver=new ChromeDriver();
   	    String productName="ZARA COAT 3";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
       driver.get("https://rahulshettyacademy.com/client/");
       LandingPage landpage=new LandingPage(driver);
       driver.findElement(By.id("userEmail")).sendKeys("aks96@gmail.com");
       driver.findElement(By.id("userPassword")).sendKeys("Akshay@5572");
       driver.findElement(By.id("login")).click();
	   WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
	   List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
       WebElement prod=products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
        prod.findElement(By.cssSelector(".btn.w-10.rounded")).click();
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	   wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
       driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
       List<WebElement> cartItems =driver.findElements(By.cssSelector(".cartSection h3"));
       Boolean m=cartItems.stream().anyMatch(cartItem->cartItem.getText().equalsIgnoreCase(productName));
      Assert.assertTrue(m);
      driver.findElement(By.cssSelector(".totalRow button")).click();
      Actions a=new Actions(driver);
      a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")),"india").build().perform();
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
      driver.findElement(By.xpath("//span[text()=' India']")).click();
      
      JavascriptExecutor js=(JavascriptExecutor)driver;
     
      js.executeScript("window.scrollBy(0,700)");
      
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[class*='action__submit']")));
      driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
     String msg=driver.findElement(By.cssSelector(".hero-primary")).getText();
     Assert.assertEquals(msg, "THANKYOU FOR THE ORDER.");
     driver.close();
}
}
	


