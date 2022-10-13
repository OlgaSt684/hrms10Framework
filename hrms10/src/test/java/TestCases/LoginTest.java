package TestCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.DashBoardPage;
import pages.LoginPage;
import utils.CommonMethods;
import utils.configReader;

public class LoginTest extends CommonMethods {

	// US 12345 User should be able to login via valid credentials

	@Test (groups="regression")
	public void adminLogin() {

		LoginPage login = new LoginPage(); // create an object of a Constractor from LoginPage class

		sendText(login.usernameBox, configReader.getPropertyValue("username"));
		sendText(login.passwordBox, configReader.getPropertyValue("password"));
		click(login.loginBtn);

		// assertion
		DashBoardPage dashboardPage = new DashBoardPage();
		Assert.assertTrue(dashboardPage.welcomeMessage.isDisplayed());

	}

	// US 34342 System should display error message when invalid login is performed
	// 1 when user enter valid/invalid username and valid/invalid password then display "Invalid credentials"
	// 2 when user leaves password field wmpty then display "Password cannot be empty"
	// 3 when user leaves username field empty then display "Username cannot be empty"

	@DataProvider
	public Object[][] invalidData() {
		Object[][] data = { 
				{ "James", "123!", "Invalid credentials" },
				{ "Admin1", "Hum@nhrm123", "Invalid credentials" }, 
				{ "Admin", "", "Password cannot be empty" },
				{  "", "Hum@nhrm123", "Username cannot be empty" } 
				};
		return data;
	}
	
	@Test (dataProvider = "invalidData")
	public void invalidLoginErrorMessageValidation(String username, String password, String message) {
		LoginPage loginPage=new LoginPage();
		loginPage.login(username, password);
		
		String actualError = loginPage.errorMessage.getText();
		Assert.assertEquals(actualError, message, "Error message does not match");
		
		
				
	
	}
	
	

}
