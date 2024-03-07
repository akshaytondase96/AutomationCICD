package scbseleniumframework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import scbseleniumframeworkdesign.AbstractComponents.AbstractComponents;

public class ProductCatalog extends AbstractComponents
{
	WebDriver driver;

   public ProductCatalog(WebDriver driver) 
    {
	   super(driver);
	   //initialization
	  this.driver=driver;
	  PageFactory.initElements(driver, this);
    }

   @FindBy(css=".mb-3")
   List<WebElement> products;

   By productsBy=By.cssSelector(".mb-3");
   By addToCart=By.cssSelector(".btn.w-10.rounded");
   By toastMessage=By.cssSelector("#toast-container");
   @FindBy(css=".ng-animating")
   WebElement spinner;
   
   
public List<WebElement> getProductList()
{	
    waitForElementToAppear(productsBy);
    return products;
}
public WebElement getProductByName(String productname)
{
	WebElement prod=getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
     return prod;
}
public void addProductToCart(String productName) throws InterruptedException
{
	WebElement prod=getProductByName(productName);
	prod.findElement(addToCart).click();	
	waitForElementToAppear(toastMessage);
	waitForElementToDisappear(spinner);
}
}
