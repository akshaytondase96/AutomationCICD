package scbseleniumframework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import scbseleniumframeworkdesign.AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents
{
	WebDriver driver;

 public CheckOutPage(WebDriver driver) 
 {
	 super(driver);
    this.driver=driver;
    PageFactory.initElements(driver,this);
    }
@FindBy(xpath="//input[@placeholder='Select Country']")
WebElement country;
 
@FindBy(xpath="//span[text()=' India']")
 WebElement selectcountry;

@FindBy(css=".btnn.action__submit.ng-star-inserted")
WebElement submit;
@FindBy(css="a[class*='action__submit")
WebElement befsum;
By countryselect=By.cssSelector(".ta-results");

public void selectCountry(String countryName)
{
    Actions a=new Actions(driver);
    a.sendKeys(country,countryName).build().perform();
	waitForElementToAppear(countryselect);
	selectcountry.click();
}
public ConfirmPage submitted() throws InterruptedException
{
	JavascriptExecutor js=(JavascriptExecutor)driver;
    js.executeScript("window.scrollBy(0,730)");
    waitForWebElementToAppear(befsum);
    submit.click();
    return new ConfirmPage(driver);
    
}
}