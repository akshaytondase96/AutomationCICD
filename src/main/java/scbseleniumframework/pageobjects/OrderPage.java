package scbseleniumframework.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import scbseleniumframeworkdesign.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents
{
	WebDriver driver;
	@FindBy(css=".cartSection h3")
    private List<WebElement> productTitles;
    
    @FindBy(css="tr td:nth-child(3)")
    private List<WebElement> productNames;
  
    public OrderPage(WebDriver driver)
    {
    	super(driver);
	    this.driver=driver;
	    PageFactory.initElements(driver,this);
    }
  public Boolean verifyOrderDisplay(String productName)
  {
	  Boolean m=productNames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
	  return m;
  }
   
    
}
