package TestCases;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.testng.annotations.Test;
import pages.AddEmployePage;
import pages.DashBoardPage;
import pages.LoginPage;
import utils.CommonMethods;
import utils.Constants;
import utils.ExcelReading;
import utils.configReader;

public class AddEmployeTest extends CommonMethods {

	// US 897892 Admin user should be able to add an employee to the app

	@Test(groups = "smoke")
	public void addEmployee() {
		// login function
		LoginPage loginpage = new LoginPage();
		loginpage.login(configReader.getPropertyValue("username"), configReader.getPropertyValue("password"));

		DashBoardPage dashboardPage = new DashBoardPage();
		click(dashboardPage.pimOption);
		click(dashboardPage.addEmployeButton);

		// add new employee
		AddEmployePage addEmployeePage = new AddEmployePage();
		sendText(addEmployeePage.firstName, "Test100");
		sendText(addEmployeePage.middleName, "Test110");
		sendText(addEmployeePage.lastName, "Test120Test");
		click(addEmployeePage.saveBtn);

	}
@Test
	public void addMultipleEmployees() {
		// login
		LoginPage loginPage = new LoginPage();
		loginPage.login(configReader.getPropertyValue("username"), configReader.getPropertyValue("password"));
        //navigate to add employee page
		DashBoardPage dashboardPage = new DashBoardPage();
		AddEmployePage addEmployeePage = new AddEmployePage();
		
		
		List<Map<String, String>> newEmployes=ExcelReading.excelIntoListMap(Constants.TESTDATA_FILEPATH,"EmployeeData");

		Iterator <Map<String, String>> it = newEmployes.iterator();
		while(it.hasNext()) {
			click(dashboardPage.pimOption);
			click(dashboardPage.addEmployeButton);
			Map<String, String> mapNewEmployee=it.next();
			sendText(addEmployeePage.firstName, mapNewEmployee.get("FirstName"));
			sendText(addEmployeePage.middleName, mapNewEmployee.get("MiddleName"));
			sendText(addEmployeePage.lastName, mapNewEmployee.get("LastName"));
			
			sendText(addEmployeePage.photograph, mapNewEmployee.get("Photograph"));
		    // select checkBox
			if (!addEmployeePage.createLoginCheckBox.isSelected()) {
				click(addEmployeePage.createLoginCheckBox);
			}
			
			// provide credentials for user
			sendText(addEmployeePage.createUsername, mapNewEmployee.get("Username"));
			sendText(addEmployeePage.createPassword, mapNewEmployee.get("Password"));
			sendText(addEmployeePage.rePassword, mapNewEmployee.get("Password"));
			
			click(addEmployeePage.saveBtn);
			
		}
	}

}
