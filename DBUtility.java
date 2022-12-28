package Utilities;

import java.sql.*;
import java.util.ArrayList;

public class DBUtility {

    public static Statement statement;
    public static Connection connection;

    public static ArrayList<ArrayList<String>> getListData (String query)
    {   // query : SELECT * FROM city;

        ArrayList< ArrayList<String> > tablo = new ArrayList<>();
        // DB'den bütün row and column'ları okuyup list'e atacağım

        // 1 - DB bağlantısını aç
        DBConnectionOpen();

        // 2 - Query'yi çalıştır
       try {
           ResultSet rs = statement.executeQuery(query);
           int kolonSayisi = rs.getMetaData().getColumnCount();

           // 3 - Query sonucunu tablo'ya doldur, bütün satırları ve satırlardaki sütunları oku ve tabloya ekle
           while (rs.next()) {

               ArrayList<String> row = new ArrayList<>();
               for (int i = 1; i <= kolonSayisi; i++)
                   row.add(rs.getString(i));

               tablo.add(row);
           }
       }
       catch (Exception ex)
       {
           System.out.println(ex.getMessage());
       }
        // 4 - DB connection'ı kapat
        DBConnectionClose();

        return tablo;
    }

    public static void main(String[] args) {
        // getListData fonksiyonum çalışıyor mu?
        ArrayList<ArrayList<String>> table = getListData("SELECT * FROM actor");

        // test için kontrol, veriler geldi mi, liste atıldı mı
        // System.out.println("tablo = " + tablo);

        for (ArrayList<String> row : table)
            System.out.println("Row: " + row);
    }
        public static void DBConnectionOpen()
        {
            String url = "jdbc:mysql://db-technostudy.ckr1jisflxpv.us-east-1.rds.amazonaws.com:3306/sakila";
            String username = "root";
            String password = "'\"-LhCB'.%k[4S]z";

            try{
                connection = DriverManager.getConnection(url, username, password);
                statement = connection.createStatement();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public static void DBConnectionClose(){
        try{
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}



