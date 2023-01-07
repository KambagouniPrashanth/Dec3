package com.example.dec3;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.ResultSet;

/*to create new class right click on java and just write class name and then enter*/
public class Login
{
    DatabaseConnection dbconn=new DatabaseConnection();
    //here SHA-256 is algorithm that encrypt the password and stored in the byte form

    public  static byte[] getSHA(String input)
    {

        try
        {
            MessageDigest md=MessageDigest.getInstance("SHA-256");
            return md.digest(input.getBytes(StandardCharsets.UTF_8));


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    //https://www.javatpoint.com/how-to-encrypt-password-in-java
    //read above article for the better understanding
     public static String getEncryptedPassword(String password)
     {
         String encryptedPassword="";
         BigInteger number=new BigInteger(1,getSHA(password));
         StringBuilder hexString=new StringBuilder(number.toString(16));
         encryptedPassword=hexString.toString();
         return encryptedPassword;
     }



    public boolean customerLogin(String email,String password)
    {
        try {
            String query=String.format("SELECT *FROM customer WHERE email='%s' && password='%s'",email,password);
            ResultSet rs=dbconn.getQueryTable(query);
            if(rs==null)return false;
            if(rs.next())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return true;
        }
    }
   /* public static void main(String args[])
    {
        System.out.println(getEncryptedPassword("ram123"));
    }*/
}
