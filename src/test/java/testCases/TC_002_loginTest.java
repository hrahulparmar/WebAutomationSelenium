package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_loginTest extends BaseClass {

	@Test(groups = {"Sanity","Master"})
	public void testLogin()
	{
		logger.info("***Starting TC_002_LoginTest***");
		try {
		
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on my account link");
		hp.clickLogin();
		logger.info("Clicked on login link");
		LoginPage lp= new LoginPage(driver);
		lp.setEmail(rb.getString("email"));
		logger.info("entered email");
		lp.setPassword(rb.getString("password"));
		logger.info("entered password");
		lp.clickLogin();
		logger.info("clicked on login button");
		MyAccountPage ap= new MyAccountPage(driver);
		boolean flag = ap.isMyAccontPageExists();
		Assert.assertEquals(flag, true);
		logger.info("success logged in");
		
		}catch (Exception e) {
		 Assert.fail();	
		 logger.info("login failed");
		}
		logger.info("***Finished TC_002_LoginTest***");
	}
}
