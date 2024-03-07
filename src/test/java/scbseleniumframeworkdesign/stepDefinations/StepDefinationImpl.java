package scbseleniumframeworkdesign.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import scbseleniumframework.pageobjects.CartPage;
import scbseleniumframework.pageobjects.CheckOutPage;
import scbseleniumframework.pageobjects.ConfirmPage;
import scbseleniumframework.pageobjects.LandingPage;
import scbseleniumframework.pageobjects.ProductCatalog;
import scbseleniumframeworkdesign.TestComponenets.BaseTest;

public class StepDefinationImpl extends BaseTest
{
  public LandingPage landpage;
  public ProductCatalog productCatalog;
  public CartPage cartpage;
  public CheckOutPage checkoutpage;
  public ConfirmPage confirmpage;
  @Given("I landed on E-commerce page.")
  public void i_landed_on_Ecommerce_page() throws IOException
  {
	  landpage=launchApplication();
 }
   @Given("^Logged with username (.+) and password (.+)$")
   public void logged_with_username_and_password(String username,String password)
   {
	    productCatalog=landpage.loginPage(username,password);
   }
   @When("^Add the product (.+) to cart.$")
    public void add_the_product_to_cart(String productName) throws InterruptedException
    {
	   List<WebElement>products=productCatalog.getProductList();
       productCatalog.addProductToCart(productName);
    }
 @And("^Check out (.+) and submit the order$")
   public void check_out_productname_and_submit_order(String productName) throws InterruptedException
   {
	  cartpage= productCatalog.goToCartPage();
	  System.out.println(productName);
	  Boolean m=cartpage.verifyProductDisplay(productName);
		
		   Assert.assertTrue(m);
		 
      checkoutpage=cartpage.goCheckOutPage();
      checkoutpage.selectCountry("India");
       confirmpage=checkoutpage.submitted();
   }
 @Then("{string} msg is displayed on confirmation page")
 public void message_displayed_confirmationPage(String string)
 {
	 String confirmMessage= confirmpage.confirmation();
     Assert.assertEquals(confirmMessage,string);
 }
 @Then("{string} message is displayed.")
	public void message_displayed(String string) throws InterruptedException
	{
		if(landpage.getErrorMessage().equals(string)) {
			  Assert.assertTrue(false); 
			  }
	}
}
