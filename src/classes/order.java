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
 * @author ismail
 */
public class order {
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
    
    public static Object[][] getOrder()
    {
        int countData = 0;
        Object[][] dataResult = null;
        try {
            
            connectDB();
            statement = con.createStatement();
            result = statement.executeQuery("SELECT count(*) FROM pemesanan");
            
            while(result.next()) {
                countData = result.getInt(1);
            }
            
            dataResult = new Object[countData][6];
            statement = con.createStatement();
            result = statement.executeQuery("SELECT * FROM pemesanan");
            
            int counter = 0;
            
            while(result.next()) {
                dataResult[counter][0] = result.getString(1);
                dataResult[counter][1] = result.getString(2);
                dataResult[counter][2] = result.getString(3);
                dataResult[counter][3] = result.getString(4);
                dataResult[counter][4] = result.getString(5);
                dataResult[counter][5] = result.getString(6);
                ++counter;
            }
            
        } catch (Exception e) {
            System.exit(1);
        }
        
        return dataResult;
    }
    
    
    public static boolean addOrder(String b,String c,String d,String f,String g)
    {
        try {
            connectDB();
            
            preparedStatement = con.prepareStatement("INSERT INTO pemesanan VALUES ('', ?, ?, ?, ?,?)");
            preparedStatement.setString(1, b);
            preparedStatement.setString(2, c);
            preparedStatement.setString(3, d);
            preparedStatement.setString(4, f);
            preparedStatement.setString(5, g);
            preparedStatement.executeUpdate();
            
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
    
    public static boolean updateOrder(String a,String b,String c,String d,String f, String g)
    {
        try {
            connectDB();
            
            preparedStatement = con.prepareStatement("UPDATE pemesanan SET id_user = ?, no_meja = ?, tanggal = ?, keterangan = ?, status_order = ? WHERE id_order = ? ");            
            preparedStatement.setString(1, b);
            preparedStatement.setString(2, c);
            preparedStatement.setString(3, d);
            preparedStatement.setString(4, f);
            preparedStatement.setString(5, g);
            preparedStatement.setString(6, a);
            preparedStatement.executeUpdate();
            
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
    
    
    public static boolean deleteOrder(String order_id)
    {
        try {
            connectDB();
            
            preparedStatement = con.prepareStatement("DELETE FROM pemesanan WHERE id_order = ?");
            preparedStatement.setString(1, order_id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
