Feature: Online Process

  Scenario: Google Testinium Search
    * "http://www.google.com/" sayfasina git
    * "txtInput" elementine "testinium" degerini yaz
    * "txtInput" elementine "ENTER" key gonder
    * "h3Check" elementinin bulundugunu kontrol et

  @yemek
  Scenario: Yemeksepeti
    * "https://www.yemeksepeti.com/" sayfasina git
    * "plate34" elementine tikla
    * "areaSelector" elementinin bulundugunu kontrol et
    * "areaSelector" elementine tikla
    * "armaganevler" elementine tikla
    * "searchBox" elementinin bulundugunu kontrol et

    @Aegon
  Scenario: Aegon
    * "https://www.aegon.com.tr/ana-sayfa/" sayfasina git
    * "tiklayiniz" elementine tikla
    * "checkHayat" elementinin bulundugunu kontrol et
