package Utilities;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseArrayList {

     // GET ROWS AND COLUMNS FROM DATABASE AND ADD INTO AN ARRAYLIST    
    
    public static void main(String[] args) {        
        
        ArrayList<ArrayList<String>> table = getListData("SELECT * FROM actor");
        
        for (ArrayList<String> row : table)
            
            System.out.println("Row: " + row);
    }
    
    
    public static Statement statement;
    public static Connection connection;   
    
    public static ArrayList<ArrayList<String>> getListData (String query)
    {       
        ArrayList< ArrayList<String> > table = new ArrayList<>();      
        
        // OPEN THE DATABASE
        
        DBConnectionOpen();    

        // START THE QUERY
        
       try {
           ResultSet rs = statement.executeQuery(query);
           int noOfCol = rs.getMetaData().getColumnCount();

         // ADD ALL ROWS AND COLUMNS INTO TABLE
           
           while (rs.next()) {

               ArrayList<String> row = new ArrayList<>();
               for (int i = 1; i <= noOfCol; i++)
                   row.add(rs.getString(i));

               table.add(row);
           }
       }
       catch (Exception ex)
       {
           System.out.println(ex.getMessage());
       }
        
      // CLOSE THE CONNECTION
        
        DBConnectionClose();

        return table;
    }
    
        public static void DBConnectionOpen()
        {
            String url = " ";  // write your URL here
            String username = " ";  // write your username here
            String password = " ";  // write your password here 

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



