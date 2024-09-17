Feature: T-Mobile Rest Assured Test

  @test1
  Scenario: Exchange rates
    Given I get current exchange rates
    Then  I display rate for currency code: USD
    Then  I display rate for currency name: dolar amerykański
    Then  I display currencies with rate above: 5
    Then  I display currencies with rate under: 3