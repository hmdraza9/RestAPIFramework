/**
 * 
 */
package resources;

/**
 * @author hmdra_gnp5
 *
 */
public enum APIResources {

	AddPlaceAPI("maps/api/place/add/json"), DeletePlaceAPI("maps/api/place/delete/json"),
	GetPlaceAPI("maps/api/place/get/json");

	private String resource;

	APIResources(String resource) {
		this.resource = resource;
	}

	public String getResource() {
		return resource;
	}

}
