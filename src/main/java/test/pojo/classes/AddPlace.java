package test.pojo.classes;

import java.util.List;

public class AddPlace {
	Location LocationObject;
	Nicknames NicknameObject;
	private String accuracy;
	private String name;
	private String phone_number;
	private String address;
	private List<String> types;
	private List<String> voyage;
	private String website;
	private String language;

	// Getter Methods

	public Location getLocation() {
		return LocationObject;
	}

	public Nicknames getNicknames() {
		return NicknameObject;
	}

	public String getAccuracy() {
		return accuracy;
	}

	public String getName() {
		return name;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public String getAddress() {
		return address;
	}

	public List<String> getTypes() {
		return types;
	}

	public List<String> getVoyage() {
		return voyage;
	}

	public String getWebsite() {
		return website;
	}

	public String getLanguage() {
		return language;
	}

	// Setter Methods

	public void setLocation(Location locationObject) {
		this.LocationObject = locationObject;
	}

	public void setNicknames(Nicknames NicknameObject) {
		this.NicknameObject = NicknameObject;
	}

	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

	public void setVoyage(List<String> voyage) {
		this.voyage = voyage;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
