Feature: Online Process

  @Test
  Scenario: Google Testinium Search
    * "https://account.testinium.com/uaa/login" sayfasina git
    * "txtInput" elementine "testinium" degerini yaz
    * "txtInput" elementine "ENTER" key gonder
