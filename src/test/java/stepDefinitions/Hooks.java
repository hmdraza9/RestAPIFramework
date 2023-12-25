package stepDefinitions;

import java.io.FileNotFoundException;

import io.cucumber.java.Before;
import rest.assured.utils.UtilMethods;

public class Hooks {

	StepDefinition objStepDef = new StepDefinition();

	UtilMethods util = new UtilMethods();

	@Before("@DeletePlacae")
	public void beforeScenario() throws FileNotFoundException {

		objStepDef.set_payload("Test Name", "123, Street" + UtilMethods.getTime(), "Grocery_Medicine_Snack", "English");
		objStepDef.user_call_with_request("AddPlaceAPI", "POST");

	}

}
