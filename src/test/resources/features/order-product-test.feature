Feature: SauceDemo select product from home page test Modibo

	@POEI23-378 @OrderProduct
	Scenario Outline: SauceDemo select product from home page test Modibo
		Given I open web site "https://www.saucedemo.com/"
		And   I enter my username "<username>"
		And   I enter my password "<password>"
		And   I click on login button
		When  I select the first product "<index>"
		And   I find product title 
		And   I find product price
		And   I add product to cart 
		And   I go back to products page 
		And   I open my cart
		And   I find the title of product in my cart
		Then  Products page title is "Swag Labs"
		And   Product title is "<expectedTitle>"
		And   Product price is "<expectedPrice>"
		And   Product title in my cart is as "<expectedTitle>"
		
		Examples:
		  |username                | password   | index |expectedTitle                     |expectedPrice |expectedTitle|
		  |standard_user           |secret_sauce| 0     |Sauce Labs Backpack               |$29.99        |Sauce Labs Backpack|
		  |standard_user             |secret_sauce| 5     |Test.allTheThings() T-Shirt (Red) |$15.99        |Test.allTheThings() T-Shirt (Red)|
		  |standard_user           |secret_sauce| 3     |Sauce Labs Fleece Jacket          |$49.99        |Sauce Labs Fleece Jacket|
