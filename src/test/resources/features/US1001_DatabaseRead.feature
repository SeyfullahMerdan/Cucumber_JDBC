Feature: US1001 Kullanici Database'e baglanip bilgileri okuyabilir

  @db
  Scenario: TC01 kullanici database baglanip istedigi bilgileri okuyabilmeli

    Given kullanici HMC veri tabanina baglanir
    #Database'e baglanacagız.
    And kullanici "tHOTELROOM" tablosundaki "Price" verilerini alir
    # SELECT price FROM HOTELROOM
    And kullanici "Price" sutunundaki verileri okur
    # 3. adımda database sorgusu sonunda bize dondurulen bilgiyi nasıl kullanabilecegimizi görecegiz


    # CRUD --> Create Read Update  Delete   Database'de yapabilecegimiz işlemler. // Burada bilgilere erişebliyoruz