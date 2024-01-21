package stepDefinitions;

import com.test.pages.Guru99LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import rest.assured.utils.UtilMethods;

public class Guru99Login extends UtilMethods {
//	private static final Logger log = LogManager.getLogger(Guru99Login.class);

	Guru99LoginPage objGuru99LoginPage = new Guru99LoginPage();

	@Given("User set up the browser")
	public void User_set_up_the_browser() {
		objGuru99LoginPage.browserSetup();
	}

	@Given("User login with {string} and {string}")
	public void user_Logs_in(String username, String password) {
		objGuru99LoginPage.login(username, password);
	}

	@Given("user verifies the login success {string}")
	public void user_verifies_the_login_success(String label) {
		objGuru99LoginPage.verifyLoginHomeLabel(label);
	}

	@Then("user logout the application")
	public void user_logout_the_application() {
		objGuru99LoginPage.logout();
	}

}
