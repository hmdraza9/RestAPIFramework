Feature: Guru99 login functionality

Background:
	Given User set up the browser

 @guru99login @Regression
 Scenario Outline: Guru99 login logout
	When User login with "<username>" and "<password>"
	And user verifies the login success "<username>"
	Then user logout the application
	
	Examples:
		| username                     | password                     |
		| mngr549176             | esAmUze          |