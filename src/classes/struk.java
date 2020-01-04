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
public class struk {
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

    public static Object[][] getStruk()
    {
        int countData = 0;
        Object[][] dataResult = null;
        try {
            
            connectDB();
            statement = con.createStatement();
            result = statement.executeQuery("SELECT count(*) FROM struk");
            
            while(result.next()) {
                countData = result.getInt(1);
            }
            
            dataResult = new Object[countData][8];
            statement = con.createStatement();
            result = statement.executeQuery("SELECT * FROM struk");
            
            int counter = 0;
            
            while(result.next()) {
                dataResult[counter][0] = result.getString(1);
                dataResult[counter][1] = result.getString(2);
                dataResult[counter][2] = result.getString(3);
                dataResult[counter][3] = result.getString(4);
                dataResult[counter][4] = result.getString(5);
                dataResult[counter][5] = result.getString(6);
                dataResult[counter][6] = result.getString(7);
                dataResult[counter][7] = result.getString(8);
                ++counter;
            }
            
        } catch (Exception e) {
            System.exit(1);
        }
        
        return dataResult;
    }
    
    public static String[] getPrimaryCode1()
    {
        int countData = 0;
        String[] data = null;
        
        try {
            connectDB();
            
            statement = con.createStatement();
            result = statement.executeQuery("SELECT count(*) FROM order");
            
            while(result.next())
            {
                countData = result.getInt(1);
            }
            
            data = new String[countData];
            statement = con.createStatement();
            result = statement.executeQuery("SELECT id_order FROM order");
            
            int counter = 0;
            
            while(result.next()) {
                data[counter] = result.getString(1);
                ++counter;
            }
            
        } catch(Exception e) {
            System.exit(1);
        }
        
        return data;
    }
    
    public static String[] getPrimaryCode2()
    {
        int countData = 0;
        String[] data = null;
        
        try {
            connectDB();
            
            statement = con.createStatement();
            result = statement.executeQuery("SELECT count(*) FROM user");
            
            while(result.next())
            {
                countData = result.getInt(1);
            }
            
            data = new String[countData];
            statement = con.createStatement();
            result = statement.executeQuery("SELECT id_user FROM user");
            
            int counter = 0;
            
            while(result.next()) {
                data[counter] = result.getString(1);
                ++counter;
            }
            
        } catch(Exception e) {
            System.exit(1);
        }
        
        return data;
    }
    
    public static boolean addStruk(String a,String b,String c,String d,String f,String g,int h)
    {
        try {
            connectDB();
            
            preparedStatement = con.prepareStatement("INSERT INTO struk VALUES (?, ?, ?, ?,?,?,?)");
            preparedStatement.setString(1, a);
            preparedStatement.setString(2, b);
            preparedStatement.setString(3, c);
            preparedStatement.setString(4, d);
            preparedStatement.setString(5, f);
            preparedStatement.setString(6, g);
            preparedStatement.setInt(7, h);
            preparedStatement.executeUpdate();
            
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
    
    public static boolean updateStruk(String a,String b,String c,String d,String f,String g,int h)
    {
        try {
            connectDB();
            
            preparedStatement = con.prepareStatement("UPDATE struk SET id_user = ?, id_order = ?, nama_user = ?, nama_menu = ?, tanggal = ?, total_bayar = ? WHERE id_struk = ? ");            
            preparedStatement.setString(1, b);
            preparedStatement.setString(2, c);
            preparedStatement.setString(3, d);
            preparedStatement.setString(4, f);
            preparedStatement.setString(5, g);
            preparedStatement.setInt(6, h);
            preparedStatement.setString(7, a);
            preparedStatement.executeUpdate();
            
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
    
    
    public static boolean deleteStruk(String order_id)
    {
        try {
            connectDB();
            
            preparedStatement = con.prepareStatement("DELETE FROM struk WHERE id_struk = ?");
            preparedStatement.setString(1, order_id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
