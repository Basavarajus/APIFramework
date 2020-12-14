package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.location;

public class TestDataBuild {
	
	
	public AddPlace AddPlacePayload(String name,String language,String address) {
		
		AddPlace place = new AddPlace();
		place.setAccuracy(50);
		place.setAddress(address);
		place.setPhone_number("(+91) 983 893 3937");
		place.setLanguage(language);
		place.setName(name);
		place.setWebsite("http://google.com");

		location l = new location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		place.setLocation(l);

		List<String> mylist = new ArrayList<String>();
		mylist.add("shoe park");
		mylist.add("shop");

		place.setTypes(mylist);
		return place;
	}
	
	
	public String DeletePayload(String place_id) {
		return "{\n" +" \"place_id\":\""+place_id+"\"\n" + "}";
		
	}
	

}
