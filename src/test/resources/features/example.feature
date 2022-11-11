Feature: Online Process

  Scenario: Google Testinium Search
    * "http://www.google.com/" sayfasına git
    * "txtInput" elementine "testinium" degerini yaz
    * "txtInput" elementine "ENTER" key gonder
    * "h3Check" elementinin bulunduğunu kontrol et

    @yemek
  Scenario: Yemeksepeti
    * "https://www.yemeksepeti.com/" sayfasına git
    * "plate34" elementine tıkla
    * "areaSelector" elementinin bulunduğunu kontrol et
    * "areaSelector" elementine tıkla
    * "armaganevler" elementine tıkla
    * "searchBox" elementinin bulunduğunu kontrol et

    @Aegon
  Scenario: Aegon
    * "https://www.aegon.com.tr/ana-sayfa/" sayfasına git
    * "tiklayiniz" elementine tıkla
    * "checkHayat" elementinin bulunduğunu kontrol et
