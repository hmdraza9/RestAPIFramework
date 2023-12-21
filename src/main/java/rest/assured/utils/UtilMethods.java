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

	private static final Logger log = LogManager.getLogger(UtilMethods.class);

	RequestSpecification req;

	public static final String path = System.getProperty("user.dir") + "\\src\\test\\resources\\";

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

	public RequestSpecification requestSpecification() throws FileNotFoundException {

		RestAssured.baseURI = readPropFile("baseURL");

		if (req == null) {
			PrintStream reqStream = new PrintStream(
					new FileOutputStream("logs/HTTPLogs/requestLogger_" + getTime() + ".log"));
			PrintStream resStream = new PrintStream(
					new FileOutputStream("logs/HTTPLogs/responseLogger_" + getTime() + ".log"));
			req = new RequestSpecBuilder().setBaseUri(readPropFile("baseURL"))
					.addQueryParam("key", readPropFile("mapKey"))
					.addFilter(RequestLoggingFilter.logRequestTo(reqStream))
					.addFilter(ResponseLoggingFilter.logResponseTo(resStream)).setContentType(ContentType.JSON)
					.setUrlEncodingEnabled(false).build();
			return req;
		}
		return req;
	}

	public static String readPropFile(String key) {
		File file = new File(path + "config.properties");
		String value = "";

		Properties pr = new Properties();
		try {
			pr.load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		value = pr.getProperty(key);
		log.info("Value read from property file: " + value);

		return value;
	}

}
