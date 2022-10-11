package TestCases;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.CommonMethods;
import utils.configReader;

public class LoginTest extends CommonMethods {

	@Test
	public void adminLogin() {

		LoginPage login = new LoginPage(); // create an object of a Constractor from LoginPage class

		sendText(login.usernameBox, configReader.getPropertyValue("username"));
		sendText(login.passwordBox, configReader.getPropertyValue("password"));
		click(login.loginBtn);

	}

}
