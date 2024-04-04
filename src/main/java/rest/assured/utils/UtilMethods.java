package rest.assured.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class UtilMethods {

	static String path = System.getProperty("user.dir");

	private static final Logger log = LogManager.getLogger(UtilMethods.class);

	RequestSpecification req;

//	create logic
//	to toggle
//	path value
//	wrt OS name
	/*
	 * 
	 * public static final String path = System.getProperty("user.dir") +
	 * "/src/test/resources/";
	 * 
	 * 
	 * public static final String path = System.getProperty("user.dir") +
	 * "\\src\\test\\resources\\";
	 */

	public JsonPath rawToJson(String response) {

		JsonPath js1 = new JsonPath(response);

		return js1;

	}

	public static String getTime() {

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss-SSS");
		return sdf.format(cal.getTime());
	}

//	public AddPlace addPlaceBodySetUp() {
//
////		log.info("Add place body setup.");
//		AddPlace ap = new AddPlace();
//		Location apLoc = new Location();
//		apLoc.setLat(32.98977F);
//		apLoc.setLng(-32.98977F);
//		ap.setLocation(apLoc);
//		ap.setAccuracy("50");
//		ap.setName("Iqbal Villa");
//		ap.setPhone_number("+965 123123");
//		ap.setAddress("8-1-33, Toli Chowki "+getTime());
//		System.out.println("ap.getAddress(): "+ap.getAddress());
//		List<String> typeList = new ArrayList<>();
//		typeList.add("Grocery");
//		typeList.add("Dairy");
//		typeList.add("Snack");
//		ap.setTypes(typeList);
//		ap.setWebsite("www.google.com");
//		ap.setLanguage("English-IN");
//
//		return ap;
//	}

	public static String getMethodName() {
		return Thread.currentThread().getStackTrace()[1].getMethodName();
	}

	public RequestSpecification requestSpecificationMap(String apiType) throws FileNotFoundException {

		RestAssured.baseURI = readPropFile("baseURL");

		if (req == null) {
			PrintStream reqStream = new PrintStream(
					new FileOutputStream("logs/HTTPLogs/requestLogger_" + getTime() + ".log"));
			PrintStream resStream = new PrintStream(
					new FileOutputStream("logs/HTTPLogs/responseLogger_" + getTime() + ".log"));
			switch (apiType.toLowerCase()) {
			case "graphql":
				req = new RequestSpecBuilder().setBaseUri(readPropFile("baseURL"))
						.addFilter(RequestLoggingFilter.logRequestTo(reqStream))
						.addFilter(ResponseLoggingFilter.logResponseTo(resStream)).setContentType(ContentType.JSON)
						.setUrlEncodingEnabled(false).build();
				break;
			case "map":
				req = new RequestSpecBuilder().setBaseUri(readPropFile("baseURL"))
						.addQueryParam("key", readPropFile("mapKey"))
						.addFilter(RequestLoggingFilter.logRequestTo(reqStream))
						.addFilter(ResponseLoggingFilter.logResponseTo(resStream)).setContentType(ContentType.JSON)
						.setUrlEncodingEnabled(false).build();
			}
			return req;
		}
		return req;
	}

	public static String readPropFile(String key) {

		File file;

		if (System.getProperty("os.name").toLowerCase().contains("Mac".toLowerCase())) {
//			path = ;
			file = new File(path + "/src/test/resources/" + "config.properties");
		} else
			file = new File(path + "\\src\\test\\resources\\" + "config.properties");

		String value = "";

		Properties pr = new Properties();
		try {
			pr.load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		value = pr.getProperty(key);

		return value;
	}

}
