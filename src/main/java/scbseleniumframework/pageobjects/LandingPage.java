
package scbseleniumframework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import scbseleniumframeworkdesign.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents
{
	WebDriver driver;

   public LandingPage(WebDriver driver) 
    {
	   super(driver);
	   //initialization
	  this.driver=driver;
	  PageFactory.initElements(driver, this);
    }
//WebElement userEmail=driver.findElement(By.id("userEmail"));
//PageFactory
 @FindBy(id="userEmail")
  WebElement userEmail;

 @FindBy(id="userPassword")
 WebElement passwordEle;
 
 @FindBy(id="login")
 WebElement submit;
 
 @FindBy(css="div[class*='flyInOut']")
 WebElement errorMessage;
 

 public ProductCatalog loginPage(String email,String password)
 {
	 userEmail.sendKeys(email);
	 passwordEle.sendKeys(password);
	 submit.click();
     ProductCatalog productCatalog=new ProductCatalog(driver);
      return productCatalog;
 }
 public String getErrorMessage() throws InterruptedException
 {
	 waitForWebElementToAppear(errorMessage);
	System.out.println(errorMessage.getText());
	return errorMessage.getText();
 }
 public void goTo()
 {
     driver.get("https://rahulshettyacademy.com/client/");
}
}
//.ng-tns-c4-21.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-success