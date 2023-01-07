package com.example.dec3;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class Order {
    public SimpleIntegerProperty oid;
    public SimpleIntegerProperty cid;
    public SimpleIntegerProperty pid;

    public Order(int oid,int cid,int pid)
    {
        this.oid=new SimpleIntegerProperty(oid);
        this.cid=new SimpleIntegerProperty(cid);
        this.pid=new SimpleIntegerProperty(pid);
    }
    public static ObservableList<Order> getAllOrders()
    {
        DatabaseConnection dbConn=new DatabaseConnection();
        ObservableList<Order> data= FXCollections.observableArrayList();
        String selectAllProducts="SELECT *FROM orders ";
        try
        {
            ResultSet rs=dbConn.getQueryTable(selectAllProducts);
            while(rs.next())
            {
                data.add(new Order(rs.getInt("oid"),
                        rs.getInt("cid"),
                        rs.getInt("pic"))
                );
            }
            rs.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return data;
    }
    public static boolean placeSingleOrder(Product product,String customerEmail)
    {
        String orderQuery = String.format("INSERT INTO orders(quantity,customer_id,product_id,status) VALUES (1,(SELECT cid FROM customer WHERE email = '%s'),%s,'ORDERED')",
                customerEmail,product.getId());
        DatabaseConnection dbconn=new DatabaseConnection();
        if(dbconn.insertData(orderQuery)>=1)
        {
            return true;
        }
        System.out.println(orderQuery);
        return false;
    }
    public int getOid()
    {
        return getOid();
    }
    public int getCid()
    {
        return getCid();
    }
    public int getPid()
    {
        return getPid();
    }
}
