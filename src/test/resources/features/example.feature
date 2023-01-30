Feature: Online Process

  @Search
  Scenario: Google Testinium Search
    * "https://account.testinium.com/uaa/login" sayfasina git
    * "txtInput" elementine "testinium" degerini yaz
    * "txtInput" elementine "ENTER" key gonder
