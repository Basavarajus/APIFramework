package StepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlaceAPI")
	public void beforeScenario() throws IOException {
		
		//Write a code that will give you place id
		//execute this code only if place id is null
		StepDefinition StepDef = new StepDefinition();
		if(StepDefinition.place_id==null) {
			StepDef.addplace_Payload("Raju", "Kannada", "KG Halli");
			StepDef.user_calls_using_http_request("AddPlaceAPI", "post");
			StepDef.verify_Place_Id_create_maps_to_using("Raju", "GetPlaceAPI");
		}
		}
	

}
