package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) 
	{
		super(driver);
	}

	// elements
	@FindBy(xpath = "//span[text()='My Account']")
	WebElement lnkMyaccount;
	@FindBy(linkText = "Register")
	WebElement lnkRegister;
	@FindBy(xpath="//a[text()='Login']")
	WebElement lnkLogin;

	// action method
	public void clickMyAccount() {
		lnkMyaccount.click();
	}

	public void clickRegister() {
		lnkRegister.click();
	}
	
	public void clickLogin() {
	lnkLogin.click();
	}
}
