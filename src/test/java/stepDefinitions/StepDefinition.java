package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import rest.assured.utils.UtilMethods;
import rest.assured.utils.testDataPayloads;

public class StepDefinition extends UtilMethods {
	private static final Logger log = LogManager.getLogger(StepDefinition.class);

	public static String placeID;

	TestDataBuild payload = new TestDataBuild();

	RequestSpecification reqSpec;

	ResponseSpecification resspec;

	Response res;

	String responseBody;

	testDataPayloads data = new testDataPayloads();

	@Given("Add Place Payload with {string} {string} {string}")
	public void payload(String name, String address, String types) throws FileNotFoundException {

		log.info("Setting up Pay Load");

		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		reqSpec = given().spec(requestSpecification()).body(payload.addPlaceBodySetUp(name, address, types));

	}

	@When("User call {string} with {string} request")
	public void user_call_with_request(String resource, String method) {
		APIResources resourceAPI = APIResources.valueOf(resource);

		log.info("Capturing response");

		if (method.equalsIgnoreCase("GET")) {
			// using property file
			// res =
			// reqSpec.when().log().all().post(UtilMethods.readPropFile("uriMapAddPlace")).then().spec(resspec).extract().response();
			// using Enum
			res = reqSpec.when().log().all().get(resourceAPI.getResource()).then().spec(resspec).extract().response();
		} else if (method.equalsIgnoreCase("POST")) {
			res = reqSpec.when().log().all().post(resourceAPI.getResource()).then().spec(resspec).extract().response();
		} else
			res = reqSpec.when().log().all().delete(resourceAPI.getResource()).then().spec(resspec).extract()
					.response();

	}

	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer statCode) {

		log.info("Validating if API Call status is OK");

		assertTrue(res.getStatusCode() == statCode);
		responseBody = res.asString();
		log.info("Response Body: " + responseBody);

	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {

		log.info("Validating response body value");

		res.then().log().all().assertThat().body(keyValue, equalTo(expectedValue));

		placeID = rawToJson(responseBody).getString("place_id");

		log.info("Place ID: " + placeID);

	}

	@Then("verify data {string} with API {string}")
	public void verify_data_with_api(String expected, String resource) {
		
		log.info("Verifying the data");
	}

}
