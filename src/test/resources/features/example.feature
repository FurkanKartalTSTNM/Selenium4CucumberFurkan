Feature: Online Process

  @Search
  Scenario: Google Testinium Search
    * "http://www.google.com/" sayfasina git
    * "txtInput" elementine "testinium" degerini yaz
    * "txtInput" elementine "ENTER" key gonder
    * "h3Check" elementinin bulundugunu kontrol et
