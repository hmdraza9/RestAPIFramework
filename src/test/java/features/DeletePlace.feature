Feature: This is Google Map test feature - Delete Place

 @DeletePlace @Regression
Scenario Outline: Verify add place is working "<name>"
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
		| name                        | address                    | types                                   | language |
		| Test House - Delete         | 123, Street, East Coast    | Grocery_Dairy_Snack_Beverages_Medicines | English  |
		| Ahmed Villa - Delete        | 2w1q2e, Street, West Coast | Grocery_Snack_Beverages                 | French   |
		| Megha Resort - Delete       | 0o9i8u, Street, West Coast | Grocery_Snack_Beverages                 | Spanish  |
		| Sai Nilayam - Delete        | 8u7y6t, Street, West Coast | Grocery_Snack_Beverages                 | Hindi    |
		| IICC - Delete               | 7y6t5r, Street, West Coast | Grocery_Snack_Beverages                 | French   |
		| Eclipse Hall - Delete       | 0o6t3w, Street, West Coast | Grocery_Snack_Beverages                 | Hindi    |
		| Karachi Bakery - Delete     | 9i1q8u, Street, West Coast | Grocery_Snack_Beverages                 | Sanskrit |
		| Puma Shoes - Delete         | 1q8u5r, Street, West Coast | Grocery_Snack_Beverages                 | Punjabi  |
		| Lifestyle Clothing - Delete | 9i0o8u, Street, West Coast | Grocery_Snack_Beverages                 | French   |
		| Shopers Stop - Delete       | 6t8u0o, Street, West Coast | Grocery_Snack_Beverages                 | Sanskrit |
	
	