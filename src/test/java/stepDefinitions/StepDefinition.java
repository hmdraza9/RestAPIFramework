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

	UtilMethods util = new UtilMethods();

	TestDataBuild payload = new TestDataBuild();

	RequestSpecification reqSpec;

	ResponseSpecification resspec;

	Response res;

	String responseBody;

	APIResources resourceAPI;

	testDataPayloads data = new testDataPayloads();

	@Given("Add Place Payload with {string} {string} {string} {string}")
	public void payload(String name, String address, String types, String language) throws FileNotFoundException {
		log.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		log.info("Setting up Pay Load");

		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		reqSpec = given().spec(requestSpecification()).body(payload.addPlaceBodySetUp(name, address, types, language));

	}

	@When("User call {string} with {string} request")
	public void user_call_with_request(String resource, String method) {
		log.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		resourceAPI = APIResources.valueOf(resource);

		log.info("Calling API: " + resource + "; HTTP Method: " + method);

		if (method.equalsIgnoreCase("GET")) {
			// using property file
			// res =
			// reqSpec.when().log().all().post(UtilMethods.readPropFile("uriMapAddPlace")).then().spec(resspec).extract().response();
			// using Enum
			res = reqSpec.when().log().all().get(resourceAPI.getResource()).then().spec(resspec).extract().response();
		} else if (method.equalsIgnoreCase("POST")) {
			res = reqSpec.when().log().all().post(resourceAPI.getResource()).then().spec(resspec).extract().response();

			if (resource.equals("AddPlaceAPI")) {
				placeID = util.rawToJson(res.asString()).getString("place_id");
				log.info("resourceAPI.getResource(): " + resource);
			}
		} else if (method.equalsIgnoreCase("DELETE"))
			res = reqSpec.when().log().all().delete(resourceAPI.getResource()).then().spec(resspec).extract()
					.response();
	}

	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer statCode) {
		log.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		log.info("Validating if API Call status is OK");

		assertTrue(res.getStatusCode() == statCode);
		responseBody = res.asString();
		log.info("Response Body: " + responseBody);

	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
		log.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		log.info("Validating response body value");

		res.then().log().all().assertThat().body(keyValue, equalTo(expectedValue));

		log.info("keyValue: " + keyValue);

		log.info("expectedValue: " + expectedValue);

	}

	@When("verify data {string} for string {string} with {string}")
	public void verify_data_for_string(String expectedValue, String jPathString, String resource)

			throws FileNotFoundException {
		log.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		log.info("In verify data step");

		resourceAPI = APIResources.valueOf("GetPlaceAPI");

		reqSpec = given().spec(requestSpecification()).queryParam("place_id", placeID);

		user_call_with_request(resource, "GET");

		String actualValue = util.rawToJson(res.asString()).getString(jPathString);

		log.info("\nExpected: " + expectedValue + "\nActual  : " + actualValue);

		assertTrue(actualValue.contains(expectedValue));

	}

	@Given("deletePlace Payload")
	public void delete_place_payload() throws FileNotFoundException {
		log.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		log.info("In delete Place Payload step");

		reqSpec = given().spec(requestSpecification()).body(payload.deletePlacePayload(placeID));

		log.info("Deletion response: " + res.asString());

	}
}
