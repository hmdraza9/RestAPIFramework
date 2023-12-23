Feature: Title of your feature

@AddPlace @DeletePlace
Scenario: Verify add place is working "<name>"
	Given Add Place Payload with "<name>" "<address>" "<types>" "<language>"
	When User call "AddPlaceAPI" with "POST" request
	Then the API call is success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify data "<address>" for string "address" with "GetPlaceAPI"
	Given deletePlace Payload
	When User call "DeletePlaceAPI" with "POST" request
	Then the API call is success with status code 200
	And "status" in response body is "OK"
	
	Examples:
		| name       | address                    | types                                   | language |
		| Test House | 123, Street, East Coast    | Grocery_Dairy_Snack_Beverages_Medicines | English  |
		| New Villa  | 1q2w3e, Street, West Coast | Grocery_Snack_Beverages                 | French   |
	
	
	