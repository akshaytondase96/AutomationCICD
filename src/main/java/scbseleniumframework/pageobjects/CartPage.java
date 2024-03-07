package scbseleniumframework.pageobjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import scbseleniumframeworkdesign.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents
{
	WebDriver driver;
	@FindBy(css=".cartSection h3")
    private List<WebElement> productTitles;
    
    @FindBy(css=".totalRow button")
    WebElement checkoutele;
  
    public CartPage(WebDriver driver)
    {
    	super(driver);
	    this.driver=driver;
	    PageFactory.initElements(driver,this);
    }
  public Boolean verifyProductDisplay(String productName) throws InterruptedException
  {   
	  
		  Boolean m=productTitles.stream().anyMatch(cartItem->cartItem.getText().equalsIgnoreCase(productName));
		  return m;
  }
   public CheckOutPage goCheckOutPage() 
   {
	   checkoutele.click();   
	   return new CheckOutPage(driver);
   }
    
}
