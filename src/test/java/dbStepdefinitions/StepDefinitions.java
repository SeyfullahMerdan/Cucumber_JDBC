package dbStepdefinitions;

import io.cucumber.java.en.Given;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StepDefinitions {
// Database'e baglanmam için bir Autentication gerekecek, bunu her seferinde sistem yöneticisinden alcagım.
//      databaseName=hotelmycamp ; user=techproed;password=P2s@rt65";    Autentication
// öncesindeki kısım ise IP'dir. Her sitenin bir adresi vardır.

    String url="jdbc:sqlserver://184.168.194.58:1433;databaseName=hotelmycamp ; user=techproed;password=P2s@rt65";
    String username="techproed";
    String password="P2s@rt65";
    Connection connection;   // Baglantıyı kuracak
    Statement statement;      // Sorguyu çalıştırack
    ResultSet resultSet;     // Sonucu kullanabilmemizi saglayacak
// Class levelde oluşturdum aşagıda deger atmasını yapacagım.

    @Given("kullanici HMC veri tabanina baglanir")
    public void kullanici_hmc_veri_tabanina_baglanir() throws SQLException {
        // database'e baglantı kuruyoruz. Driveri connection yaptım. Bir nevi bagladım gibi düşünebilirsiniz.
        connection= DriverManager.getConnection(url, username, password);
        statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

    }


    @Given("kullanici {string} tablosundaki {string} verilerini alir")
    public void kullanici_tablosundaki_verilerini_alir(String table, String field) throws SQLException {
        // Query çalıştıracağız
        // Select Price from tHotelTable
        String readQuery="SELECT " +field+ " FROM " + table;
        // SQL sorgumu dinamik olarak yazmış oldum.
        resultSet=statement.executeQuery(readQuery);
        // Sorguyu çalıştırma komutudur.
        // Durumu execute yap diyoruz içine de sorgumun kodlarını giriyorum.

    }


    @Given("kullanici {string} sutunundaki verileri okur")
    public void kullanici_sutunundaki_verileri_okur(String field) throws SQLException {

        // Resultset bizim şuana kadar kullandıgımız set yapısında degildir.
        // Resultset iterator ile çalışır. İmleç,index yapısı olmayan yapılar için kullanılırdı.
        // ResultSet içindeki bilgileri okumak için önce iteratoru istedigimiz yere göndermeliyiz.

        resultSet.first();
        //Bu komut iteratoru ilk elemente götürür, gitti ise true, gidemezse false döner
        //iterator istenen konuma gidince artık elementi yazdırabilirz.

        System.out.println( resultSet.getString(field) );

        // ikinci oda fiyatını görmek istersek iteratori next ile bir sonraine yollamamız gerek.
        resultSet.next();
        System.out.println(resultSet.getString(field));


          System.out.println(resultSet.next());  // true döner. İşlemi gerçekleştirebilecegi için True döner.
          System.out.println(resultSet.getString(field));

          // resultset.next nerede olursa olsun iteratoru bir sonrakine geçirir. İmlec her kod kullanıldıgında
        //  bir sonrakine geçer.

        System.out.println("===============================================================================");
        // Tüm price sutunu yazdırmak istiyorsam.
        // Bunun için nexti sürekli hareket ettirerek Loop döngüsü içine alarak
        // tüm sonuçları alabilirim.
        // resultSet ile calisirken iterator konumunu kontrol etmek zorundayiz
        // eger 1. elemana donmeden listeyi yazdirmaya devam edersem
        // kaldigi yerden devam edip 4. element ve sonrasini yazdirir

        resultSet.absolute(0); // önce imleci en başa aldım.
        while (resultSet.next()) { // içeride - burada da next yapıyor. Gördügü her yerde oynatıyor imleci
            System.out.println(resultSet.getString(field));
        }


        // price sutununda kac data oldugunu bulalim
        // while loop ile resultSet.next() false donunceye kadar gittik
        // dolayisiyla artik iterator sonda

        resultSet.last();   // Resultset ile o dosyadaki verilere ulaşıp onları okuyabilirz
        System.out.println(resultSet.getRow());

        // suanda tum price bilgileri resultSet uzerinde
        // eger bu bilgilerle birden fazla test yapacaksak
        // bu bilgileri Java ortamina almakta fayda var
        // ornegin bir List<Double> olusturup
        // tum price verilerini bu listeye ekleyebiliriz
        // boylece bir java objesi olan LIst sayesinde
        // price degerleri uzerinde istedigimiz testleri yapabiliriz

        resultSet.absolute(0);
        List<Double> priceList=new ArrayList<Double>();

        while (resultSet.next()) {
            priceList.add(resultSet.getDouble(field));
        }

        System.out.println(priceList);



    }
}
