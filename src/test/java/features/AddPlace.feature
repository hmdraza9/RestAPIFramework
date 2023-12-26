Feature: This is Google Map test feature - Add Place

 @AddPlace @Regression
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
		| name                     | address                    | types                                   | language |
		| Test House - Add         | 123, Street, East Coast    | Grocery_Dairy_Snack_Beverages_Medicines | English  |
		| Ahmed Villa - Add        | 2w1q2e, Street, West Coast | Grocery_Snack_Beverages                 | French   |
		| Megha Resort - Add       | 0o9i8u, Street, West Coast | Grocery_Snack_Beverages                 | Spanish  |
		| Sai Nilayam - Add        | 8u7y6t, Street, West Coast | Grocery_Snack_Beverages                 | Hindi    |
		| IICC - Add               | 7y6t5r, Street, West Coast | Grocery_Snack_Beverages                 | French   |
		| Eclipse Hall - Add       | 0o6t3w, Street, West Coast | Grocery_Snack_Beverages                 | Hindi    |
		| Karachi Bakery - Add     | 9i1q8u, Street, West Coast | Grocery_Snack_Beverages                 | Sanskrit |
		| Puma Shoes - Add         | 1q8u5r, Street, West Coast | Grocery_Snack_Beverages                 | Punjabi  |
		| Lifestyle Clothing - Add | 9i0o8u, Street, West Coast | Grocery_Snack_Beverages                 | French   |
		| Shopers Stop - Add       | 6t8u0o, Street, West Coast | Grocery_Snack_Beverages                 | Sanskrit |
	
	