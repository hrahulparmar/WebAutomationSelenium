package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDDT extends BaseClass {

	@Test(dataProvider = "LoginData",dataProviderClass = DataProviders.class)
	public void loginDTT(String email,String password,String exp)
	{
		logger.info("***Starting TC_002_LoginTest***");
		try {
		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp= new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(password);
		lp.clickLogin();
		
		MyAccountPage ap= new MyAccountPage(driver);
		boolean tp = ap.isMyAccontPageExists();
		
		if(exp.equals("Valid"))
		{
			if(tp==true)
			{
				ap.clickLogout();
				Assert.assertTrue(true);
			}else
			{
				Assert.assertTrue(false);
			}
		}
		if(exp.equals("Invalid"))
		{
			if(tp==true)
			{
				ap.clickLogout();
				Assert.assertTrue(false);
			}else
			{
				Assert.assertTrue(true);
			}
		}
		}catch (Exception e) {
			// TODO: handle exception
			 Assert.fail();	
			 logger.info("test failed");
		}
		logger.info("***Finished TC_002_LoginTest***");
	}
}
