package StepDefinitions;

import static org.junit.Assert.*;

import java.io.IOException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.given;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils{
	
	RequestSpecification res;
	static String place_id;
	ResponseSpecification responspec; //Global variable to hold the API result (then) like statuscode etc
	Response response;
	 JsonPath js;
	TestDataBuild data= new TestDataBuild();
	@Given("AddPlace Payload {string} {string} {string}")
	public void addplace_Payload(String name, String language, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete action
		 res = given().spec(RequestSpecification()).body(data.AddPlacePayload(name,language,address));
		
	}

	@When("user calls {string} using {string} http request")
	public void user_calls_using_http_request(String Resource, String httpmethod) {
	    // Write code here that turns the phrase above into concrete actions
		APIResources APIresource= APIResources.valueOf(Resource);
		
	    responspec = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
	    
	    if(httpmethod.equalsIgnoreCase("post"))
		response = res.when().post(APIresource.GetResource());
	    else if(httpmethod.equalsIgnoreCase("get"))
	    	response = res.when().get(APIresource.GetResource());
	}

	@Then("the API call got success with statuscode {int}")
	public void the_API_call_got_success_with_statuscode(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(200,response.getStatusCode());  
	}

	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String KeyValue, String ExpectedValue) {
	    // Write code here that turns the phrase above into concrete actions
		
		   assertEquals(ExpectedValue,GetJSONPath(response, KeyValue));
	}
	

	@Then("Verify Place_Id create maps to {string} using {string}")
	public void verify_Place_Id_create_maps_to_using(String Expectedname, String resource) throws IOException {
    // Write code here that turns the phrase above into concrete actions
		place_id = GetJSONPath(response, "place_id");
	//	req specification for get place api
		res=given().spec(RequestSpecification()).queryParam("place_id",place_id);
		user_calls_using_http_request(resource, "Get");
		String actualname= GetJSONPath(response,"name");
		  assertEquals(Expectedname,actualname);
	}
	@Given("DeletePlace payload")
	public void deleteplace_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		 res = given().spec(RequestSpecification()).body(data.DeletePayload(place_id));
	}


	}
