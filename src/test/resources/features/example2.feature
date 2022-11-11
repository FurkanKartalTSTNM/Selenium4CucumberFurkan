Feature: example2

  Scenario: Google Testinium Search
    * "http://www.google.com/" sayfasina git
    * "txtInput" elementine "testinium" degerini yaz
    * "txtInput" elementine "ENTER" key gonder
    * "h3Check" elementinin bulundugunu kontrol et


  Scenario: Yemeksepeti1
    * "https://www.yemeksepeti1.com/" sayfasina git
    * "plate34" elementine tikla
    * "areaSelector" elementinin bulundugunu kontrol et
    * "areaSelector" elementine tikla
    * "armaganevler" elementine tikla
    * "searchBox" elementinin bulundugunu kontrol et

    @aegon
  Scenario: Aegon
    * "https://www.aegon.com1.tr/ana-sayfa/" sayfasina git
    * "tiklayiniz" elementine tikla
    * "checkHayat" elementinin bulundugunu kontrol et
