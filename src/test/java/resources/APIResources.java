package resources;

//Enum is special class which holds list of constants and methods
public enum APIResources {
	
	AddPlaceAPI("/maps/api/place/add/json"),
	GetPlaceAPI("maps/api/place/get/json"),
	DeletePlaceAPI("maps/api/place/delete/json");
	private String resource;
	
	
	APIResources(String resource) {
		// TODO Auto-generated constructor stub
		this.resource=resource;
	}
	
	public String GetResource() {
		return resource;	
		}
	
	

}
