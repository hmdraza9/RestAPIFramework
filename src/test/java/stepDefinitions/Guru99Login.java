package stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.test.pages.Guru99LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import rest.assured.utils.UtilMethods;

public class Guru99Login extends UtilMethods {
	private static final Logger log = LogManager.getLogger(Guru99Login.class);

	Guru99LoginPage objGuru99LoginPage = new Guru99LoginPage();

	@Given("User set up the browser")
	public void User_set_up_the_browser() {
		log.info(getMethodName());
		objGuru99LoginPage.browserSetup();
	}

	@Given("User login with {string} and {string}")
	public void user_Logs_in(String username, String password) {
		log.info(getMethodName());
		objGuru99LoginPage.login(username, password);
	}

	@Given("user verifies the login success {string}")
	public void user_verifies_the_login_success(String label) {
		log.info(getMethodName());
		objGuru99LoginPage.verifyLoginHomeLabel(label);
	}

	@Then("user logout the application")
	public void user_logout_the_application() {
		log.info(getMethodName());
		objGuru99LoginPage.logout();
	}

}
