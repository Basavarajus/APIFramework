package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.internal.path.json.JSONAssertion;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

//this class contains all reusable methods
public class Utils {

	public static RequestSpecification req;

	public RequestSpecification RequestSpecification() throws IOException {
		if (req == null) {
			PrintStream log = new PrintStream(new FileOutputStream("Logging.txt"));
			req = new RequestSpecBuilder()
					.setBaseUri(GetGlobalValue("baseUrl"))
					.addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
			return req;

		}
		return req;

	}

	public ResponseSpecification ResponseSpe() {
		ResponseSpecification responspec = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		return responspec;

	}

	public static String GetGlobalValue(String Key) throws IOException {
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(
				"/home/basavaraju/eclipse-workspace/APIFrameWork/src/test/java/resources/global.properties");
		prop.load(file);
		return prop.getProperty(Key);
	}
	
	
	public String GetJSONPath(Response response,String key) {
		
		 String resp = response.asString();
		   JsonPath js = new JsonPath(resp);
		    return  js.getString(key).toString();
		
	}

}
