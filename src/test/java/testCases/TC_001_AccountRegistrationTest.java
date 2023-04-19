package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegisterationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {

	
	@Test(groups = {"Regression","Master"})
	public void test_account_Registration() 
	{
		logger.debug("***application logs***");
		logger.info("***Starting TC_001_AccountRegistrationTest***");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on my account link");
			hp.clickRegister();
			logger.info("Click on register link");
			AccountRegisterationPage rp = new AccountRegisterationPage(driver);
			logger.info("Providing customer data");
			rp.setFirstName(randomString().toUpperCase());
			rp.setLastName(randomString().toUpperCase());
			rp.setEmail(randomString() + "@gmail.com");
			rp.setTelephone(randomNumber());
			String pass = randomAlphaNumberic();
			rp.setPassword(pass);
			rp.setConfirmPassword(pass);
			rp.setPrivacyPolicy();
			rp.clickContinue();
			logger.info("click on continue");
			String msg = rp.getConfirmationMsg();
			logger.info("Validating expected message");
			Assert.assertEquals(msg, "Your Account Has Been Created!","not getting expected message test failed!");
		} catch (Exception e) 
		{
			logger.error("test failed");
			Assert.fail();
		}
		logger.info("***Finished TC_001_AccountRegistrationtest***");
	}

}
