Feature: Title of your feature

Scenario: Verify add place is working
	Given Add Place Payload with "<name>" "<address>" "<types>"
	When User call "AddPlaceAPI" with "POST" request
	Then the API call is success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	
	Examples:
|name|address|types|
| Test House   | 123, Street, East Coast      |Grocery_Dairy_Snack_Beverages_Medicines|
#| New Villa   | 1q2w3e, Street, West Coast      |Grocery_Snack_Beverages|
	