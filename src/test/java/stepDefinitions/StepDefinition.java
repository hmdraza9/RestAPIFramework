package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;

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
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
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

	RequestSpecification reqSpec;

	ResponseSpecification resspec;

	Response res;

	String responseBody;

	testDataPayloads data = new testDataPayloads();

	@Given("{string} Payload")
	public void payload(String typePayload) {
		// Write code here that turns the phrase above into concrete actions

		RestAssured.baseURI = baseURI;

		req = new RequestSpecBuilder().setBaseUri(baseURI).addQueryParam("key", mapKey)
				.addHeader("Content-Type", "application/json").setUrlEncodingEnabled(false).build();

		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if (typePayload.equalsIgnoreCase("addplace")) {

			reqSpec = given().spec(req).body(addPlaceBodySetUp());

		}
	}

	@When("User call {string} with {string} request")
	public void user_call_with_request(String string, String string2) {

		res = reqSpec.when().log().all().post(testDataPayloads.uriMapAddPlace).then().spec(resspec).extract()
				.response();

	}

	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer statCode) {

		assertTrue(res.getStatusCode() == statCode);
		responseBody = res.asString();
		System.out.println(responseBody);

	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {

		res.then().log().all().assertThat().body(keyValue, equalTo(expectedValue));

		placeID = utils.rawToJson(responseBody).getString("place_id");

		System.out.println("\n\nPlace ID: " + placeID);

	}

	public AddPlace addPlaceBodySetUp() {

		System.out.println("Add place body setup.");
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
