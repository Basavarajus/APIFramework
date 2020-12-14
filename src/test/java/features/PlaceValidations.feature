Feature: Validating Place API's

@AddPlace
Scenario Outline: Verify if place being added successfully using AddPlaceAPI
	Given AddPlace Payload "<Name>" "<Language>" "<address>"
    When user calls "AddPlaceAPI" using "post" http request 
	Then the API call got success with statuscode 200
	And  "status" in response body is "OK" 
	And  "scope" in response body is "APP"  
	And Verify Place_Id create maps to "<Name>" using "GetPlaceAPI"
	
	
Examples:
	 |Name      |Language |address |
	 |Basavaraju| English |Bangalore|
#	 |Nagaraj| Kannada |Mangalore|

@DeletePlace
Scenario: Verify if delete place functionality is working
	Given DeletePlace payload
	When user calls "DeletePlaceAPI" using "post" http request 
	Then the API call got success with statuscode 200
	And  "status" in response body is "OK" 