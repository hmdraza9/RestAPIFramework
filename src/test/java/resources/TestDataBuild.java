package resources;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rest.assured.utils.UtilMethods;
import test.pojo.classes.AddPlace;
import test.pojo.classes.Location;

public class TestDataBuild {

	private static final Logger log = LogManager.getLogger(TestDataBuild.class);

	public AddPlace addPlaceBodySetUp(String name, String address, String types) {

		log.info("Add place body setup.");
		AddPlace ap = new AddPlace();
		Location apLoc = new Location();
		apLoc.setLat(32.98977F);
		apLoc.setLng(-32.98977F);
		ap.setLocation(apLoc);
		ap.setAccuracy("50");
		ap.setName(name);
		ap.setPhone_number("+965 123123");
		ap.setAddress(address + " - " + UtilMethods.getTime());
		List<String> typeList = Arrays.asList(types.split("_"));

//		for (String str : typeList) {
//			typeList.add(str);
//		}

		ap.setTypes(typeList);
		ap.setWebsite("www.google.com");
		ap.setLanguage("English-IN");

		return ap;
	}

}
