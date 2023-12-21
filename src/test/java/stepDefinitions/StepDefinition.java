package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import rest.assured.utils.UtilMethods;
import rest.assured.utils.testDataPayloads;
import test.pojo.classes.AddPlace;
import test.pojo.classes.Location;

public class StepDefinition {
	private static final Logger log = LogManager.getLogger(StepDefinition.class);

	public static final String baseURI = "https://rahulshettyacademy.com";

	public static final String mapKey = "qaclick123";

	public static String placeID;

	private static Set<String> placeSet;

	UtilMethods utils = new UtilMethods();

	RequestSpecification req;

	ValidatableResponse vResp;

	Response resp;

	testDataPayloads data = new testDataPayloads();

	@Given("{string} Payload")
	public void payload(String typePayload) {
		// Write code here that turns the phrase above into concrete actions

		RestAssured.baseURI = baseURI;

		req = new RequestSpecBuilder().setBaseUri(baseURI).addQueryParam("key", mapKey)
				.addHeader("Content-Type", "application/json").setUrlEncodingEnabled(false).build();

		RequestSpecification addPlaceReqSpec = given().spec(req).log().all();

		if (typePayload.equalsIgnoreCase("addplace")) {

			addPlaceReqSpec.body(addPlaceBodySetUp());

		}
	}

	@When("User call {string} with {string} request")
	public void user_call_with_request(String string, String string2) {

		resp = req.when().log().all().post(testDataPayloads.uriMapAddPlace);

	}

	@Then("the API call is success with status code {string}")
	public void the_api_call_is_success_with_status_code(String string) {

		vResp = resp.then().log().all().assertThat().statusCode(200);

	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String string, String string2) {

		vResp.assertThat().body(string, equalTo(string2));

	}

	public AddPlace addPlaceBodySetUp() {

//		log.info("Add place body setup.");
		AddPlace ap = new AddPlace();
		Location apLoc = new Location();
		apLoc.setLat(32.98977F);
		apLoc.setLng(-32.98977F);
		ap.setLocation(apLoc);
		ap.setAccuracy("50");
		ap.setName("Iqbal Villa");
		ap.setPhone_number("+965 123123");
		ap.setAddress("8-1-33, Toli Chowki");
		List<String> typeList = new ArrayList<>();
		typeList.add("Grocery");
		typeList.add("Dairy");
		typeList.add("Snack");
		ap.setTypes(typeList);
		ap.setWebsite("www.google.com");
		ap.setLanguage("English-IN");

		return ap;
	}

}
