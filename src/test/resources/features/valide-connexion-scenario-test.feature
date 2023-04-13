Feature: Sauce Demo Test Connexion Modibo

	@TEST_POEI23-377 @Login
	Scenario Outline: Sauce Demo Test Connexion Modibo
		Given Je ouvre le site "https://www.saucedemo.com/"
		When  Je saisis le username "<username>"
		And   Je saisis le mot de passe "<password>"
		And   Je clique sur le button "<Login>"
		Then  La page de accueil est affichee avec le titre "Swag Labs"
		
		Examples:
		  |username                 |password     |
		  |standard_user            |secret_sauce |
		  |problem_user             |secret_sauce |
		  |performance_glitch_user  |secret_sauce |
