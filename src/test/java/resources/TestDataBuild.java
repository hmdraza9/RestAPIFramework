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

	public AddPlace addPlaceBodySetUp(String name, String address, String types, String language) {

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

		ap.setTypes(typeList);
		ap.setWebsite("www.google.com");
		ap.setLanguage(language);

		return ap;
	}

	public String deletePlacePayload(String placeID) {
		return "{\"place_id\":\"" + placeID + "\"}";
	}

	public String graphQLPayload() {
		return "{\"query\":\"query($charId:Int!, $epiId:Int!,$locId:Int!,$charName:String!)\\n{\\n  character(characterId:$charId) {\\n    name\\n    gender\\n    id\\n    status\\n  }\\n  location(locationId: $locId) {\\n    name\\n    dimension\\n  }\\n  episode(episodeId: $epiId) {\\n    name\\n    air_date\\n    episode\\n  }\\n  characters(filters: {name: $charName}) {\\n    info {\\n      count\\n    }\\n    result {\\n      id\\n      name\\n      type\\n      status\\n      species\\n    }\\n  }\\n  episodes(filters: {name: \\\"tom and jerry\\\"}) {\\n    info {\\n      count\\n    }\\n    result {\\n      id\\n      name\\n      air_date\\n      episode\\n      created\\n    }\\n  }\\n}\\n\",\"variables\":{\"charId\":4545,\"epiId\":3733,\"locId\":5076,\"charName\":\"Aladdin\"}}";
	}

}
