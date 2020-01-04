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
import java.util.HashMap;

/**
 *
 * @author azz
 */
public class cmenu {
    public static Connection con;
    public static Statement statement;
    public static PreparedStatement preparedStatement;
    public static ResultSet result;
    HashMap<String,String> datas = new HashMap<String,String>();
    
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
    
    public static Object[][] getMenu()
    {
        int countData = 0;
        Object[][] dataResult = null;
        try {
            
            connectDB();
            statement = con.createStatement();
            result = statement.executeQuery("SELECT count(*) FROM menu");
            
            while(result.next()) {
                countData = result.getInt(1);
            }
            
            dataResult = new Object[countData][4];
            statement = con.createStatement();
            result = statement.executeQuery("SELECT * FROM menu");
            
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
    
    public static Object[][] getMenus()
    {
        int countData = 0;
        Object[][] dataResult = null;
        try {
            
            connectDB();
            statement = con.createStatement();
            result = statement.executeQuery("SELECT count(*) FROM menu");
            
            while(result.next()) {
                countData = result.getInt(1);
            }
            
            dataResult = new Object[countData][2];
            statement = con.createStatement();
            result = statement.executeQuery("SELECT nama_menu,harga FROM menu");
            
            int counter = 0;
            
            while(result.next()) {
                dataResult[counter][0] = result.getString(1);
                dataResult[counter][1] = result.getString(2);
                ++counter;
            }
            
        } catch (Exception e) {
            System.exit(1);
        }
        
        return dataResult;
    }
    
    
    public static boolean addMenus(String a,String b,int c,String d)
    {
        try {
            connectDB();
            
            preparedStatement = con.prepareStatement("INSERT INTO menu VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, a);
            preparedStatement.setString(2, b);
            preparedStatement.setInt(3, c);
            preparedStatement.setString(4, d);
            preparedStatement.executeUpdate();
            
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
    
    public static boolean updateMenu(String a,String b,int c,String d)
    {
        try {
            connectDB();
            
            preparedStatement = con.prepareStatement("UPDATE menu SET nama_menu = ?, harga = ?, status_menu = ? WHERE id_menu = ?");            
            preparedStatement.setString(1, b);
            preparedStatement.setInt(2, c);
            preparedStatement.setString(3, d);
            preparedStatement.setString(4, a);
            preparedStatement.executeUpdate();
            
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
    
    
    public static boolean deleteMenu(String menu_id)
    {
        try {
            connectDB();
            
            preparedStatement = con.prepareStatement("DELETE FROM menu WHERE id_menu = ?");
            preparedStatement.setString(1, menu_id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
