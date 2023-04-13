Feature: SauceDemo fail login test mono

	@TEST_POEI23-388 @fail-login-test
	Scenario Outline: SauceDemo fail login test mono
		Given I open web site "https://www.saucedemo.com/"
		When  I enter my username "<username>"
		And   I enter my password "<password>"
		And   I click on login button
		Then  I verify that a error massage is generated "<errorMessage>"
		Examples:
		   |username                               | password              |errorMessage|
		   |locked_out_user                        | secret_sauce          |Epic sadface: Sorry, this user has been locked out.|
		   |standard_user                          | mobo                  |Epic sadface: Username and password do not match any user in this service|
