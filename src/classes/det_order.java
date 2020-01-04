/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author azz
 */
public class det_order {
    public static Connection con;
    public static Statement statement;
    public static PreparedStatement preparedStatement;
    public static ResultSet result;
    
    public static void connectDB()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/restoran", "root", "");            
            
        } catch (Exception e) {
            
            System.out.println(e);
            System.exit(1);
            
        }
    }
    
    public static Object[][] getDetOrder()
    {
        int countData = 0;
        Object[][] dataResult = null;
        try {
            
            connectDB();
            statement = con.createStatement();
            result = statement.executeQuery("SELECT count(*) detail_FROM pemesanan");
            
            while(result.next()) {
                countData = result.getInt(1);
            }
            
            dataResult = new Object[countData][4];
            statement = con.createStatement();
            result = statement.executeQuery("SELECT * FROM detail_pemesanan");
            
            int counter = 0;
            
            while(result.next()) {
                dataResult[counter][0] = result.getString(1);
                dataResult[counter][1] = result.getString(2);
                dataResult[counter][2] = result.getString(3);
                dataResult[counter][3] = result.getString(4);
                ++counter;
            }
            
        } catch (Exception e) {
            System.exit(1);
        }
        
        return dataResult;
    }
    
    
    public static boolean addDetOrder(String c,String d)
    {
        try {
            connectDB();
            
            preparedStatement = con.prepareStatement("INSERT INTO detail_pemesanan VALUES ('', '', ?,?)");
            preparedStatement.setString(1, c);
            preparedStatement.setString(2, d);
            preparedStatement.executeUpdate();
            
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
    
    public static boolean updateDetOrder(String a,String b,String c,String d)
    {
        try {
            connectDB();
            
            preparedStatement = con.prepareStatement("UPDATE detail_pemesanan SET id_order = ?, menu = ?, total_harga = ? WHERE id_detail = ? ");            
            preparedStatement.setString(1, b);
            preparedStatement.setString(2, c);
            preparedStatement.setString(3, d);
            preparedStatement.setString(5, a);
            preparedStatement.executeUpdate();
            
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
    
    
    public static boolean deleteDetOrder(String order_id)
    {
        try {
            connectDB();
            
            preparedStatement = con.prepareStatement("DELETE FROM detail_pemesanan WHERE id_detail = ?");
            preparedStatement.setString(1, order_id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}