package com.example.dec3;
import java.sql.*;

/*create one library file i.e lib
and copy the this mysql-connector-j-8.0.31.jar
go to project structure-->
                  modules-->
                   (click on)  +-->
                              jar directories-->
                                     clik on dec3 and click on lib and click on  mysql-connector-j-8.0.31.jar click on ok
in this way connect data connection to class



* */
public class DatabaseConnection {
    private final static String DB_URL="jdbc:mysql://localhost:3306/dec3";
    private final static String USER="root";
    private final static String PASS="prashanth";

   public Statement getStatement()
   {
       Statement statement=null;

       Connection conn;
       try
       {
           conn=DriverManager.getConnection(DB_URL,USER,PASS);
           statement= conn.createStatement();

       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
       return statement;
   }


    public ResultSet getQueryTable(String query)
    {
        Statement statement=getStatement();
        try{
            return statement.executeQuery(query);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    public int insertData(String query)
    {
        Statement statement=getStatement();
        try {
            return statement.executeUpdate(query);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }
    /*public static void main(String args[])
    {
        DatabaseConnection dbconn=new DatabaseConnection();


        String query="SELECT *FROM customer";
        ResultSet rs=dbconn.getQueryTable(query);

        try{
            while(rs!=null && rs.next())
            {
                System.out.println("Fetched results");
                System.out.println("cid:"+rs.getInt("cid")+
                        " Email: "+rs.getString("email"));

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
*/

}
