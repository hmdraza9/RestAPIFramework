package rest.assured.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.restassured.path.json.JsonPath;
import test.pojo.classes.AddPlace;
import test.pojo.classes.Location;

public class UtilMethods {

//	private static final Logger log = LogManager.getLogger(UtilMethods.class);

	public JsonPath rawToJson(String response) {

//		log.info("In " + (new Throwable().getStackTrace()[0].getMethodName()));

		JsonPath js1 = new JsonPath(response);

		return js1;

	}

	public Set<Integer> randBetween(int min, int max, int size) {

		int i = 0;

		Set<Integer> randSet = new HashSet<Integer>();

		while (randSet.size() < size) {

			i = (int) (Math.random() * 1000 / 1);
			if (i >= min && i <= max) {
				randSet.add(i);
//				System.out.println(i);
			}
//			System.out.println("randSet.size(): " + randSet.size());
		}
		return randSet;

	}

	public static String getTime() {

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss-SSS");
		return sdf.format(cal.getTime());
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
