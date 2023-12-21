package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.Set;

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
import resources.TestDataBuild;
import rest.assured.utils.UtilMethods;
import rest.assured.utils.testDataPayloads;

public class StepDefinition extends UtilMethods {
	private static final Logger log = LogManager.getLogger(StepDefinition.class);

	public static String placeID;

	TestDataBuild payload = new TestDataBuild();

	private static Set<String> placeSet;

//	UtilMethods utils = new UtilMethods();

	RequestSpecification reqSpec;

	ResponseSpecification resspec;

	Response res;

	String responseBody;

	testDataPayloads data = new testDataPayloads();

	@Given("{string} Payload")
	public void payload(String typePayload) throws FileNotFoundException {

		log.info("Hell yeah!!");

		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if (typePayload.equalsIgnoreCase("addplace")) {

			reqSpec = given().spec(requestSpecification()).body(payload.addPlaceBodySetUp());

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

		placeID = rawToJson(responseBody).getString("place_id");

		System.out.println("\n\nPlace ID: " + placeID);

	}

}
